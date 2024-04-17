package org.gonzalez;

import org.junit.jupiter.api.Test;

import static org.gonzalez.CurrencyService.charactersValidityTest;
import static org.junit.jupiter.api.Assertions.*;
class CurrencyServiceTest {

    @Test
    void given_ValidCurrency_when_charactersValidityTest_then_returnTrue() {
        assertTrue(charactersValidityTest("CHF"));
    }

    @Test
    void given_LongCurrency_when_charactersValidityTest_then_throwException() {
        assertThrows(InvalidCurrencyException.class, () -> charactersValidityTest("aa"));
    }

    @Test
    void given_ShortCurrency_when_charactersValidityTest_then_throwException() {
        assertThrows(InvalidCurrencyException.class, () -> charactersValidityTest("aaaa"));
    }

    @Test
    void given_NoCurrency_when_charactersValidityTest_then_throwException() {
        assertThrows(InvalidCurrencyException.class, () -> charactersValidityTest(""));
    }
}