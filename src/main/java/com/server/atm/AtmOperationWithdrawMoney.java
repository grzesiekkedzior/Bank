package com.server.atm;

import com.bank.Bank;
import com.transfer.Transaction;
import com.transfer.WithdrawMoney;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AtmOperationWithdrawMoney implements AtmOperation {

    @Override
    public void operaton(Bank bank, Socket socket) throws ClassNotFoundException, IOException {
        String surname = getSurname(socket);
        int money = getMoney(socket);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        senObject(bank, surname, money, objectOutputStream);
    }

    private void senObject(Bank bank, String surname, int money, ObjectOutputStream objectOutputStream) throws IOException {
        Transaction withdrawMoney = new WithdrawMoney(bank, surname, money);
        withdrawMoney.execute();
        objectOutputStream.writeObject(withdrawMoney);
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
