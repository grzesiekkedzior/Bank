package com.server.savefile;

import com.server.checkfile.FileLoader;
import lombok.Getter;
import java.io.BufferedWriter;
import java.io.IOException;

@Getter
public class FileSaver {
    private String name;
    private BufferedWriter  bufferedWriter;

    public FileSaver(String name) {
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
                bufferedWriter = fileLoader.writeFile("src/main/resources/data1");
            } else if (name.startsWith(data2[i].toString())) {
                FileLoader fileLoader = new FileLoader();
                bufferedWriter = fileLoader.writeFile("src/main/resources/data2");
            } else if (name.startsWith(data3[i].toString())) {
                FileLoader fileLoader = new FileLoader();
                bufferedWriter = fileLoader.writeFile("src/main/resources/data3");
            } else if (name.startsWith(data4[i].toString())) {
                FileLoader fileLoader = new FileLoader();
                bufferedWriter = fileLoader.writeFile("src/main/resources/data4");
            } else if (name.startsWith(data5[i].toString())) {
                FileLoader fileLoader = new FileLoader();
                bufferedWriter = fileLoader.writeFile("src/main/resources/data5");
            }
        }
    }
}
