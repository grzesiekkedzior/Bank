package com.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Atm {
    private static final String GREEN_BOLD = "\033[1;32m";
    private final String name = "atm";

    public String menu() {
        String menu = GREEN_BOLD +
                "\n----------------------------\n" +
                "---- 1. ACCOUNT BALANCE ----\n" +
                "---- 2. WITHDRAW MONEY  ----\n" +
                "----------------------------\n";
        return menu;
    }

    public String getName() {
        return name;
    }
}
