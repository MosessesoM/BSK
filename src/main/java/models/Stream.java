package models;

import java.util.Arrays;

public class Stream implements Cipher {

    public String key;

    public String binary_key;

    public Stream(String key) {
        this.key = key;
    }

    @Override
    public String encryption(String input) {
        StringBuilder binary_input= new StringBuilder();
        StringBuilder encrypted = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i=0;i<input.length();i++){
            binary_input.append(String.format("%8s",Integer.toBinaryString(chars[i])).replaceAll(" ","0"));
        }
        LFSR lfsr = new LFSR(key);
        binary_key= lfsr.generate(binary_input.toString());
        for (int i=0;i<binary_key.length();i++){
            if (binary_input.charAt(i)==binary_key.charAt(i)){
                encrypted.append('0');
            } else {
                encrypted.append('1');
            }
        }
        return encrypted.toString();
    }

    @Override
    public String decryption(String input) {
        StringBuilder decrypted= new StringBuilder();
        StringBuilder binary_output=new StringBuilder();
        for (int i=0;i<binary_key.length();i++){
            if (input.charAt(i)==binary_key.charAt(i)){
                binary_output.append('0');
            } else {
                binary_output.append('1');
            }
        }
        Arrays.stream(
                binary_output.toString().split("(?<=\\G.{8})")
        ).forEach(s ->
                decrypted.append((char) Integer.parseInt(s, 2))
        );
        return decrypted.toString();
    }

//    Zwraca klucz binarny
    public String getBinary_key() {
        return binary_key;
    }

//    Ustala wartość klucza binarnego (Trzeba to dać przed wywołaniem decryption)
    public void setBinary_key(String binary_key) {
        this.binary_key = binary_key;
    }
}
