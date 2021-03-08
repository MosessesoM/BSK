package models;

public class Type_C extends Matrix {
    public Type_C(String key) {
        super(key);
    }

    @Override
    public int getKeyNumber(int j) {
        return 0;
    }

    @Override
    public String encryption(String input) {
        return null;
    }

    @Override
    public String decryption(String input) {
        return null;
    }
}
