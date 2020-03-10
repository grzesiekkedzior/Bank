package com.client;

import com.bank.Bank;
import com.company.Millenium;
import com.transfer.AccountBalance;
import com.transfer.Transaction;
import com.transfer.TransferMoney;
import com.transfer.WithdrawMoney;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TransferServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Transfer transfer = new Transfer();

        while (true) {
            Socket socket = startConnection();
            sendTransferName(transfer, socket);
            System.out.println(transfer.menu());
            menu(socket);
        }
    }

    private static void menu(Socket socket) throws IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        if (num == 1) {
            putDataToTransferMoney(socket);
            getTransactionData(socket);
        }
    }

    private static void getTransactionData(Socket socket) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("WRONG DATA!");
        }
        Bank millenium = null;
        try {
                millenium = (Bank) objectInputStream.readObject();
                millenium.printTable();
        } catch (IOException e) {
            System.out.println("INCORECT VALUE !!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void putDataToTransferMoney(Socket socket) throws IOException {
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

        System.out.println("CLIENT SURNAME");
        Scanner c = new Scanner(System.in);
        String clientSurname = c.nextLine();
        ObjectOutputStream clientSurnameObject = new ObjectOutputStream(socket.getOutputStream());
        clientSurnameObject.writeObject(clientSurname);
    }

    private static void sendTransferName(Transfer transfer, Socket socket) throws IOException {
            var out = new PrintWriter(socket.getOutputStream(), true);
            out.println(transfer.getName());
    }

    private static Socket startConnection() {
        Socket socket = null;
        InetAddress inetAddress = null;
        try {
            inetAddress = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("CLIENT DOESNT EXIST!");
        }
        try {
            socket = new Socket(inetAddress, 59090);
        } catch (IOException e) {
            System.out.println("CLIENT DOESNT EXIST!");
        }
        return socket;
    }
}
