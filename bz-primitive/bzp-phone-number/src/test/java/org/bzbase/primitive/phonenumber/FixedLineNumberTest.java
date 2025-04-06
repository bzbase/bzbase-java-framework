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
 * 固定电话号码测试
 *
 * @author legendjw
 */
class FixedLineNumberTest {
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
            "01023456789",
            "+861023456789",
            "+86 10 2345 6789",
            "010-2345-6789",
            "021-3456-7890",
            "0755-2345-6789",
    })
    void test_some_valid_fixed_line_number(String fixedLineNumber) {
        assertThatNoException().isThrownBy(() -> FixedLineNumber.of(fixedLineNumber));
        assertThatNoException().isThrownBy(() -> FixedLineNumber.ofStrict(fixedLineNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "  ",
            "2345",
            "abcd",
            "a18712345678",
    })
    void test_some_invalid_fixed_line_number(String fixedLineNumber) {
        assertThatExceptionOfType(InvalidPhoneNumberException.class)
                .isThrownBy(() -> FixedLineNumber.of(fixedLineNumber))
                .withMessageContaining("无效的固定电话号码")
                .hasFieldOrPropertyWithValue("phoneNumber", fixedLineNumber)
                .hasFieldOrPropertyWithValue("phoneNumberType", PhoneNumberType.FIXED_LINE);

        assertThatExceptionOfType(InvalidPhoneNumberException.class)
                .isThrownBy(() -> FixedLineNumber.ofStrict(fixedLineNumber))
                .withMessageContaining("无效的固定电话号码")
                .hasFieldOrPropertyWithValue("phoneNumber", fixedLineNumber)
                .hasFieldOrPropertyWithValue("phoneNumberType", PhoneNumberType.FIXED_LINE);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123 4567 8901",
            "000 4567 8901",
    })
    void test_some_normal_length_but_invalid_fixed_line_number(String fixedLineNumber) {
        assertThatNoException().isThrownBy(() -> FixedLineNumber.of(fixedLineNumber));
        assertThatExceptionOfType(InvalidPhoneNumberException.class).isThrownBy(() -> FixedLineNumber.ofStrict(fixedLineNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+862134567890",
            "+86 21-3456-7890",
            "02134567890",
            "021-3456-7890",
    })
    void test_new_fixed_line_number(String input) {
        FixedLineNumber fixedLineNumber1 = FixedLineNumber.of(input);
        FixedLineNumber fixedLineNumber2 = FixedLineNumber.ofStrict(input);

        ThrowingConsumer<FixedLineNumber> assertFixedLineNumber = p -> {
            assertThat(p.toE164Format()).isEqualTo("+862134567890");
            assertThat(p.toReadableE164Format()).isEqualTo("+86 21 3456 7890");
            assertThat(p.toNationalFormat()).isEqualTo("2134567890");
            assertThat(p.toReadableNationalFormat()).isEqualTo("021 3456 7890");
            assertThat(p.getLocation()).isEqualTo("上海市");
        };

        assertThat(fixedLineNumber1).satisfies(assertFixedLineNumber);
        assertThat(fixedLineNumber2).satisfies(assertFixedLineNumber);
    }

    @Test
    void test_fixed_line_number_value() {
        FixedLineNumber fixedLineNumber1 = FixedLineNumber.of("021-3456-7890");
        assertThat(fixedLineNumber1.getNumber()).isEqualTo("2134567890");

        FixedLineNumber fixedLineNumber2 = FixedLineNumber.of("+86 021-3456-7890 ");
        assertThat(fixedLineNumber2.getNumber()).isEqualTo("+862134567890");
    }

    @Test
    void test_fixed_line_number_equal() {
        FixedLineNumber sameFixedLineNumber1 = FixedLineNumber.of("021-3456-7890");
        FixedLineNumber sameFixedLineNumber2 = FixedLineNumber.of("021-3456-7890");
        FixedLineNumber fixedLineNumber3 = FixedLineNumber.of("021-1234-1234");

        assertThat(sameFixedLineNumber1.equals(sameFixedLineNumber2)).isTrue();
        assertThat(sameFixedLineNumber1.equals(fixedLineNumber3)).isFalse();
    }
}
