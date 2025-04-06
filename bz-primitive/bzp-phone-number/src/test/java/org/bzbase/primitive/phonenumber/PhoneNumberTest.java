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
 * 电话号码测试
 *
 * @author legendjw
 */
class PhoneNumberTest {
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
            "01023456789",
            "+861023456789",
            "+86 10 2345 6789",
            "010-2345-6789",
            "021-3456-7890",
            "0755-2345-6789",
    })
    void test_some_valid_phone_number(String phoneNumber) {
        assertThatNoException().isThrownBy(() -> PhoneNumber.of(phoneNumber));
        assertThatNoException().isThrownBy(() -> PhoneNumber.ofStrict(phoneNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "  ",
            "2345",
            "abcd",
            "a18712345678",
    })
    void test_some_invalid_phone_number(String phoneNumber) {
        assertThatExceptionOfType(InvalidPhoneNumberException.class)
                .isThrownBy(() -> PhoneNumber.of(phoneNumber))
                .withMessageContaining("无效的电话号码")
                .hasFieldOrPropertyWithValue("phoneNumber", phoneNumber);

        assertThatExceptionOfType(InvalidPhoneNumberException.class)
                .isThrownBy(() -> PhoneNumber.ofStrict(phoneNumber))
                .withMessageContaining("无效的电话号码")
                .hasFieldOrPropertyWithValue("phoneNumber", phoneNumber);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123 4567 8901",
            "9999-2345-6789",
    })
    void test_some_normal_length_but_invalid_phone_number(String phoneNumber) {
        assertThatNoException().isThrownBy(() -> PhoneNumber.of(phoneNumber));
        assertThatExceptionOfType(InvalidPhoneNumberException.class).isThrownBy(() -> PhoneNumber.ofStrict(phoneNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+8618795951234",
            "+86 187 9595 1234",
            "18795951234",
            "187 9595 1234",
    })
    void test_new_mobile_phone_number(String input) {
        PhoneNumber phoneNumber1 = PhoneNumber.of(input);
        PhoneNumber phoneNumber2 = PhoneNumber.ofStrict(input);

        ThrowingConsumer<PhoneNumber> assertPhoneNumber = p -> {
            assertThat(p.toE164Format()).isEqualTo("+8618795951234");
            assertThat(p.toReadableE164Format()).isEqualTo("+86 187 9595 1234");
            assertThat(p.toNationalFormat()).isEqualTo("18795951234");
            assertThat(p.toReadableNationalFormat()).isEqualTo("187 9595 1234");
            assertThat(p.toRFC3966Format()).isEqualTo("tel:+86-187-9595-1234");
            assertThat(p.getLocation()).isEqualTo("江苏省南京市");
            assertThat(p.getCarrier()).isEqualTo("中国移动");
        };

        assertThat(phoneNumber1).satisfies(assertPhoneNumber);
        assertThat(phoneNumber2).satisfies(assertPhoneNumber);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+862134567890",
            "+86 21-3456-7890",
            "02134567890",
            "021-3456-7890",
    })
    void test_new_fixed_line_phone_number(String input) {
        PhoneNumber phoneNumber1 = PhoneNumber.of(input);
        PhoneNumber phoneNumber2 = PhoneNumber.ofStrict(input);

        ThrowingConsumer<PhoneNumber> assertPhoneNumber = p -> {
            assertThat(p.toE164Format()).isEqualTo("+862134567890");
            assertThat(p.toReadableE164Format()).isEqualTo("+86 21 3456 7890");
            assertThat(p.toNationalFormat()).isEqualTo("2134567890");
            assertThat(p.toReadableNationalFormat()).isEqualTo("021 3456 7890");
            assertThat(p.getLocation()).isEqualTo("上海市");
        };

        assertThat(phoneNumber1).satisfies(assertPhoneNumber);
        assertThat(phoneNumber2).satisfies(assertPhoneNumber);
    }

    @Test
    void test_phone_number_value() {
        PhoneNumber phoneNumber1 = PhoneNumber.of("+8618795951234");
        assertThat(phoneNumber1.getNumber()).isEqualTo("+8618795951234");

        PhoneNumber phoneNumber2 = PhoneNumber.of("18795951234");
        assertThat(phoneNumber2.getNumber()).isEqualTo("18795951234");

        PhoneNumber phoneNumber3 = PhoneNumber.of(" 021-3456-7890 ");
        assertThat(phoneNumber3.getNumber()).isEqualTo("2134567890");

        PhoneNumber phoneNumber4 = PhoneNumber.of("+86 021-3456-7890 ");
        assertThat(phoneNumber4.getNumber()).isEqualTo("+862134567890");
    }

    @Test
    void test_phone_number_equal() {
        PhoneNumber samePhoneNumber1 = PhoneNumber.of("18795951234");
        PhoneNumber samePhoneNumber2 = PhoneNumber.of("18795951234");
        PhoneNumber phoneNumber3 = PhoneNumber.of("18795952345");

        assertThat(samePhoneNumber1.equals(samePhoneNumber2)).isTrue();
        assertThat(samePhoneNumber1.equals(phoneNumber3)).isFalse();
    }

    @Test
    void test_phone_number_type() {
        PhoneNumber mobileNumber = PhoneNumber.of("18795951234");
        assertThat(mobileNumber.getType().equals(PhoneNumberType.MOBILE)).isTrue();

        PhoneNumber fixedLineNumber = PhoneNumber.of("021-3456-7890");
        assertThat(fixedLineNumber.getType().equals(PhoneNumberType.FIXED_LINE)).isTrue();
    }
}
