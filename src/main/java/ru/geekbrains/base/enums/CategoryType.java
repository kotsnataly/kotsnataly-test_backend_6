package ru.geekbrains.base.enums;

import lombok.Getter;

public enum CategoryType {
    FOOD(1, "Food"),
    ELECTRONICS(2, "Electronics"),
    Furniture(3, "Furniture"),
    CASSAT(279, "Cassatt"),
    BUG(16, "Bug"),
    HOME(6, "Home"),
    BERNINI(280, "Bernini"),
    BOTICELLI(281, "Botticelli"),
    WINSLOW_HOMER(13, "Winslow Homer"),
    MONET(195, "Monet"),
    AUTO_AND_INDUSTRIAL(38, "Auto & industrial"),
    RENOIR(45, "Renoir"),
    HANDMADE(666, "Handmade"),
    TEST(38, "Test"),
    DURER(275, "Durer"),
    VINCENT(276, "Vincent"),
    FURNITURE_1(158, "Furniture"),
    EDWARD_HOPPER(277, "Edward Hopper"),
    CHAGALL(278, "Chagall");

    @Getter
    private final Integer id;
    @Getter
    private final String title;

    CategoryType(int id, String title) {
        this.id = id;
        this.title = title;
    }

}
