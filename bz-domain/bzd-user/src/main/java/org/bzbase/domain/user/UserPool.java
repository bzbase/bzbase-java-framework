package org.bzbase.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.library.ddd.type.AbstractAggregateRoot;
import org.bzbase.primitive.user.UserId;

import java.time.Instant;

/**
 * 用户池聚合根
 *
 * @author legendjw
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class UserPool extends AbstractAggregateRoot<UserPoolId> {
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
     * 创建人
     */
	private UserId createdBy;

	/**
	 * 创建时间
	 */
	private Instant createdAt;
}
