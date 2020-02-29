package com.server.atm;

import com.bank.Bank;
import com.transfer.AccountBalance;
import com.transfer.Transaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AtmOperationBalance implements AtmOperation {

    @Override
    public void operaton(Bank bank, Socket socket) throws ClassNotFoundException, IOException {
        String surname = getSurname(socket);
        int pin = getPin(socket);
        sendObject(bank, socket, surname, pin);
        return;
    }

    private void sendObject(Bank bank, Socket socket, String surname, int pin) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Transaction accountBalance = new AccountBalance(bank, surname, pin);
        objectOutputStream.writeObject(accountBalance);
    }

    private int getPin(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectPin = new ObjectInputStream(socket.getInputStream());
        return (int) (Integer) objectPin.readObject();
    }

    private String getSurname(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectSurname = new ObjectInputStream(socket.getInputStream());
        return (String) objectSurname.readObject();
    }
}
