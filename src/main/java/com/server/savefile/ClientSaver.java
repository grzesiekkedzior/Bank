package com.server.savefile;

import com.bank.Account;
import com.bank.Bank;
import com.server.checkfile.FileChecker;
import lombok.Getter;
import java.io.IOException;

@Getter
public class ClientSaver {
    private Bank bank;
    private FileChecker fileChecker;
    private FileSaver fileSaver;
    private String surname;
    private StringBuilder stringBuilder = new StringBuilder();

    public ClientSaver(Bank bank, String surname) {
        this.bank = bank;
        this.fileChecker = new FileChecker(surname);
        this.fileSaver = new FileSaver(surname);
        this.surname = surname;
    }

    public void updateRecord() throws IOException {
        fileChecker.checkFileNumber();

        String line;
        while ((line = fileChecker.getBufferedReader().readLine()) != null) {
            String splitedRecord[] = line.split(" ");

            if (splitedRecord[3].equals(surname) && !isClient()) {
                Account account = bank.getAccountList().stream()
                        .filter(x -> x.getClient().getSurname().equals(surname))
                        .findFirst().get();
                stringBuilder.append(account.getPin() + " " +
                        account.getNumber() + " " +
                        account.getClient().getName() + " " +
                        account.getClient().getSurname() + " " +
                        account.getDate() + " " +
                        account.getMoney() + "\n");
            } else {
                stringBuilder.append(line + "\n");
            }
        }

        fileChecker.getBufferedReader().close();
        String change = new String(stringBuilder);
        saveClientMoneyToFile(change);
    }

    private void saveClientMoneyToFile(String s) throws IOException {
        fileSaver.checkFileNumber();
        fileSaver.getBufferedWriter().write(s);
        fileSaver.getBufferedWriter().close();
    }

    private boolean isClient() {
        return bank.getAccountList().stream()
                .anyMatch(x->x.equals(x.getClient().getSurname().equals(surname)));
    }
}
