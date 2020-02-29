package com.client;

import com.bank.Bank;
import com.transfer.Transaction;
import com.transfer.WithdrawMoney;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ShopServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Shop shop = new Shop();

        while (true) {
            Socket socket = startConnection();
            sendShopName(shop, socket);
            System.out.println(shop.menu());
            menu(socket);
        }
    }

    private static void menu(Socket socket) throws IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if (num == 1) {
            buyBook(socket);
            getWithdrawMoneyObject(socket);
            getTransactionData(socket);
        } else if (num == 2) {
            buyXbox(socket);
            getWithdrawMoneyObject(socket);
            getTransactionData(socket);
        }
    }

    private static void getWithdrawMoneyObject(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Transaction withdrawMoney = (WithdrawMoney) objectInputStream.readObject();
        withdrawMoney.execute();
    }

    private static void getTransactionData(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Bank millenium = (Bank) objectInputStream.readObject();
        millenium.printTable();
    }

    private static void buyXbox(Socket socket) throws IOException {
        System.out.println("YOUR SURNAME");
        Scanner s = new Scanner(System.in);
        String surname = s.nextLine();
        ObjectOutputStream objectSurname = new ObjectOutputStream(socket.getOutputStream());
        objectSurname.writeObject(surname);

        Integer money = 30;
        ObjectOutputStream objectMoney = new ObjectOutputStream(socket.getOutputStream());
        objectMoney.writeObject(money);
    }

    private static void buyBook(Socket socket) throws IOException {
        System.out.println("YOUR SURNAME");
        Scanner s = new Scanner(System.in);
        String surname = s.nextLine();
        ObjectOutputStream objectSurname = new ObjectOutputStream(socket.getOutputStream());
        objectSurname.writeObject(surname);

        Integer money = 25;
        ObjectOutputStream objectMoney = new ObjectOutputStream(socket.getOutputStream());
        objectMoney.writeObject(money);
    }

    private static void sendShopName(Shop shop, Socket socket) throws IOException {
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println(shop.getName());
    }

    private static Socket startConnection() throws IOException {
        InetAddress inetAddress = Inet4Address.getLocalHost();
        return new Socket(inetAddress, 59090);
    }
}
