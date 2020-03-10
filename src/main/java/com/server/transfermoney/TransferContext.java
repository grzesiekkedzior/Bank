package com.server.transfermoney;

import com.bank.Bank;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;

public class TransferContext {
    TransferOperation transferOperation;

    public TransferContext(Supplier<TransferOperation> transferOperationSupplier) {
        this.transferOperation = transferOperationSupplier.get();
    }

    public void showTransfer(Bank bank, Socket socket) throws IOException, ClassNotFoundException {
        transferOperation.operaton(bank, socket);
    }
}
