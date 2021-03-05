package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public String path;

//    Zwraca ścieżkę do pliku
    public String getPath() {
        return path;
    }

//    Pobiera ścieżkę do pliku
    public void setPath(String path) {
        this.path = path;
    }

//    Odczytuje dane z pliku
    public String readFile() throws FileNotFoundException {
        File file = new File(path);
        Scanner reader = new Scanner(file);
        StringBuilder data= new StringBuilder();
        while (reader.hasNextLine()){
            data.append(reader.nextLine());
        }
        reader.close();
        return data.toString();
    }

//    Zapisuje dane do pliku
    public String writeFile(String data) throws IOException {
        File file = new File(System.getProperty("user.home"),"Desktop\\output.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(data);
        fileWriter.close();
        return null;
    }
}
