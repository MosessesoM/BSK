package models;

public class Caesar implements Cipher {
    public String key;

    public Caesar(String key) {
        this.key = key;
    }

    @Override
    public String encryption(String input) {
        int key_num=Integer.parseInt(key);
        StringBuilder encrypted = new StringBuilder();
        for (int i=0;i<input.length();i++){
            encrypted.append(Character.toChars(((input.charAt(i)-65)+key_num)%26+65));
        }
        return encrypted.toString();
    }

    @Override
    public String decryption(String input) {
        int key_num=Integer.parseInt(key);
        StringBuilder decrypted = new StringBuilder();
        for (int i=0;i<input.length();i++){
            decrypted.append(Character.toChars(((input.charAt(i)-65)+(26-key_num))%26+65));
        }
        return decrypted.toString();
    }
}
