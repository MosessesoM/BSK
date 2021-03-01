package models;

public abstract class Matrix implements Cipher {

    public String key;

    public Matrix(String key) {
        this.key = key;
    }

//    Wypełnia macierz
    public char[][] setMatrix(String input, int rows) {
        int index =0;
        char[][] matrix = new char[rows][key.length()];
        for (int i = 0; i< rows; i++){
            for (int j = 0; j<key.length(); j++){
                if (index<input.length()){
                    matrix[i][j] = input.charAt(index);
                    index++;
                }
            }
        }
        return matrix;
    }

//    Zwraca znak z klucza
    public abstract int getKeyNumber(int j);

//    Ustala zakres fora
    public abstract int getRange();

    public String encryption(String input) {
        int rows = input.length()/key.length() + (input.length()%key.length()>0?1:0);
        char[][] matrix = setMatrix(input, rows);
        StringBuilder output = new StringBuilder();
        for (int x=0;x<rows;x++){
            for (int i = 0; i < getRange(); i++) {
                for (int j = 0; j < key.length(); j++) {
                    if (i == getKeyNumber(j)) {
                        output.append(matrix[x][j]);
                    }
                }
            }
        }
        return output.toString();
    }

    public String decryption(String input) {
        return null;
    }
}
