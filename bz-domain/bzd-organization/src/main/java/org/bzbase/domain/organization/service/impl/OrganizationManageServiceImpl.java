package org.bzbase.domain.organization.service.impl;

import lombok.RequiredArgsConstructor;
import org.bzbase.domain.organization.Organization;
import org.bzbase.domain.organization.event.OrganizationCreatedEvent;
import org.bzbase.domain.organization.infrastructure.OrganizationRepository;
import org.bzbase.domain.organization.service.OrganizationManageService;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.organization.OrganizationId;

import java.time.Instant;

/**
 * 组织管理服务实现类
 */
@RequiredArgsConstructor
public class OrganizationManageServiceImpl implements OrganizationManageService {
    private final IdGenerator<String> idGenerator;
    private final OrganizationRepository organizationRepository;

    @Override
    public Organization createOrganization(Organization organization) {
        if (organizationRepository.existsByName(organization.getName())) {
            throw new DomainException("组织名称已经存在");
        }

        if (organization.getId() == null) {
            organization.setId(new OrganizationId(idGenerator.generate()));
        }
        organization.setCreatedAt(Instant.now());
        organization.registerDomainEvent(new OrganizationCreatedEvent(organization));

        return organization;
    }

    @Override
    public Organization modifyOrganization(Organization organization) {
        Organization oldOrganization = getOrganizationById(organization.getId());

        boolean isNameChanged = !organization.getName().equals(oldOrganization.getName());
        if (isNameChanged && organizationRepository.existsByName(organization.getName())) {
            throw new DomainException("组织名称已经存在");
        }

        return organization;
    }

    @Override
    public Organization deleteOrganization(OrganizationId id) {
        Organization organization = getOrganizationById(id);

        return organization;
    }

    @Override
    public Organization enableOrganization(OrganizationId id) {
        Organization organization = getOrganizationById(id);

        organization.enable();

        return organization;
    }

    @Override
    public Organization disableOrganization(OrganizationId id) {
        Organization organization = getOrganizationById(id);

        organization.disable();

        return organization;
    }

    public Organization getOrganizationById(OrganizationId id) {
        return organizationRepository.findById(id).orElseThrow(() -> new DomainException("指定的组织不存在"));
    }
}
