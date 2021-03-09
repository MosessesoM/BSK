package models;

public class Vigenere implements Cipher {
    public String key;

    private int[][] table = new int[26][26];

    public Vigenere(String key) {
        this.key = key;
        setTable();
    }

    private void setTable(){
        for (int i=0;i<26;i++){
            for (int j=0;j<26;j++){
                if (i==0){
                    table[i][j]=65+j;
                } else if (table[i-1][j]!=90){
                    table[i][j]=table[i-1][j]+1;
                } else {
                    table[i][j]=65;
                }
            }
        }
    }

    @Override
    public String encryption(String input) {
        int index=0;
        StringBuilder encrypted = new StringBuilder();
        if (key.length()<input.length()){
            for (int i=0;i<input.length();i++){
                key+=key.charAt(i);
            }
        }
        while (index<input.length()){
            encrypted.append((char)table[(int)input.charAt(index)-65][(int)key.charAt(index)-65]);
            index++;
        }
        return encrypted.toString();
    }

    @Override
    public String decryption(String input) {
        int index=0;
        StringBuilder decrypted = new StringBuilder();
        if (key.length()<input.length()){
            for (int i=0;i<input.length();i++){
                key+=key.charAt(i);
            }
        }
        while (index<input.length()){
            for (int i=0;i<26;i++){
                if (table[key.charAt(index)-65][i]==(int)input.charAt(index)){
                    decrypted.append((char)(i+65));
                }
            }
            index++;
        }
        return decrypted.toString();
    }
}
