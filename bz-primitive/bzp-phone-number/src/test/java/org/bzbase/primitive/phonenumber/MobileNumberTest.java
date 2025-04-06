package org.bzbase.primitive.phonenumber;

import org.assertj.core.api.ThrowingConsumer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

/**
 * 手机号码测试
 *
 * @author legendjw
 */
class MobileNumberTest {
    private static Locale defaultLocale;

    @BeforeAll
    public static void setUp() {
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.CHINA);
    }

    @AfterAll
    public static void tearDown() {
        Locale.setDefault(defaultLocale);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "18712345678",
            "+8618712345678",
            "+86 187 1234 5678",
            " 19203161234 ",
            "17404121234",
            "16648591234",
    })
    void test_some_valid_mobile_number(String mobileNumber) {
        assertThatNoException().isThrownBy(() -> MobileNumber.of(mobileNumber));
        assertThatNoException().isThrownBy(() -> MobileNumber.ofStrict(mobileNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "  ",
            "2345",
            "abcd",
            "a18712345678",
            "187123456781",
            "1871234567",
    })
    void test_some_invalid_mobile_number(String mobileNumber) {
        assertThatExceptionOfType(InvalidPhoneNumberException.class)
                .isThrownBy(() -> MobileNumber.of(mobileNumber))
                .withMessageContaining("无效的手机号码")
                .hasFieldOrPropertyWithValue("phoneNumber", mobileNumber)
                .hasFieldOrPropertyWithValue("phoneNumberType", PhoneNumberType.MOBILE);

        assertThatExceptionOfType(InvalidPhoneNumberException.class)
                .isThrownBy(() -> MobileNumber.ofStrict(mobileNumber))
                .withMessageContaining("无效的手机号码")
                .hasFieldOrPropertyWithValue("phoneNumber", mobileNumber)
                .hasFieldOrPropertyWithValue("phoneNumberType", PhoneNumberType.MOBILE);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123 4567 8901",
            "999-2345-6789",
    })
    void test_some_normal_length_but_invalid_mobile_number(String mobileNumber) {
        assertThatNoException().isThrownBy(() -> MobileNumber.of(mobileNumber));
        assertThatExceptionOfType(InvalidPhoneNumberException.class).isThrownBy(() -> MobileNumber.ofStrict(mobileNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+8618795951234",
            "+86 187 9595 1234",
            "18795951234",
            "187 9595 1234",
    })
    void test_new_mobile_number(String input) {
        MobileNumber mobileNumber1 = MobileNumber.of(input);
        MobileNumber mobileNumber2 = MobileNumber.ofStrict(input);

        ThrowingConsumer<MobileNumber> assertMobileNumber = p -> {
            assertThat(p.toE164Format()).isEqualTo("+8618795951234");
            assertThat(p.toReadableE164Format()).isEqualTo("+86 187 9595 1234");
            assertThat(p.toNationalFormat()).isEqualTo("18795951234");
            assertThat(p.toReadableNationalFormat()).isEqualTo("187 9595 1234");
            assertThat(p.getLocation()).isEqualTo("江苏省南京市");
            assertThat(p.getCarrier()).isEqualTo("中国移动");
        };

        assertThat(mobileNumber1).satisfies(assertMobileNumber);
        assertThat(mobileNumber2).satisfies(assertMobileNumber);
    }

    @Test
    void test_mobile_number_value() {
        MobileNumber mobileNumber1 = MobileNumber.of("+8618795951234");
        assertThat(mobileNumber1.getNumber()).isEqualTo("+8618795951234");

        MobileNumber mobileNumber2 = MobileNumber.of("18795951234");
        assertThat(mobileNumber2.getNumber()).isEqualTo("18795951234");
    }

    @Test
    void test_mobile_number_equal() {
        MobileNumber sameMobileNumber1 = MobileNumber.of("18795951234");
        MobileNumber sameMobileNumber2 = MobileNumber.of("18795951234");
        MobileNumber mobileNumber3 = MobileNumber.of("18795952345");

        assertThat(sameMobileNumber1.equals(sameMobileNumber2)).isTrue();
        assertThat(sameMobileNumber1.equals(mobileNumber3)).isFalse();
    }
}
