package org.bzbase.domain.user.service;

import org.bzbase.domain.user.User;
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
     * @param user 用户
     */
    User createUser(User user);

    /**
     * 修改用户
     * 
     * @param user 用户
     */
    User modifyUser(User user);

    /**
     * 删除用户
     * 
     * @param userId 用户ID
     */
    User deleteUser(UserId userId);
}
