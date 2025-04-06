package org.bzbase.domain.user.service;

import org.bzbase.domain.user.User;
import org.bzbase.domain.user.valueobject.UserProfile;
import org.bzbase.primitive.user.UserId;

/**
 * 用户信息修改领域服务
 * 
 * @author legendjw
 */
public interface UserModifyProfileService {
    /**
     * 修改用户信息
     * 
     * @param userId 用户id
     * @param newProfile 新用户信息
     * @return 修改后的用户
     */
    User modifyProfile(UserId userId, UserProfile newProfile);
}
