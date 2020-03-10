package com.client;

import com.bank.Bank;
import com.transfer.AccountBalance;
import com.transfer.Transaction;
import com.transfer.WithdrawMoney;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Stream;

public class AtmServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Atm atm = new Atm();

        while (true) {
            Socket socket = startConnection();
            sendAtmName(atm, socket);
            System.out.println(atm.menu());
            menu(socket);
        }
    }

    private static void menu(Socket socket) throws IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if (num == 1) {
            putDataToCheckBalanceAccount(socket);
            getAccountBalanceObject(socket);
            getTransactionData(socket);
        } else if (num == 2) {
            putDataToWithdrawMoney(socket);
            getWithdrawMoneyObject(socket);
            getTransactionData(socket);
        }
    }

    private static void getTransactionData(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Bank millenium = (Bank) objectInputStream.readObject();
        millenium.printTable();
    }

    private static void putDataToWithdrawMoney(Socket socket) throws IOException {
        ObjectOutputStream withdrawObject = new ObjectOutputStream(socket.getOutputStream());
        withdrawObject.writeObject("withdraw");
        System.out.println("YOUR SURNAME");

        Scanner s = new Scanner(System.in);
        String surname = s.nextLine();
        ObjectOutputStream objectSurname = new ObjectOutputStream(socket.getOutputStream());
        objectSurname.writeObject(surname);

        System.out.println("MONEY");
        Scanner m = new Scanner(System.in);
        Integer money = m.nextInt();

        ObjectOutputStream objectMoney = new ObjectOutputStream(socket.getOutputStream());
        objectMoney.writeObject(money);
    }

    private static void putDataToCheckBalanceAccount(Socket socket) throws IOException {
        ObjectOutputStream withdrawObject = new ObjectOutputStream(socket.getOutputStream());
        withdrawObject.writeObject("balance");

        System.out.println("YOUR SURNAME");
        Scanner s = new Scanner(System.in);
        String surname = s.nextLine();
        ObjectOutputStream objectSurname = new ObjectOutputStream(socket.getOutputStream());
        objectSurname.writeObject(surname);

        System.out.println("YOUR PIN");
        Scanner p = new Scanner(System.in);
        Integer pin = p.nextInt();
        System.out.println(pin);

        ObjectOutputStream objectPin = new ObjectOutputStream(socket.getOutputStream());
        objectPin.writeObject(pin);
    }

    private static Socket startConnection() throws IOException {
        InetAddress inetAddress = Inet4Address.getLocalHost();
        return new Socket(inetAddress, 59090);
    }

    private static void sendAtmName(Atm atm, Socket socket) throws IOException {
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println(atm.getName());
    }

    private static void getAccountBalanceObject(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Transaction account = (AccountBalance) objectInputStream.readObject();
        account.execute();
    }

    private static void getWithdrawMoneyObject(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Transaction withdrawMoney = (WithdrawMoney) objectInputStream.readObject();
        withdrawMoney.execute();
    }
}
