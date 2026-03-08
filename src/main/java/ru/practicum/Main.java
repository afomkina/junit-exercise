package ru.practicum;

public class Main {
    public static void main(String[] args) {
        OrderCalculator calculator = new OrderCalculator();

        int totalPrice = calculator.calculateTotalPrice(1000, 3);
        boolean freeDelivery = calculator.isFreeDelivery(totalPrice);
        int discountPercent = calculator.calculateDiscountPercent("NEW10");
        String deliveryType = calculator.getDeliveryType(totalPrice, false);
        String summary = calculator.createOrderSummary("Анна", totalPrice, freeDelivery);

        System.out.println("Итоговая сумма: " + totalPrice);
        System.out.println("Бесплатная доставка: " + freeDelivery);
        System.out.println("Скидка: " + discountPercent + "%");
        System.out.println("Тип доставки: " + deliveryType);
        System.out.println("Сводка заказа: " + summary);
    }
}
