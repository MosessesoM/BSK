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
    public int getRange() {
        return key.length();
    }

}
