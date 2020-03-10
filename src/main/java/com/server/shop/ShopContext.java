package com.server.shop;

import com.bank.Bank;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;

public class ShopContext {
    ShopOperation shopOperation;

    public ShopContext(Supplier<ShopOperation> shopOperationSupplier) {
        this.shopOperation = shopOperationSupplier.get();
    }

    public void showShop(Bank bank, Socket socket) throws IOException, ClassNotFoundException {
        shopOperation.operaton(bank, socket);
    }
}
