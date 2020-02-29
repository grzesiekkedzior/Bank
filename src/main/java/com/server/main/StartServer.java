package com.server.main;

import com.company.Millenium;
import com.server.atm.AtmContext;
import com.server.shop.ShopContext;
import com.server.transfermoney.TransferContext;
import com.transfer.CreateClient;
import com.transfer.Transaction;

import java.io.IOException;

public class StartServer {
    private Millenium millenium = new Millenium();

    public void run() throws IOException {
        Transaction transaction = new CreateClient(millenium,"Jan", "Kowalski", 1000, 1111);
        transaction.execute();
        Transaction transaction2 = new CreateClient(millenium,"Grzegorz", "Beza", 1000, 2222);
        transaction2.execute();
    }

    public Millenium getMillenium() {
        return millenium;
    }
}
