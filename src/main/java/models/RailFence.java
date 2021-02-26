package models;

public class RailFence implements Cipher {

    public Integer key;

    public RailFence(Integer key) {
        this.key = key;
    }

//    TODO: Dodać możliwość podawania pliku tekstowego.

    @Override
    public String encryption(String input) {
        StringBuilder[] rows = new StringBuilder[key];
        StringBuilder stringBuilder = new StringBuilder();
        int row = 0;
        boolean check = false;
        for(int i=0;i<key;i++){
            rows[i]=new StringBuilder("");
        }
        for (int i = 0; i < input.length(); i++) {
            rows[row].append(input.charAt(i));
            if (row < key - 1 && !check) {
                row++;
            } else {
                check = true;
                row--;
            }
            if (row == 0) {
                check = false;
            }
        }
        for (int i = 0; i < key; i++) {
            stringBuilder.append(rows[i].toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public String decryption(String input) {
        char[] decrypted = new char[input.length()];
        int n=0;
        boolean check =false;
        for (int i=0;i<key;i++){
            check =true;
            for (int j=i;j<input.length();){
                decrypted[j]=input.charAt(n++);
                if (i==0 || i==key-1){
                    j+=2*(key-1);
                } else if (check){
                    j+=2*(key-i-1);
                    check=!check;
                } else {
                    j+=2*i;
                    check=!check;
                }
            }
        }
        return new String(decrypted);
    }
}
