package com.server.atm;

import com.bank.Bank;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;

public class AtmContext {
    AtmOperation atmOperation;

    public AtmContext(Supplier<AtmOperation> atmOperationSupplier) {
        this.atmOperation = atmOperationSupplier.get();
    }

    public void showAtm(Bank bank, Socket socket) throws IOException, ClassNotFoundException {
        atmOperation.operaton(bank, socket);
    }
}
