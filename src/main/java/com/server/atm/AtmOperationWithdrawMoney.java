package com.server.atm;

import com.bank.Bank;
import com.server.checkfile.ClientChecker;
import com.server.savefile.ClientSaver;
import com.transfer.Transaction;
import com.transfer.WithdrawMoney;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AtmOperationWithdrawMoney implements AtmOperation {
    private ClientChecker clientChecker;
    private ClientSaver clientSaver;

    @Override
    public void operaton(Bank bank, Socket socket) throws ClassNotFoundException, IOException {
        String surname = getSurname(socket);
        loadClientIfExist(bank, surname);
        int money = getMoney(socket);


        senObject(bank, surname, money, socket);
        saveClientIfExist(bank, surname);
    }

    private void loadClientIfExist(Bank bank, String surname) throws IOException {
        ClientChecker clientChecker = new ClientChecker(bank, surname);
        clientChecker.searchClientBySurname();
    }

    private void saveClientIfExist(Bank bank, String surname) throws IOException {
        clientSaver = new ClientSaver(bank, surname);
        clientSaver.updateRecord();
    }

    private void senObject(Bank bank, String surname, int money, Socket socket) throws IOException {
        Transaction withdrawMoney = new WithdrawMoney(bank, surname, money);
        withdrawMoney.execute();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(withdrawMoney);
        ObjectOutputStream bankOutput = new ObjectOutputStream(socket.getOutputStream());
        bankOutput.writeObject(bank);
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
