package com.server.shop;

import com.bank.Bank;
import com.server.checkfile.ClientChecker;
import com.server.savefile.ClientSaver;
import com.transfer.Transaction;
import com.transfer.TransferMoney;
import com.transfer.WithdrawMoney;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ShopProductOperation implements ShopOperation {
    private ClientSaver clientSaver;

    @Override
    public void operaton(Bank bank, Socket socket) throws ClassNotFoundException, IOException {
        String surname = getSurname(socket);
        loadClientIfExist(bank, surname);
        int money = getMoney(socket);
        sendObject(bank, socket, surname, money);
        saveClientIfExist(bank, surname);
        return;
    }

    private void loadClientIfExist(Bank bank, String surname) throws IOException {
        ClientChecker clientChecker = new ClientChecker(bank, surname);
        clientChecker.searchClientBySurname();
    }

    private void saveClientIfExist(Bank bank, String surname) throws IOException {
        clientSaver = new ClientSaver(bank, surname);
        clientSaver.updateRecord();
    }

    private void sendObject(Bank bank, Socket socket, String surname, int money) throws IOException {
        Transaction withdrawMoney = new WithdrawMoney(bank, surname, money);
        withdrawMoney.execute();
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream2.writeObject(withdrawMoney);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(bank);
    }

    private int getMoney(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectMoney = new ObjectInputStream(socket.getInputStream());
        return (int) (Integer) objectMoney.readObject();
    }

    private String getSurname(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectSurname = new ObjectInputStream(socket.getInputStream());
        return (String) objectSurname.readObject();
    }
}
