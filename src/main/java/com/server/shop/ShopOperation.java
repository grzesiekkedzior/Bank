package com.server.shop;

import com.bank.Bank;

import java.io.IOException;
import java.net.Socket;

public interface ShopOperation {
    void operaton(Bank bank, Socket socket) throws ClassNotFoundException, IOException;
}
