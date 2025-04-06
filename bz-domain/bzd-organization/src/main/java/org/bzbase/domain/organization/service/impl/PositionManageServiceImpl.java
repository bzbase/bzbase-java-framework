package org.bzbase.domain.organization.service.impl;

import org.bzbase.domain.organization.Position;
import org.bzbase.domain.organization.infrastructure.PositionRepository;
import org.bzbase.domain.organization.service.PositionManageService;
import org.bzbase.domain.organization.valueobject.OrganizationId;
import org.bzbase.domain.organization.valueobject.PositionId;
import org.bzbase.library.ddd.annotation.Service;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 岗位管理服务实现
 */
@Service
@RequiredArgsConstructor
public class PositionManageServiceImpl implements PositionManageService {
    private final IdGenerator<String> idGenerator;
	private final PositionRepository positionRepository;

	@Override
	public Position createPosition(OrganizationId organizationId, String name, String code, String description,
			UserId currentUserId) {
		PositionId positionId = new PositionId(idGenerator.generate());
		if (positionRepository.existsByOrganizationIdAndName(organizationId, name)) {
			throw new DomainException("岗位名称已经存在");
		}

		return Position.create(positionId, organizationId, name, code, description, currentUserId);
	}

	@Override
	public Position modifyPosition(PositionId positionId, String name, String code, String description,
			UserId currentUserId) {
		Position position = positionRepository.findById(positionId)
				.orElseThrow(() -> new DomainException("岗位不存在"));

		boolean isNameChanged = !position.getName().equals(name);
		if (isNameChanged && positionRepository.existsByOrganizationIdAndName(position.getOrganizationId(), name)) {
			throw new DomainException("岗位名称已经存在");
		}

		position.modify(name, code, description, currentUserId);
		return position;
	}

	@Override
	public Position deletePosition(PositionId positionId, UserId currentUserId) {
		Position position = positionRepository.findById(positionId)
				.orElseThrow(() -> new DomainException("岗位不存在"));
		position.delete(currentUserId);
		return position;
	}
}