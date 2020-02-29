package com.server.transfermoney;

import com.bank.Bank;

import java.io.IOException;
import java.net.Socket;

public interface TransferOperation {
    void operaton(Bank bank, Socket socket) throws ClassNotFoundException, IOException;
}
