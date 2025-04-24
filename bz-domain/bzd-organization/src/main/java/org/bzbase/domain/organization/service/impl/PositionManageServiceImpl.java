package org.bzbase.domain.organization.service.impl;

import java.time.Instant;

import org.bzbase.domain.organization.Position;
import org.bzbase.domain.organization.infrastructure.PositionRepository;
import org.bzbase.domain.organization.service.PositionManageService;
import org.bzbase.domain.organization.valueobject.PositionId;
import org.bzbase.library.ddd.annotation.Service;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.library.ddd.type.IdGenerator;

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
	public Position createPosition(Position position) {
		if (position.getId() == null) {
			position.setId(new PositionId(idGenerator.generate()));
		}
		position.setCreatedAt(Instant.now());
		if (positionRepository.existsByName(position.getTenantId(), position.getOrganizationId(), position.getName())) {
			throw new DomainException("岗位名称已经存在");
		}

		return position;
	}

	@Override
	public Position modifyPosition(Position position) {
		Position oldPosition = getPositionById(position.getId());

		boolean isNameChanged = !position.getName().equals(oldPosition.getName());
		if (isNameChanged && positionRepository.existsByName(position.getTenantId(), position.getOrganizationId(), position.getName())) {
			throw new DomainException("岗位名称已经存在");
		}

		return position;
	}

	@Override
	public Position deletePosition(PositionId positionId) {
		Position position = getPositionById(positionId);
		
		if (positionRepository.existsByParentId(position.getTenantId(), position.getOrganizationId(), positionId)) {
			throw new DomainException("指定的岗位存在子岗位，不能删除");
		}

		return position;
	}

	public Position getPositionById(PositionId positionId) {
		return positionRepository.findById(positionId).orElseThrow(() -> new DomainException("岗位不存在"));
	}
}