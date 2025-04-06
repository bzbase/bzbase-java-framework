package org.bzbase.domain.organization.service.impl;

import org.bzbase.domain.organization.Organization;
import org.bzbase.domain.organization.infrastructure.OrganizationRepository;
import org.bzbase.domain.organization.service.OrganizationManageService;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 组织管理服务实现类
 */
@RequiredArgsConstructor
public class OrganizationManageServiceImpl implements OrganizationManageService {
    private final IdGenerator<String> idGenerator;
    private final OrganizationRepository organizationRepository;

    @Override
    public Organization createOrganization(String name, String shortName, UserId currentUserId) {
        if (organizationRepository.existsByName(name)) {
            throw new DomainException("组织名称已经存在");
        }

        OrganizationId organizationId = new OrganizationId(idGenerator.generate());
        return Organization.create(organizationId, name, shortName, currentUserId);
    }

    @Override
    public Organization modifyOrganization(OrganizationId id, String name, String shortName, UserId currentUserId) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new DomainException("指定的组织不存在"));

        boolean isNameChanged = !organization.getName().equals(name);
        if (isNameChanged && organizationRepository.existsByName(name)) {
            throw new DomainException("组织名称已经存在");
        }

        organization.modify(name, shortName, currentUserId);

        return organization;
    }

    @Override
    public Organization deleteOrganization(OrganizationId id, UserId currentUserId) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new DomainException("指定的组织不存在"));

        organization.delete(currentUserId);

        return organization;
    }

    @Override
    public Organization enableOrganization(OrganizationId id, UserId currentUserId) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new DomainException("指定的组织不存在"));

        organization.enable(currentUserId);

        return organization;
    }

    @Override
    public Organization disableOrganization(OrganizationId id, UserId currentUserId) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new DomainException("指定的组织不存在"));

        organization.disable(currentUserId);

        return organization;
    }
}
