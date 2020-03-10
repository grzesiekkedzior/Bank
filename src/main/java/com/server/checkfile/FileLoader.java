package com.server.checkfile;

import java.io.*;

public class FileLoader {
    public BufferedReader readFile(String path) throws IOException {
        File file = new File(path);
        return new BufferedReader(new FileReader(file));
    }

    public BufferedWriter writeFile(String path) throws IOException {
        File file = new File(path);
        return new BufferedWriter(new FileWriter(file));
    }
}
