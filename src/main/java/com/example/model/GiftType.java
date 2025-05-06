package com.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GiftType {
    BEAR("\uD83E\uDDF8Bear", 15, "bear"),
    HEART("❤\uFE0FHeart", 15, "heart"),
    ROSE("\uD83C\uDF39Rose", 25, "rose"),
    PRESENT("\uD83C\uDF81Gift", 25, "gift"),
    CAKE("\uD83C\uDF82Cake", 50, "cake"),
    FLOWERS("\uD83D\uDC90Flowers", 50, "flowers"),
    ROCKET("\uD83D\uDE80Rocket", 50, "rocket"),
    CHAMPAGNE("\uD83C\uDF7EChampagne", 50, "champagne"),
    TROPHY("\uD83C\uDFC6Trophy", 100, "trophy"),
    RING("\uD83D\uDC8DRing", 100, "ring"),
    DIAMOND("\uD83D\uDC8EDiamond", 100, "diamond")

    ;

    private final String title;
    private final int cost;
    private final String telegramId;

    // Находит подарок по названию и стоимости (регистронезависимо)
    public static GiftType findByTitleAndCost(String title, int cost) {
        for (GiftType gift : values()) {
            if (gift.title.equalsIgnoreCase(title) && gift.cost == cost) {
                return gift;
            }
        }
        throw new IllegalArgumentException("Gift not found: " + title + " (cost: " + cost + ")");
    }

    // Находит подарок по enum-имени (BEAR, HEART и т.д.)
    public static GiftType fromName(String name) {
        for (GiftType gift : values()) {
            if (gift.name().equalsIgnoreCase(name)) {
                return gift;
            }
        }
        throw new IllegalArgumentException("Unknown gift: " + name);
    }
}
