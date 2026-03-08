package ru.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderCalculatorLegacyTest {

    private OrderCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new OrderCalculator();
    }

    @Test
    @DisplayName("Метод calculateTotalPrice должен корректно рассчитывать общую стоимость заказа")
    void calculateTotalPriceShouldReturnCorrectTotalPrice() {
        int result = calculator.calculateTotalPrice(500, 2);

        assertEquals(1000, result);
    }

    @Test
    @DisplayName("Метод calculateDiscountPercent должен выбрасывать исключение для неизвестного промокода")
    void calculateDiscountPercentShouldThrowExceptionForUnknownPromoCode() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateDiscountPercent("UNKNOWN"));
    }
}
