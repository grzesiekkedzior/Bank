package com.server.checkfile;

import com.bank.Bank;
import com.company.Millenium;
import com.transfer.CreateClient;
import com.transfer.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Getter
@Setter
public class ClientChecker {
    private Bank bank;
    private FileChecker fileChecker;
    private String surname;

    public ClientChecker(Bank bank, String surname) {
        this.bank = bank;
        this.fileChecker = new FileChecker(surname);
        this.surname = surname;
    }

    public void searchClientBySurname() throws IOException {
        fileChecker.checkFileNumber();

        String line;
        
        while ((line = fileChecker.getBufferedReader().readLine()) != null) {
            String splitedRecord[] = line.split(" ");
            if (splitedRecord[3].equals(surname) && !isClient()) {
                String name = splitedRecord[2];
                String surname = splitedRecord[3];
                int money = Integer.parseInt(splitedRecord[5]);
                int pin = Integer.parseInt(splitedRecord[0]);

                bank.createClient(name , surname , money , pin);
                break;
            }
        }
        fileChecker.getBufferedReader().close();
    }

    private boolean isClient() {
        return bank.getAccountList().stream()
                .anyMatch(x->x.equals(x.getClient().getSurname().equals(surname)));
    }
}
