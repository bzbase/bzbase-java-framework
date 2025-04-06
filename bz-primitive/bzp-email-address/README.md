# bz-email-address

## 概述
抽象电子邮箱地址的类库，提供邮箱地址的值对象表示、验证功能。

## 领域知识
参见 [邮箱地址领域文档](https://github.com/bzbase/bzbase-domain-docs/email-address/domain-knowledge.md)

## 类库使用

### 核心功能
1. 提供邮箱地址值对象（EmailAddress）以及地址合法性的验证。
2. 获取邮箱地址的部分字符串，如本地部分、域名部分。

### 安装依赖

```xml
<dependency>
    <groupId>org.bzbase.primitive</groupId>
    <artifactId>bzp-email-address</artifactId>
    <version>最新版本</version>
</dependency>
```

### 基本使用

```java
// 实例化并验证指定的邮箱地址，如果是无效的地址，则抛出 InvalidEmailAddressException 异常
EmailAddress emailAddress = EmailAddress.of("abc@gmail.com");

// 获取邮箱地址，输出："abc@gmail.com"
System.out.println(emailAddress.getAddress());

// 获取邮箱地址本地部分，输出："abc"
System.out.println(emailAddress.getLocalPart());

// 获取邮箱地址域名部分，输出："gmail.com"
System.out.println(emailAddress.getDomain());
```
