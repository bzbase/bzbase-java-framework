package org.bzbase.domain.user.service;

import java.util.Set;

import org.bzbase.domain.user.User;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.domain.user.valueobject.UserProfile;
import org.bzbase.domain.user.valueobject.Verifiable;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.user.UserId;

/**
 * 用户管理服务
 * 
 * @author legendjw
 */
public interface UserManageService {
    /**
     * 创建用户
     * 
     * @param userId 用户ID
     * @param userPoolId 用户池ID
     * @param userProfile 用户资料
     */
    User createUser(UserPoolId userPoolId, UserProfile userProfile, Set<Verifiable<Identifier>> identifiers, UserId currentUserId);

    /**
     * 修改用户
     * 
     * @param userId 用户ID
     * @param userProfile 用户资料
     */
    User modifyUser(UserId userId, UserProfile userProfile, Set<Verifiable<Identifier>> identifiers, UserId currentUserId);

    /**
     * 删除用户
     * 
     * @param userId 用户ID
     */
    User deleteUser(UserId userId, UserId currentUserId);
}
