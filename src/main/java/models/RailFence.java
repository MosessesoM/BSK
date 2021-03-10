package models;

public class RailFence implements Cipher {

    public String key;

    public RailFence(String key) {
        this.key = key;
    }
    

    @Override
    public String encryption(String input) {
        int key_num=Integer.parseInt(key);
        StringBuilder[] rows = new StringBuilder[key_num];
        StringBuilder stringBuilder = new StringBuilder();
        int row = 0;
        boolean check = false;
        for(int i = 0; i< key_num; i++){
            rows[i]=new StringBuilder("");
        }
        for (int i = 0; i < input.length(); i++) {
            rows[row].append(input.charAt(i));
            if (row < key_num - 1 && !check) {
                row++;
            } else {
                check = true;
                row--;
            }
            if (row == 0) {
                check = false;
            }
        }
        for (int i = 0; i < key_num; i++) {
            stringBuilder.append(rows[i].toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public String decryption(String input) {
        int key_num=Integer.parseInt(key);
        char[] decrypted = new char[input.length()];
        int n=0;
        boolean check =false;
        for (int i = 0; i< key_num; i++){
            check =true;
            for (int j=i;j<input.length();){
                decrypted[j]=input.charAt(n++);
                if (i==0 || i== key_num -1){
                    j+=2*(key_num -1);
                } else if (check){
                    j+=2*(key_num -i-1);
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
