package models;

public abstract class Matrix implements Cipher {

    public String key;

    public Matrix(String key) {
        this.key = key;
    }

//    Wypełnia macierz
    public char[][] setMatrix(String input, int rows, int columns) {
        int index =0;
        char[][] matrix = new char[rows][columns];
        for (int i = 0; i< rows; i++){
            for (int j = 0; j<columns; j++){
                if (index<input.length()){
                    matrix[i][j] = input.charAt(index);
                    index++;
                }
            }
        }
        return matrix;
    }

    public int getRows(String input) {
        return input.length() / key.length() + (input.length() % key.length() > 0 ? 1 : 0);
    }

//    Zwraca znak z klucza
    public abstract int getKeyNumber(int j);
}
