package com.server.checkfile;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;

@Getter
@Setter
public class FileChecker {
    private String name;
    private BufferedReader bufferedReader;

    public FileChecker(String name) {
        this.name = name;
    }

    private final Character[] data1 = {'A', 'B', 'C', 'D', 'E'};
    private final Character[] data2 = {'F', 'G', 'H', 'I', 'J'};
    private final Character[] data3 = {'K', 'L', 'M', 'N', 'O'};
    private final Character[] data4 = {'P', 'R', 'S', 'T', 'U'};
    private final Character[] data5 = {'W', 'X', 'Y', 'Z', 'Ż'};

    public void checkFileNumber() throws IOException {
        for (int i = 0; i < data1.length; i++) {
            if (name.startsWith(data1[i].toString())) {
                FileLoader fileLoader = new FileLoader();
                bufferedReader = fileLoader.readFile("src/main/resources/data1");
            } else if (name.startsWith(data2[i].toString())) {
                FileLoader fileLoader = new FileLoader();
                bufferedReader = fileLoader.readFile("src/main/resources/data2");
            } else if (name.startsWith(data3[i].toString())) {
                FileLoader fileLoader = new FileLoader();
                bufferedReader = fileLoader.readFile("src/main/resources/data3");
            } else if (name.startsWith(data4[i].toString())) {
                FileLoader fileLoader = new FileLoader();
                bufferedReader = fileLoader.readFile("src/main/resources/data4");
            } else if (name.startsWith(data5[i].toString())) {
                FileLoader fileLoader = new FileLoader();
                bufferedReader = fileLoader.readFile("src/main/resources/data5");
            }
        }
    }
}
