package com.client;

public class Transfer {
    private static final String GREEN_BOLD = "\033[1;32m";
    private final String name = "transfer";

    public String menu() {
        String menu = GREEN_BOLD +
                "\n-----------------------------\n" +
                "-------- 1. TRANSFER --------\n" +
                "-----------------------------\n";
        return menu;
    }

    public String getName() {
        return name;
    }
}
