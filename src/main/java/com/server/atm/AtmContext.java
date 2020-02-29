package com.server.atm;

import com.bank.Bank;

import java.io.IOException;
import java.net.Socket;

public class AtmContext {
    AtmOperation atmOperation;

    public AtmContext(AtmOperation atmOperation) {
        this.atmOperation = atmOperation;
    }

    public void showAtm(Bank bank, Socket socket) throws IOException, ClassNotFoundException {
        atmOperation.operaton(bank, socket);
    }
}
