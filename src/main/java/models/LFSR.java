package models;

import java.util.Random;

public class LFSR implements Generator {
    public String key;

    public LFSR(String key) {
        this.key = key;
    }


    @Override
    public String generate(String binary_input) {
        int[] line = firstline();
        StringBuilder random_key=new StringBuilder();
        for (int i=0;i<binary_input.length();i++){
            random_key.append(Character.forDigit(result(line),10));
            line=shift(line);
        }
        return random_key.toString();
    }

    public int[] firstline() {
        int[] binarykey = keyToBinary();
        Random random = new Random();
        int count = binarykey.length;
        int[] line=new int[count];
        for (int i=0;i<count;i++){
            line[i]=random.nextInt(2);
        }
        return line;
    }

    private int[] keyToBinary(){
        int count=0;
        key=key.replaceAll("\\s+","");
        for (int i=key.length()-1;i>0;i--){
            if (key.charAt(i)=='^'){
                count=Integer.parseInt(key.substring(i+1));
                break;
            }
        }
        if (count==0){
            count=1;
        }
        int[] binary_key =new int[count];
        for (int i=0;i<count;i++){
            binary_key[i]=0;
        }
        for (int i=0;i<key.length();i++){
            if (key.charAt(i)=='x'){
                if (key.charAt(i+1)=='^'){
                    binary_key[Character.getNumericValue(key.charAt(i+2))-1]=1;
                } else {
                    binary_key[0]=1;
                }
            }
        }
        return binary_key;
    }

    public int result(int[] line){
        int[] binary_key = keyToBinary();
        int count = binary_key.length;
        return line[count-1];
    }

    public int[] shift(int[] line){
        int[] binary_key = keyToBinary();
        int count = binary_key.length;
        int result =2;
        int[] next_line = new int[count];
        for (int i=0;i<count;i++){
            if (binary_key[i]==1){
                if (result==2){
                    result=line[i];
                } else {
                    result=result^line[i];
                }
            }
        }
        next_line[0]=result;
        for (int i=1;i<count;i++){
            next_line[i]=line[i-1];
        }
        return next_line;
    }

}
