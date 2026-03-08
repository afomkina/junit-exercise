package ru.practicum;

public class OrderCalculator {

    public int calculateTotalPrice(int price, int quantity) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше нуля");
        }
        return price * quantity;
    }

    public boolean isFreeDelivery(int totalPrice) {
        return totalPrice >= 3000;
    }

    public int calculateDiscountPercent(String promoCode) {
        if (promoCode == null || promoCode.isBlank()) {
            throw new IllegalArgumentException("Промокод не должен быть пустым");
        }

        return switch (promoCode) {
            case "NEW10" -> 10;
            case "SALE15" -> 15;
            case "VIP20" -> 20;
            default -> throw new IllegalArgumentException("Неизвестный промокод");
        };
    }

    public String getDeliveryType(int totalPrice, boolean expressDelivery) {
        if (expressDelivery) {
            return "EXPRESS";
        }
        if (totalPrice >= 3000) {
            return "FREE";
        }
        return "PAID";
    }

    public String createOrderSummary(String clientName, int totalPrice, boolean freeDelivery) {
        String delivery = freeDelivery ? "бесплатная" : "платная";
        return "Клиент: " + clientName + ", сумма: " + totalPrice + ", доставка: " + delivery;
    }
}
