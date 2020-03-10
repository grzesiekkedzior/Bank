package com.server.main;

import com.company.Millenium;
import com.server.atm.AtmContext;
import com.server.shop.ShopContext;
import com.server.transfermoney.TransferContext;
import com.transfer.CreateClient;
import com.transfer.Transaction;
import lombok.Getter;

import java.io.IOException;

@Getter
public class StartServer {
    private Millenium millenium = new Millenium();

    public void run() throws IOException {
//        Transaction transaction = new CreateClient(millenium,"Jan", "Kowalski", 1000, 1111);
//        transaction.execute();
//        Transaction transaction2 = new CreateClient(millenium,"Grzegorz", "Beza", 1000, 2222);
//        transaction2.execute();
//        Transaction transaction3 = new CreateClient(millenium,"Monika", "Hohol", 1000, 3333);
//        transaction3.execute();
//        Transaction transaction4 = new CreateClient(millenium,"Patrycja", "Lipa", 1000, 4444);
//        transaction4.execute();
//        Transaction transaction5 = new CreateClient(millenium,"Henryk", "Kolos", 1000, 55555);
//        transaction5.execute();
    }
}
