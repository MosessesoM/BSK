package models;

public interface Cipher {

//    Zakodowuje dane hasło
    public String encryption(String input);

//    Odkodowuje dane hasło
    public String decryption(String input);
}
