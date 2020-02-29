package com.client;

public class Shop {
    private static final String GREEN_BOLD = "\033[1;32m";
    private final String name = "shop";
    private final int BOOK = 25;
    private final int XBOX = 30;

    public String menu() {
        String menu = GREEN_BOLD +
                "\n--------------------------------\n" +
                "----------- BUY PRODUCT -----------\n" +
                "----------- 1. BOOK 25$ -----------\n" +
                "----------- 2. XBOX 30$ -----------\n" +
                "-----------------------------------\n";
        return menu;
    }

    public String getName() {
        return name;
    }

    public int getBOOK() {
        return BOOK;
    }

    public int getXBOX() {
        return XBOX;
    }
}
