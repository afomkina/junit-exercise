package ru.practicum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderCalculatorLegacyTest {

    private OrderCalculator calculator;

    @Before
    public void setUp() {
        calculator = new OrderCalculator();
    }

    @Test
    public void calculateTotalPriceShouldReturnCorrectTotalPrice() {
        int result = calculator.calculateTotalPrice(500, 2);

        Assert.assertEquals(1000, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateDiscountPercentShouldThrowExceptionForUnknownPromoCode() {
        calculator.calculateDiscountPercent("UNKNOWN");
    }
}
