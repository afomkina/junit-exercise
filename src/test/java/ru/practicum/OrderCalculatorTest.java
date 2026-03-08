package ru.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderCalculatorTest {

    private OrderCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new OrderCalculator();
    }

    @Test
    @DisplayName("Метод calculateTotalPrice должен выбрасывать исключение, если количество товаров равно нулю")
    void calculateTotalPriceShouldThrowExceptionWhenQuantityIsZero() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateTotalPrice(1000, 0));
    }

    @Test
    @DisplayName("Метод calculateTotalPrice должен выбрасывать исключение, если цена товара отрицательная")
    void calculateTotalPriceShouldThrowExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateTotalPrice(-100, 2));
    }

    @ParameterizedTest
    @CsvSource({
            "3000",
            "5000"
    })
    @DisplayName("Метод isFreeDelivery должен возвращать true, если доставка бесплатная")
    void isFreeDeliveryShouldReturnTrue(int totalPrice) {
        boolean actual = calculator.isFreeDelivery(totalPrice);

        assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource({
            "2999",
            "1000"
    })
    @DisplayName("Метод isFreeDelivery должен возвращать false, если доставка не бесплатная")
    void isFreeDeliveryShouldReturnFalse(int totalPrice) {
        boolean actual = calculator.isFreeDelivery(totalPrice);

        assertFalse(actual);
    }

    @ParameterizedTest
    @CsvSource({
            "NEW10, 10",
            "SALE15, 15",
            "VIP20, 20"
    })
    @DisplayName("Метод calculateDiscountPercent должен возвращать корректную скидку по известному промокоду")
    void calculateDiscountPercentShouldReturnExpectedDiscount(String promoCode, int expectedDiscount) {
        int actualDiscount = calculator.calculateDiscountPercent(promoCode);

        assertEquals(expectedDiscount, actualDiscount);
    }

    @ParameterizedTest
    @CsvSource({
            "5000, true, EXPRESS",
            "5000, false, FREE",
            "1000, false, PAID",
            "1000, true, EXPRESS"
    })
    @DisplayName("Метод getDeliveryType должен возвращать корректный тип доставки")
    void getDeliveryTypeShouldReturnExpectedDeliveryType(int totalPrice, boolean expressDelivery, String expectedType) {
        String actualType = calculator.getDeliveryType(totalPrice, expressDelivery);

        assertEquals(expectedType, actualType);
    }

    @Test
    @DisplayName("Метод createOrderSummary должен корректно формировать сводку заказа")
    void createOrderSummaryShouldReturnCorrectSummary() {
        String summary = calculator.createOrderSummary("Анна", 3500, true);

        assertAll(
                () -> assertNotNull(summary),
                () -> assertTrue(summary.contains("Анна")),
                () -> assertTrue(summary.contains("3500")),
                () -> assertTrue(summary.contains("бесплатная"))
        );
    }
}
