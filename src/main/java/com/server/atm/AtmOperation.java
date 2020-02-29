package com.server.atm;

import com.bank.Bank;

import java.io.IOException;
import java.net.Socket;

public interface AtmOperation {
    void operaton(Bank bank, Socket socket) throws ClassNotFoundException, IOException;
}
