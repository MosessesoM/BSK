package models;

public class Type_A extends Matrix {

    public Type_A(String key) {
        super(key);
    }

    @Override
    public int getKeyNumber(int j) {
        return key.charAt(j) - '0' - 1;
    }

    @Override
    public String encryption(String input) {
        key=key.replaceAll("-","");
        int rows = getRows(input);
        int columns=key.length();
        char[][] matrix = setMatrix(input, rows,columns);
        StringBuilder output = new StringBuilder();
        char[][] decrypted=new char[rows][columns];
        int m=0,k=0,index=0;
        while (k<columns){
            index=getKeyNumber(m);
            for (int r=0;r<rows;r++){
                decrypted[r][k]=matrix[r][index];
            }
            m++;
            k++;
        }
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                if ((int)decrypted[i][j]!=0){
                    output.append(decrypted[i][j]);
                }
            }
        }
        return output.toString();
    }

    @Override
    public String decryption(String input) {
        key=key.replaceAll("-","");
        int rows = getRows(input);
        int columns=key.length();
        char[][] matrix = setMatrix(input, rows,columns);
        StringBuilder output = new StringBuilder();
        for (int i=columns-1;i>=0;i--){
            for(int j=0;j<columns;j++){
                if (i==getKeyNumber(j) && (int)matrix[rows-1][j]!=0){
                    int pom=i;
                    while (pom>=0){
                        if ((int)matrix[rows-1][pom]==0){
                            matrix[rows-1][pom]=matrix[rows-1][j];
                            matrix[rows-1][j]=0;
                        }
                        pom--;
                    }
                }
            }
        }
        for (int x=0;x<rows;x++){
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < columns; j++) {
                    if (i == getKeyNumber(j)) {
                        output.append(matrix[x][j]);
                    }
                }
            }
        }
        return output.toString();
    }

}
