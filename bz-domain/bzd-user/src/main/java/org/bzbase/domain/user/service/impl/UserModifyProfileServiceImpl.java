package org.bzbase.domain.user.service.impl;

import org.bzbase.domain.user.User;
import org.bzbase.domain.user.infrastructure.UserRepository;
import org.bzbase.domain.user.service.UserModifyProfileService;
import org.bzbase.domain.user.valueobject.UserProfile;
import org.bzbase.library.ddd.exception.DomainException;
import org.bzbase.primitive.user.UserId;

import lombok.RequiredArgsConstructor;

/**
 * 用户信息修改领域服务实现
 * 
 * @author legendjw
 */
@RequiredArgsConstructor
public class UserModifyProfileServiceImpl implements UserModifyProfileService {
    private final UserRepository userRepository;

    @Override
    public User modifyProfile(UserId userId, UserProfile newProfile) {
        User user = userRepository.findById(userId).orElseThrow(() -> new DomainException("指定用户不存在"));
        user.modifyProfile(newProfile);
        return user;
    }
}
