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
    public int getRange() {
        return 26;
    }

}
