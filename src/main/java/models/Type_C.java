package models;

public class Type_C extends Matrix {
    public Type_C(String key) {
        super(key);
    }

    @Override
    public int getKeyNumber(int j) {
        return key.charAt(j) - 65;
    }

    @Override
    public String encryption(String input) {
        key=key.replaceAll("-","");
        input=input.replaceAll("\\s+","");
        int columns = key.length();
        int[] key_numeric =new int[columns];
        StringBuilder[] matrix = new StringBuilder[key.length()];
        for (int i=0;i<key.length();i++){
            matrix[i]=new StringBuilder("");
        }
        int index=0;
        for (int i=0;i<26;i++){
            for (int j=0;j<columns;j++){
                if (i==getKeyNumber(j)){
                    key_numeric[j]=index;
                    index++;
                }
            }
        }
        index=0;
        int letter =0;
        while (index<columns){
            for (int i = 0; i < key.length(); i++) {
                if (key_numeric[i] == index) {
                    for (int j = 0; j <= i && letter<input.length(); j++) {
                        matrix[j].append(input.charAt(letter));
                        letter++;
                    }
                    for (int j=i+1;j<key.length();j++){
                        matrix[j].append("");
                    }
                }
            }
            index++;
        }
        index=0;
        StringBuilder encrypted = new StringBuilder();
        while (index<key.length()){
            for (int i = 0; i < key.length(); i++) {
                if (key_numeric[i] == index) {
                    encrypted.append(matrix[i]);
                }
            }
            encrypted.append(" ");
            index++;
        }
        return encrypted.toString();
    }

    @Override
    public String decryption(String input) {
        key=key.replaceAll("-","");
        int columns = key.length();
        int[] key_numeric =new int[columns];
        int index=0;
        while (input.charAt(index)!=' '){
            index++;
        }
        input=input.replaceAll("\\s+","");
        int rows=index;
        index=0;
        char[][] matrix = new char[rows][columns];
        for (int i=0;i<26;i++){
            for (int j=0;j<columns;j++){
                if (i==getKeyNumber(j)){
                    key_numeric[j]=index;
                    index++;
                }
            }
        }
        index=0;
        int letter =0;
        int count=0;
        int ile=0;
        int index2=0;
        while (index<columns){
            for (int i = 0; i < columns; i++) {
                if (key_numeric[i] == index) {
                    for (int j=0;j<rows && letter<input.length();j++){
                        while (index2<columns){
                            if (index2==j || key_numeric[index2]==j) {
                                matrix[j][i] = input.charAt(letter);
                                letter++;
                            }
                            index2++;
                        }
                    }
                }
            }
            index++;
        }
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        return null;
    }
}
