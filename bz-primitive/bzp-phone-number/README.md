# bz-phone-number

## 概述
抽象电话号码及相关信息的类库，提供电话号码的值对象表示、验证、格式化、获取归属地、获取运营商等功能。

## 领域知识
参见 [电话号码领域文档](https://github.com/bzbase/bzbase-domain-docs/phone-number/domain-knowledge.md)

## 类库使用
### 核心功能
1. 提供电话号码值对象（PhoneNumber）、手机号码值对象（MobileNumber）、固定电话号码值对象（FixedLineNumber）以及号码合法性的验证。
2. 电话号码的格式化，如标准E164、国内号码等格式。
3. 获取电话号码的归属地、运营商信息。

### 安装依赖

```xml
<dependency>
    <groupId>org.bzbase.primitive</groupId>
    <artifactId>bzp-phone-number</artifactId>
    <version>最新版本</version>
</dependency>
```

### 基本使用

#### 实例化
```java
import org.bzbase.primitive.phonenumber.PhoneNumber;
import org.bzbase.primitive.phonenumber.MobileNumber;
import org.bzbase.primitive.phonenumber.FixedLineNumber;

// 实例化并验证指定的电话号码，如果是无效的号码，则抛出 InvalidPhoneNumberException 异常
// 输入的电话号码格式支持标准的国际电话号码格式以及国内电话号码格式，比如：+8618712345678、18712345678、187 1234 5678、010-2345-6789
PhoneNumber phoneNumber = PhoneNumber.of("18795951234");

// 默认的 of 方法采用宽松的验证规则，仅检查长度是否符合要求。如果需要严格验证号码是否符合运营商发布的号码段规则，可以使用 ofStrict 方法
PhoneNumber phoneNumber = PhoneNumber.ofStrict("18795951234");

// 获取电话号码类型，输出：PhoneNumberType.MOBILE
System.out.println(phoneNumber.getType());

// 获取电话号码的原始值，输出：18795951234
System.out.println(phoneNumber.getNumber());

// PhoneNumber电话号码值对象支持固定电话号码、移动电话号码、VOIP号码等多种类型，如果需要表示特定的电话号码类型可以使用特定的值对象，类库额外
// 提供了固定电话号码值对象（FixedLineNumber）和移动电话号码值对象（MobileNumber）这两种最常用的类型。
// 实例化并验证指定的手机号码
MobileNumber mobileNumber = MobileNumber.of("18795951234");
// 实例化并验证指定的固定电话号码
FixedLineNumber fixedLineNumber = FixedLineNumber.of("01023456789");
```

#### 格式化
```java
import org.bzbase.primitive.phonenumber.PhoneNumber;

// 实例化指定的电话号码
PhoneNumber phoneNumber = PhoneNumber.of("18795951234");

// 获取E164格式，输出："+8618795951234"
System.out.println(phoneNumber.toE164Format());

// 获取E164转换后的可读格式(类似E123国际号码格式)，输出："+86 187 9595 1234"
System.out.println(phoneNumber.toReadableE164Format());

// 获取国内号码格式，输出："18795951234"
System.out.println(phoneNumber.toNationalFormat());

// 获取国内号码可读格式，输出："187 9595 1234"
System.out.println(phoneNumber.toReadableNationalFormat());

// 获取RFC3966格式，输出："tel:+86-187-9595-1234"
System.out.println(phoneNumber.toRFC3966Format());
```

#### 获取号码归属地、运营商信息
```java
import org.bzbase.primitive.phonenumber.PhoneNumber;

// 实例化指定的电话号码
PhoneNumber phoneNumber = PhoneNumber.of("18795951234");

// 获取号码归属地，输出："江苏省南京市"
System.out.println(phoneNumber.getLocation());

// 获取运营商名称，输出："中国移动"
System.out.println(phoneNumber.getCarrier());
```


