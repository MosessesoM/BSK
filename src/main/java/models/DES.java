package models;

import java.util.Arrays;

public class DES implements Cipher {

    @Override
    public String encryption(String input) {
        String binary_input=getBinaryInput(input);
        Character[][] block = new Character[8][8];
        int blocks_number=binary_input.length()/64;
        int index=0;
        int index2=0;
        int index3=0;
        for (int i=0;i<blocks_number;i++){
            for (int j=0;j<8;j++){
                for (int k=0;k<8;k++){
                    block[j][k]=binary_input.charAt(index);
                    index++;
                }
            }

//            TODO: Tutaj trzeba wsadzić dalszy algorytm dla danego bloku.
        }
        int lacking=64-binary_input.length()%64;
        String binary_lacking=Integer.toBinaryString(lacking);
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (index<binary_input.length()) {
                    block[i][j] = binary_input.charAt(index);
                    index++;
                } else {
                    if (index2<lacking-binary_lacking.length()){
                        block[i][j]='0';
                        index2++;
                    } else if (index3<lacking-1){
                        block[i][j]=binary_lacking.charAt(index3);
                        index3++;
                    }
                }
            }
        }
        //            TODO: Tutaj trzeba wsadzić dalszy algorytm dla danego bloku.
        return null;
    }

    @Override
    public String decryption(String input) {
        return null;
    }

    private String getBinaryInput(String input){
        StringBuilder binary_input= new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i=0;i<input.length();i++){
            binary_input.append(String.format("%8s",Integer.toBinaryString(chars[i])).replaceAll(" ","0"));
        }
        return binary_input.toString();
    }

    private Character[][] initialPermutation(Character[][] block){
        Character[][] perm_block=new Character[block.length][block[0].length];
        for (int i=0;i<perm_block.length;i++){
            for (int j=0;j<perm_block[0].length;j++){
                if (i<4){
                    perm_block[i][j]=block[7-j][1+i*2];
                } else {
                    perm_block[i][j]=block[7-j][i*2-8];
                }
            }
        }
        return perm_block;
    }

}
