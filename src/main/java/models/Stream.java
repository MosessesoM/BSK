package models;

public class Stream implements Cipher {

    public String key;

    public Stream(String key) {
        this.key = key;
    }

    @Override
    public String encryption(String input) {
        StringBuilder binary_input= new StringBuilder();
        StringBuilder encrypted = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i=0;i<input.length();i++){
            binary_input.append(String.format("%8s",Integer.toBinaryString(chars[i])).replaceAll(" ","0"));
        }
        LFSR lfsr = new LFSR(key);
        String binary_key= lfsr.generate(binary_input.toString());
        for (int i=0;i<binary_key.length();i++){
            if (binary_input.charAt(i)==binary_key.charAt(i)){
                encrypted.append('0');
            } else {
                encrypted.append('1');
            }
        }
        return encrypted.toString();
    }

    @Override
    public String decryption(String input) {
        return null;
    }
}
