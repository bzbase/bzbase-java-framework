package org.bzbase.domain.authentication.infrastructure;

import org.bzbase.domain.authentication.Authentication;
import org.bzbase.domain.authentication.valueobject.AuthenticationId;
import org.bzbase.library.ddd.type.Repository;

/**
 * 身份认证资源库
 *
 * @author legendjw
 */
public interface AuthenticationRepository extends Repository<Authentication, AuthenticationId> {
}
