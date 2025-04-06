# bz-password

## 概述
抽象密码的类库，提供明文密码、哈希密码值对象表示、密码加密、格式验证、密码强度等功能。

## 类库使用

### 核心功能
1. 提供明文密码值对象（PlainPassword）、常用明文密码验证规则验证、获取明文密码强度。
2. 提供哈希密码值对象（HashedPassword）、哈希密码生成验证、支持常见的哈希算法：Argon2、BCrypt、SCrypt、PBKDF2。

### 安装依赖

```xml
<dependency>
    <groupId>org.bzbase.primitive</groupId>
    <artifactId>bzp-password</artifactId>
    <version>最新版本</version>
</dependency>
```

### 基本使用
#### 明文密码
创建一个明文密码值对象，并使用指定的规则进行验证。

```java
// 创建一个明文密码值对象，并使用8-20位，至少包含一个英文字母，一个数字的规则进行验证，验证失败则抛出 InvalidPasswordException 异常
PlainPassword plainPassword = PlainPassword.of("123456", CommonPasswordPolicy.AT_LEAST_ONE_LETTER_DIGIT);

// 获取密码文本，输出："123456"
System.out.println(plainPassword.getPlainText());

// 获取密码强度，输出："PasswordStrength.WEAK"
System.out.println(plainPassword.getStrength());
```

系统内置了一系列常见的密码规则可以开箱即用：
- **CommonPasswordPolicy.SIX_DIGIT**: 6位数字
- **CommonPasswordPolicy.AT_LEAST_TWO_OF_LETTER_DIGIT_SPECIAL**: 8-20位，至少包含英文字母，数字，特殊符号中的2种
- **CommonPasswordPolicy.AT_LEAST_THREE_OF_UPPER_LOWER_DIGIT_SPECIAL**: 8-20位，至少包含大写字母，小写字母，数字，特殊符号中的3种
- **CommonPasswordPolicy.AT_LEAST_ONE_LETTER_DIGIT**: 8-20位，至少包含一个英文字母，一个数字
- **CommonPasswordPolicy.AT_LEAST_ONE_UPPER_LOWER_DIGIT**: 8-20位，至少包含一个大写字母，一个小写字母，一个数字
- **CommonPasswordPolicy.AT_LEAST_ONE_LETTER_DIGIT_SPECIAL**: 8-20位，至少包含一个英文字母，一个数字，一个特殊符号
- **CommonPasswordPolicy.AT_LEAST_ONE_UPPER_LOWER_DIGIT_SPECIAL**: 8-20位，至少包含一个大写字母，一个小写字母，一个数字，一个特殊符号

如果内置系统规则不满足业务需求，可以通过实现 PasswordRule 接口自定义验证规则。也可以使用系统提供的大量基础规则类进行组合。

使用 RegexRule 类进行正则表达式验证:

```java
PasswordRule rule = new RegexRule("^[A-Za-z]{8,}$","至少 8 位只能包括英文字母");
PlainPassword plainPassword = PlainPassword.of("123456", rule);
```

使用 ComposeRule 类进行基本规则组合:

```java
// 至少6-12位，只能包含数字或字母，至少包含一个小写字母，一个大写字母
PasswordRule rule = new CompositeRule(
        new LengthRule(6, 12),
        new OnlyAllowedCharacterRule(Characters.DIGITS_OR_LETTERS, "只能包含数字或字母"),
        new AtLeastCharacterRule(Characters.LOWERCASE_LETTERS, "至少包含一个小写字母"),
        new AtLeastCharacterRule(Characters.UPPERCASE_LETTERS, "至少包含一个大写字母")
);
PlainPassword plainPassword = PlainPassword.of("123456", rule);
```

#### 哈希密码
数据库中我们会存储哈希后的密码，通过类库提供的密码哈希器对明文密码进行哈希处理获取哈希后的密码。

```java
import org.bzbase.primitive.password.HashedPassword;
import org.bzbase.primitive.password.hasher.BCryptPasswordHasher;
import org.bzbase.primitive.password.hasher.PasswordHasher;

// 实例化一个基于 BCrypt 的密码哈希器
PasswordHasher passwordHasher = new BCryptPasswordHasher();

// 对明文密码进行哈希处理，获取哈希后的密码
HashedPassword hashedPassword = passwordHasher.hash(PlainPassword.of("123456"));
// 验证指定明文密码是否和哈希密码匹配
boolean matched = passwordHasher.verify(PlainPassword.of("123456"), hashedPassword);

// 获取哈希密码算法名称，输出："BCRYPT"
System.out.println(hashedPassword.getAlgorithm());
// 获取哈希密码哈希值，输出："$2b$10$6Ht1NvNX9ITyLb3vbiWXSuTnplH6xXEW3wjjekA2rEv9WYryf/ghy"
System.out.println(hashedPassword.getHash());

// 在数据库中存储时，如果使用一个字段存储哈希后的密码，推荐存储包含算法名称的格式如"BCRYPT:$2b$10$6Ht1N"，默认以冒号分割，方便后续迁移哈希算法。
// 获取包含算法名称的哈希密码，输出："BCRYPT:$2b$10$6Ht1NvNX9ITyLb3vbiWXSuTnplH6xXEW3wjjekA2rEv9WYryf/ghy"
System.out.println(hashedPassword.toString());
// 通过哈希密码字符串，获取哈希密码对象
HashedPassword hashedPassword = HashedPassword.of("BCRYPT:$2b$10$6Ht1NvNX9ITyLb3vbiWXSuTnplH6xXEW3wjjekA2rEv9WYryf/ghy");
// 也可以通过哈希算法名称和哈希值，获取哈希密码对象
HashedPassword hashedPassword = HashedPassword.of("BCRYPT", "$2b$10$6Ht1NvNX9ITyLb3vbiWXSuTnplH6xXEW3wjjekA2rEv9WYryf/ghy");
```

系统内置了以下密码哈希器：
- **Argon2PasswordHasher**: Argon2 哈希算法
- **SCryptPasswordHasher**: SCrypt 哈希算法
- **BCryptPasswordHasher**: BCrypt 哈希算法
- **PBKDF2PasswordHasher**: PBKDF2 哈希算法

如果内置哈希算法不满足业务需求，可以通过实现 PasswordHasher 接口自定义哈希算法。
