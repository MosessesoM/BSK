package models;

public class Type_B extends Matrix {

    public Type_B(String key) {
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
        int rows = getRows(input);
        int columns=key.length();
        char[][] matrix = setMatrix(input, rows,columns);
        int[] key_numeric=new int[columns];
        char[][] decrypted =new char[rows][columns];
        StringBuilder output = new StringBuilder();
        int c =0,m=0,index=0;
        for (int i=0;i<26;i++){
            for (int j=0;j<columns;j++){
                if (i==getKeyNumber(j)){
                    key_numeric[j]=index;
                    index++;
                }
            }
        }
        index=0;
        while (c <columns){
            index=key_numeric[m];
            for (int r=0;r<rows;r++){
                decrypted[r][index]=matrix[r][c];
            }
            m++;
            c++;
        }
        for (int i=0;i<columns;i++){
            for (int j=0;j<rows;j++){
                if ((int)decrypted[j][i]!=0){
                    output.append(decrypted[j][i]);
                }
            }
            output.append(" ");
        }
        return output.toString().trim();
    }

    @Override
    public String decryption(String input) {
        key=key.replaceAll("-","");
        input=input.replaceAll("\\s+","");
        int rows = getRows(input);
        int columns=key.length();
        int rest=rows*columns-input.length();
        char[][] matrix = new char[rows][columns];
        int index=0;
        for (int i=0;i<columns;i++){
            for (int j=0;j<rows;j++){
                if (j==rows-1 && i>=columns-rest){
                    matrix[j][i]=0;
                } else if (index<input.length()){
                    matrix[j][i] = input.charAt(index);
                    index++;
                }
            }
        }
        int[] key_numeric=new int[columns];
        char[][] decrypted =new char[rows][columns];
        StringBuilder output = new StringBuilder();
        int c =0;
        int m=0;
        index=0;
        for (int i=0;i<26;i++){
            for (int j=0;j<columns;j++){
                if (i==getKeyNumber(j)){
                    key_numeric[j]=index;
                    index++;
                }
            }
        }
        index=0;
        while (c <columns){
            index=key_numeric[m];
            for (int r=0;r<rows;r++){
                decrypted[r][c]=matrix[r][index];
            }
            m++;
            c++;
        }
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                    output.append(decrypted[i][j]);
            }
        }
        return output.toString().trim();
    }
}
