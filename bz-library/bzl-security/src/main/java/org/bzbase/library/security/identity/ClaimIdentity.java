package org.bzbase.library.security.identity;

import java.util.Set;

import org.bzbase.library.security.claim.Claim;

import lombok.Value;

/**
 * 基于声明的身份认证实体
 *
 * @author legendjw
 */
@Value
public class ClaimIdentity implements Identity {
    /**
     * 身份标识
     */
    Identifier identifier;

    /**
     * 声明
     */
    Set<Claim> claims;

    /**
     * 认证类型
     */
    String authenticationType;
}
