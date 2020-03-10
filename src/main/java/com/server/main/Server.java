package com.server.main;

import com.company.Millenium;
import com.server.atm.AtmOperationBalance;
import com.server.atm.AtmContext;
import com.server.atm.AtmOperationWithdrawMoney;
import com.server.checkfile.ClientChecker;
import com.server.checkfile.FileChecker;
import com.server.shop.ShopContext;
import com.server.shop.ShopProductOperation;
import com.server.transfermoney.TransferContext;
import com.server.transfermoney.TransferMoneyOperation;
import com.transfer.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class doesn't have instance enywhere
 */
public class Server {
    private static final String ATM = "atm";
    private static final String BALANCE = "balance";
    private static final String WITHDRAW = "withdraw";
    private static final String TRANSFER = "transfer";
    private static final String SHOP = "shop";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StartServer startServer = new StartServer();
        startServer.run();

        Millenium millenium = startServer.getMillenium();
        serverOperation(millenium);
        return;
    }

    private static void serverOperation(Millenium millenium) throws IOException, ClassNotFoundException {
        AtmContext atmContext;
        TransferContext transferContext;
        ShopContext shopContext;

        try (var listener = new ServerSocket(59090)) {
            System.out.println("The date server is running...");
            while (true) {
                try (var socket = listener.accept()) {
                    String str = getDataFromClient(socket);

                    if (str.equals(ATM)) {
                        String withdrawValue = clientConcretOperation(socket);
                        if (withdrawValue.equals(BALANCE)) {
                            atmContext = new AtmContext(AtmOperationBalance::new);
                            atmContext.showAtm(millenium, socket);
                        } else if (withdrawValue.equals(WITHDRAW)) {
                            atmContext = new AtmContext(AtmOperationWithdrawMoney::new);
                            atmContext.showAtm(millenium, socket);
                        }
                    } else if (str.equals(TRANSFER)) {
                        transferContext = new TransferContext(TransferMoneyOperation::new);
                        transferContext.showTransfer(millenium, socket);
                    } else if (str.equals(SHOP)) {
                        shopContext = new ShopContext(ShopProductOperation::new);
                        shopContext.showShop(millenium, socket);
                    }
                }
            }
        }
    }

    private static String clientConcretOperation(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream withdrawObject = new ObjectInputStream(socket.getInputStream());
        return (String) withdrawObject.readObject();
    }

    private static String getDataFromClient(Socket socket) throws IOException {
        Scanner in = new Scanner(socket.getInputStream());
        return in.nextLine();
    }
}
