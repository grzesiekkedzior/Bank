package com.server.transfermoney;

import com.bank.Bank;
import com.server.checkfile.ClientChecker;
import com.server.savefile.ClientSaver;
import com.transfer.Transaction;
import com.transfer.TransferMoney;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TransferMoneyOperation implements TransferOperation {
    private ClientChecker clientChecker;
    private ClientSaver clientSaver;

    @Override
    public void operaton(Bank bank, Socket socket) throws ClassNotFoundException, IOException {
        String surname = getOwnerSurname(socket);
        int money = getMoney(socket);
        String clientSur = getClientSurname(socket);

        loadClientIfExist(bank, surname);
        loadClientIfExist(bank, clientSur);
        sendObject(bank, socket, surname, money, clientSur);
        saveClientIfExist(bank, surname);
        saveClientIfExist(bank, clientSur);
        bank.printTable();
        return;
    }

    private void sendObject(Bank bank, Socket socket, String surname, int money, String clientSur) throws IOException {
        Transaction transferMoney = new TransferMoney(bank, surname, money, clientSur);
        transferMoney.execute();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(bank);
    }

    private void loadClientIfExist(Bank bank, String surname) throws IOException {
        clientChecker = new ClientChecker(bank, surname);
        clientChecker.searchClientBySurname();
    }

    private void saveClientIfExist(Bank bank, String surname) throws IOException {
        clientSaver = new ClientSaver(bank, surname);
        clientSaver.updateRecord();
    }

    private int getMoney(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectMoney = new ObjectInputStream(socket.getInputStream());
        return (int) (Integer) objectMoney.readObject();
    }

    private String getClientSurname(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream clientSurname = new ObjectInputStream(socket.getInputStream());
        return (String) clientSurname.readObject();
    }

    private String getOwnerSurname(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectSurname = new ObjectInputStream(socket.getInputStream());
        return (String) objectSurname.readObject();
    }
}
