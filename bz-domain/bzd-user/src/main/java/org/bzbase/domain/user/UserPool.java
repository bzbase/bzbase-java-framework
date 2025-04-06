package org.bzbase.domain.user;

import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.library.ddd.type.LifecycleAggregateRoot;
import org.bzbase.primitive.user.UserId;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * 用户池聚合根
 *
 * @author legendjw
 */
@Getter
@SuperBuilder(toBuilder = true)
public class UserPool extends LifecycleAggregateRoot<UserPoolId> {
    /**
     * 用户池id
     */
    private UserPoolId id;

    /**
     * 用户池名称
     */
    private String name;

    /**
     * 用户池编码
     */
    private String code;

    /**
     * 创建用户池
     *
     * @param id            用户池id
     * @param name          用户池名称
     * @param code          用户池编码
     * @param currentUserId 当前用户id
     * @return 用户池
     */
    public static UserPool create(UserPoolId id, String name, String code, UserId currentUserId) {
        UserPool userPool = UserPool.builder()
                .id(id)
                .name(name)
                .code(code)
                .build();
        userPool.markAsCreated(currentUserId);
        return userPool;
    }

    /**
     * 修改用户池
     *
     * @param name          用户池名称
     * @param code          用户池编码
     * @param currentUserId 当前用户id
     */
    public void modify(String name, String code, UserId currentUserId) {
        this.name = name;
        this.code = code;
        this.markAsUpdated(currentUserId);
    }

    /**
     * 删除用户池
     *
     * @param currentUserId 当前用户id
     */
    public void delete(UserId currentUserId) {
        this.markAsDeleted(currentUserId);
    }
}
