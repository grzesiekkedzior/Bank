package com.server.shop;

import com.bank.Bank;

import java.io.IOException;
import java.net.Socket;

public class ShopContext {
    ShopOperation shopOperation;

    public ShopContext(ShopOperation shopOperation) {
        this.shopOperation = shopOperation;
    }

    public void showShop(Bank bank, Socket socket) throws IOException, ClassNotFoundException {
        shopOperation.operaton(bank, socket);
    }
}
