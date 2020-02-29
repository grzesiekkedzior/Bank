package com.server.transfermoney;

import com.bank.Bank;

import java.io.IOException;
import java.net.Socket;

public class TransferContext {
    TransferOperation transferOperation;

    public TransferContext(TransferOperation transferOperation) {
        this.transferOperation = transferOperation;
    }

    public void showTransfer(Bank bank, Socket socket) throws IOException, ClassNotFoundException {
        transferOperation.operaton(bank, socket);
    }

}
