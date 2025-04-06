package org.bzbase.domain.authentication;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.bzbase.domain.authentication.valueobject.AuthenticationId;
import org.bzbase.domain.authentication.valueobject.FailureMessage;
import org.bzbase.library.security.identity.Identifier;
import org.bzbase.library.security.identity.Identity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * 认证测试
 * 
 * @author legendjw
 */
class AuthenticationTest {
    @Test
    void test_initiate_authentication() {
        AuthenticationId id = new AuthenticationId("test-auth-id");
        String authenticationType = "password";
        Identifier identifier = mockIdentifier();

        Authentication authentication = Authentication.initiate(id, authenticationType, identifier);

        assertThat(authentication.getId()).isEqualTo(id);
        assertThat(authentication.getAuthenticationType()).isEqualTo(authenticationType);
        assertThat(authentication.getIdentifier()).isEqualTo(identifier);
        assertThat(authentication.isInitiated()).isTrue();
        assertThat(authentication.getInitiatedAt()).isNotNull();
    }

	@Test
	void test_authenticate_success() {
		AuthenticationId id = new AuthenticationId("test-auth-id");
		String authenticationType = "password";
		Identifier identifier = mockIdentifier();

		Authentication authentication = Authentication.initiate(id, authenticationType, identifier);
		Identity identity = mockIdentity();
		authentication.succeed(identity);

		assertThat(authentication.isSuccessful()).isTrue();
		assertThat(authentication.isFailed()).isFalse();
		assertThat(authentication.isInitiated()).isFalse();
		assertThat(authentication.getFinishedAt()).isNotNull();
        assertThat(authentication.getIdentifier()).isEqualTo(identity.getIdentifier());
		assertThat(authentication.getClaims()).isEqualTo(identity.getClaims());
	}

	@Test
	void test_authenticate_failed() {
		AuthenticationId id = new AuthenticationId("test-auth-id");
		String authenticationType = "password";
		Identifier identifier = mockIdentifier();

		Authentication authentication = Authentication.initiate(id, authenticationType, identifier);
		authentication.fail(new FailureMessage("AuthenticationException", "认证失败"));

		assertThat(authentication.isSuccessful()).isFalse();
		assertThat(authentication.isFailed()).isTrue();
		assertThat(authentication.isInitiated()).isFalse();
		assertThat(authentication.getFinishedAt()).isNotNull();
		assertThat(authentication.getFailureMessage()).isNotNull();
		assertThat(authentication.getFailureMessage().getCode()).isEqualTo("AuthenticationException");
		assertThat(authentication.getFailureMessage().getMessage()).isEqualTo("认证失败");
	}

	private Identifier mockIdentifier() {
        Identifier identifier = Mockito.mock(Identifier.class);
        Mockito.when(identifier.getType()).thenReturn("username");
        Mockito.when(identifier.getTypeName()).thenReturn("用户名");
        Mockito.when(identifier.getValue()).thenReturn("test-user");
        return identifier;
    }

	private Identity mockIdentity() {
		Identity identity = Mockito.mock(Identity.class);
		Identifier identifier = mockIdentifier();
		Mockito.when(identity.getIdentifier()).thenReturn(identifier);
		Mockito.when(identity.getClaims()).thenReturn(Collections.emptySet());
		return identity;
	}
}
