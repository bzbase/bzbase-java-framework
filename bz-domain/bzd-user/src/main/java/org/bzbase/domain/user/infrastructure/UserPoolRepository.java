package org.bzbase.domain.user.infrastructure;

import java.util.Optional;

import org.bzbase.domain.user.UserPool;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.library.ddd.type.Repository;

/**
 * 用户池资源库
 *
 * @author legendjw
 */
public interface UserPoolRepository extends Repository<UserPool, UserPoolId> {
	/**
	 * 根据编码查询用户池
	 *
	 * @param code 用户池编码
	 * @return 用户池
	 */
	Optional<UserPool> findByCode(String code);

	/**
	 * 是否存在用户池
	 *
	 * @param code 用户池编码
	 * @return 是否存在
	 */
	boolean existsByCode(String code);
}
