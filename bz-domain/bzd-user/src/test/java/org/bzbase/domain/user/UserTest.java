package org.bzbase.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.bzbase.domain.authentication.factor.AuthenticationFactorType;
import org.bzbase.domain.user.authentication.factor.PasswordFactor;
import org.bzbase.domain.user.authentication.identity.UsernameIdentifier;
import org.bzbase.domain.user.valueobject.UserPoolId;
import org.bzbase.domain.user.valueobject.UserProfile;
import org.bzbase.domain.user.valueobject.Verifiable;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.PlainPassword;
import org.bzbase.primitive.password.hasher.BCryptPasswordHasher;
import org.bzbase.primitive.password.hasher.PasswordHasher;
import org.bzbase.primitive.person.Birthdate;
import org.bzbase.primitive.person.Gender;
import org.bzbase.primitive.person.Name;
import org.bzbase.primitive.user.Nickname;
import org.bzbase.primitive.user.Picture;
import org.bzbase.primitive.user.PreferredUsername;
import org.bzbase.primitive.user.UserId;
import org.bzbase.primitive.user.Username;
import org.junit.jupiter.api.Test;

/**
 * 用户测试
 *
 * @author legendjw
 */
class UserTest {

	@Test
	void test_new_user() {
		UserId userId = new UserId("1234567890");

		UserPoolId poolId = new UserPoolId("1234567890");
		UserProfile profile = UserProfile.builder()
				.nickname(Nickname.of("张三"))
				.preferredUsername(PreferredUsername.of("zhangsan"))
				.name(Name.of("张三"))
				.picture(Picture.of("https://example.com/avatar.jpg"))
				.gender(Gender.MALE)
				.birthdate(Birthdate.of(LocalDate.of(1990, 1, 1)))
				.build();
		User user = User.create(userId, poolId, profile);
		assertThat(user.getId()).isEqualTo(userId);
		assertThat(user.getPoolId()).isEqualTo(poolId);
		assertThat(user.getProfile()).isEqualTo(profile);
	}

	@Test
	void test_bind_identifier() {
		UserId userId = new UserId("1234567890");
		UserPoolId poolId = new UserPoolId("1234567890");
		User user = User.create(userId, poolId, null);
		UsernameIdentifier identifier = new UsernameIdentifier(Username.of("zhangsan"));
		Verifiable<Identifier> verifiableIdentifier = Verifiable.verified(identifier);
		user.bindIdentifier(verifiableIdentifier);
		assertThat(user.getIdentifiers()).contains(verifiableIdentifier);
	}

	@Test
	void test_unbind_identifier() {
		UserId userId = new UserId("1234567890");
		UserPoolId poolId = new UserPoolId("1234567890");
		User user = User.create(userId, poolId, null);
		UsernameIdentifier identifier = new UsernameIdentifier(Username.of("zhangsan"));
		user.bindIdentifier(Verifiable.verified(identifier));
		user.unbindIdentifierByType(identifier.getType());
		assertThat(user.getIdentifiers()).isEmpty();
	}

	@Test
	void test_add_auth_factor() {
		UserId userId = new UserId("1234567890");
		UserPoolId poolId = new UserPoolId("1234567890");
		User user = User.create(userId, poolId, null);
		PasswordFactor authFactor = new PasswordFactor(HashedPassword.of("MD5", "123456"));
		user.addAuthFactor(authFactor);
		assertThat(user.getAuthFactors()).contains(authFactor);
		assertThat(user.getAuthFactorByType(AuthenticationFactorType.PASSWORD.name())).hasValue(authFactor);
	}

	@Test
	void test_remove_auth_factor() {
		UserId userId = new UserId("1234567890");
		UserPoolId poolId = new UserPoolId("1234567890");
		User user = User.create(userId, poolId, null);
		PasswordFactor authFactor = new PasswordFactor(HashedPassword.of("MD5", "123456"));
		user.addAuthFactor(authFactor);
		user.removeAuthFactorByType(AuthenticationFactorType.PASSWORD.name());
		assertThat(user.getAuthFactors()).isEmpty();
		assertThat(user.getAuthFactorByType(AuthenticationFactorType.PASSWORD.name())).isEmpty();
	}

	@Test
	void test_change_password() {
		UserId userId = new UserId("1234567890");
		UserPoolId poolId = new UserPoolId("1234567890");
		User user = User.create(userId, poolId, null);
		HashedPassword hashedPassword = HashedPassword.of("MD5", "123456");
		user.changePassword(hashedPassword);
		assertThat(user.getPassword()).hasValue(hashedPassword);
	}

	@Test
	void test_verify_password() {
		UserId userId = new UserId("1234567890");
		UserPoolId poolId = new UserPoolId("1234567890");
		User user = User.create(userId, poolId, null);
		PasswordHasher passwordHasher = new BCryptPasswordHasher();
		PlainPassword plainPassword = PlainPassword.of("123456");
		HashedPassword hashedPassword = passwordHasher.hash(plainPassword);
		user.changePassword(hashedPassword);
		assertThat(user.verifyPassword(plainPassword, passwordHasher)).isTrue();
		assertThat(user.verifyPassword(PlainPassword.of("wrong"), passwordHasher)).isFalse();
	}
}
