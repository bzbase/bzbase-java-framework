package org.bzbase.domain.user.service;

import org.bzbase.domain.user.User;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.domain.user.valueobject.UserProfile;
import org.bzbase.domain.user.valueobject.Verifiable;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.emailaddress.EmailAddress;
import org.bzbase.primitive.password.PlainPassword;
import org.bzbase.primitive.phonenumber.PhoneNumber;
import org.bzbase.primitive.user.Username;

import lombok.NonNull;

/**
 * 用户注册领域服务
 *
 * @author legendjw
 */
public interface UserRegisterService {
    /**
     * 通过用户名注册
     *
     * @param userPoolId  用户池id
     * @param username    用户名
     * @param password    密码
     * @param userProfile 用户信息
     * @return 用户
     */
    User registerByUsername(@NonNull UserPoolId userPoolId, @NonNull Username username, @NonNull PlainPassword password,
            UserProfile userProfile);

    /**
     * 通过手机号注册
     *
     * @param userPoolId            用户池id
     * @param verifiablePhoneNumber 可验证的手机号
     * @param password              密码
     * @param userProfile           用户信息
     * @return 用户
     */
    User registerByPhoneNumber(@NonNull UserPoolId userPoolId, @NonNull Verifiable<PhoneNumber> verifiablePhoneNumber,
            PlainPassword password, UserProfile userProfile);

    /**
     * 通过邮箱地址注册
     *
     * @param userPoolId             用户池id
     * @param verifiableEmailAddress 可验证的邮箱地址
     * @param password               密码
     * @param userProfile            用户信息
     * @return 用户
     */
    User registerByEmailAddress(@NonNull UserPoolId userPoolId,
            @NonNull Verifiable<EmailAddress> verifiableEmailAddress, PlainPassword password, UserProfile userProfile);

    /**
     * 通过本地身份注册
     *
     * @param userPoolId           用户池id
     * @param verifiableIdentifier 可验证的身份
     * @param password             密码
     * @param userProfile          个人信息
     * @return 用户
     */
    User registerByLocalIdentifier(@NonNull UserPoolId userPoolId, @NonNull Verifiable<Identifier> verifiableIdentifier,
            PlainPassword password, UserProfile userProfile);

    /**
     * 通过外部身份注册
     *
     * @param userPoolId         用户池id
     * @param externalIdentifier 外部身份
     * @param password           密码
     * @param userProfile        个人信息
     * @return 用户
     */
    User registerByExternalIdentifier(@NonNull UserPoolId userPoolId, @NonNull Identifier externalIdentifier,
            PlainPassword password, UserProfile userProfile);
}
