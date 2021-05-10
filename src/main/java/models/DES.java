package models;

import java.util.Arrays;
import java.util.Collections;

public class DES implements Cipher {

    private String generated_key;

    private StringBuilder output = new StringBuilder();
    private Character[][] block = new Character[8][8];
    private Character[][] left_block = new Character[8][4];
    private Character[][] right_block = new Character[8][4];
    private Character[][] initial_key = new Character[8][8];
    private Character[][] perm_key = new Character[8][7];
    private Character[][] n_key_block=new Character[8][6];
    private Character[][] first_c = new Character[4][7];
    private Character[][] second_d = new Character[4][7];
    private Character[][] expanded_right =new Character[8][6];
    private Character[][] pom = new Character[8][4];
    private Character[][] xored = new Character[8][6];
    private Integer[][] s1 = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},{4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
    private Integer[][] s2 = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},{3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},{0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},{13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
    private Integer[][] s3 = {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},{13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},{13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},{1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
    private Integer[][] s4 = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},{13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},{10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},{3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
    private Integer[][] s5 = {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},{14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},{4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},{11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
    private Integer[][] s6 = {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},{10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},{9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},{4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};
    private Integer[][] s7 = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},{13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},{1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},{6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
    private Integer[][] s8 = {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},{7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};
    private Integer[][][] all_s={s1,s2,s3,s4,s5,s6,s7,s8};
    private Integer[][] permutation_p_table={{16, 7, 20, 21},{29, 12, 28, 17},{1, 15, 23, 26},{5, 18, 31, 10},{2, 8, 24, 14},{32, 27, 3, 9},{19, 13, 30, 6},{22, 11, 4, 25}};
    private int[] shift_table= {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};

    @Override
    public String encryption(String input) {
        String binary_input=getBinaryInput(input);
        int blocks_number=binary_input.length()/64;
        int index=0;
        int index2=0;
        int index3=0;
        String s = String.join("", Collections.nCopies(64, String.valueOf('x')));
        LFSR lfsr = new LFSR("1+x^21+x^37");
        generated_key=lfsr.generate(s);
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                initial_key[i][j]=generated_key.charAt((i+1)*(j+1)-1);
            }
        }
        for (int i=0;i<blocks_number;i++){
            for (int j=0;j<8;j++){
                for (int k=0;k<8;k++){
                    block[j][k]=binary_input.charAt(index);
                    index++;
                }
            }
            perm_key = permutedChoice(initial_key);
            for (int j=0;j<16;j++){
                block = initialPermutation(block);
                left_block = leftSplit(block, 64);
                right_block = rightSplit(block, 64);
                first_c = up_split(perm_key);
                second_d = down_split(perm_key);
                first_c = shift(first_c,shift_table[j]);
                second_d=shift(second_d,shift_table[j]);
                perm_key =merge(first_c,second_d);
                n_key_block=permutedChoice(perm_key);
                expanded_right =expandPermutation(right_block);
                xored=exclusiveOr(n_key_block, expanded_right);
                right_block=coordinates(xored,all_s);
                right_block=permutationP(right_block,permutation_p_table);
                pom=right_block;
                right_block =exclusiveOr(right_block, left_block);
                left_block =pom;
            }
            block=merge(right_block, left_block);
            block=final_permutation(block);
            for (int x=0;x<8;x++){
                for (int y=0;y<8;y++){
                    output.append(block[x][y]);
                }
            }
        }
        int lacking=64-binary_input.length()%64;
        String binary_lacking=Integer.toBinaryString(lacking);
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (index<binary_input.length()) {
                    block[i][j] = binary_input.charAt(index);
                    index++;
                } else {
                    if (index2<lacking-binary_lacking.length()){
                        block[i][j]='0';
                        index2++;
                    } else if (index3<lacking-1){
                        block[i][j]=binary_lacking.charAt(index3);
                        index3++;
                    }
                }
            }
        }
        for (int j=0;j<16;j++){
            block = initialPermutation(block);
            left_block = leftSplit(block, 64);
            right_block = rightSplit(block, 64);
            first_c = up_split(perm_key);
            second_d = down_split(perm_key);
            first_c = shift(first_c, shift_table[j]);
            second_d = shift(second_d, shift_table[j]);
            perm_key = merge(first_c, second_d);
            n_key_block = permutedChoice(perm_key);
            expanded_right = expandPermutation(right_block);
            xored = exclusiveOr(n_key_block, expanded_right);
            right_block = coordinates(xored, all_s);
            right_block = permutationP(right_block, permutation_p_table);
            pom = right_block;
            right_block = exclusiveOr(right_block, left_block);
            left_block = pom;
        }
        block=merge(right_block, left_block);
        block=final_permutation(block);
        for (int x=0;x<8;x++){
            for (int y=0;y<8;y++){
                output.append(block[x][y]);
            }
        }
        return output.toString();
    }

    @Override
    public String decryption(String input) {
        String binary_input=input;
        int blocks_number=binary_input.length()/64;
        int index=0;
        int index2=0;
        int index3=0;
        Character[][][] keys = new Character[16][8][6];
        String s = String.join("", Collections.nCopies(64, String.valueOf('x')));
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                initial_key[i][j]=generated_key.charAt((i+1)*(j+1)-1);
            }
        }
        for (int i=0;i<blocks_number;i++){
            for (int j=0;j<8;j++){
                for (int k=0;k<8;k++){
                    block[j][k]=binary_input.charAt(index);
                    index++;
                }
            }
            perm_key = permutedChoice(initial_key);
            for (int j=0;j<16;j++){
                first_c = up_split(perm_key);
                second_d = down_split(perm_key);
                perm_key =merge(first_c,second_d);
                keys[15-j]=permutedChoice(perm_key);
            }
            for (int j=0;j<16;j++){
                n_key_block=keys[j];
                block = initialPermutation(block);
                left_block = leftSplit(block, 64);
                right_block = rightSplit(block, 64);
                first_c = shift(first_c,shift_table[j]);
                second_d=shift(second_d,shift_table[j]);
                expanded_right =expandPermutation(right_block);
                xored=exclusiveOr(n_key_block, expanded_right);
                right_block=coordinates(xored,all_s);
                right_block=permutationP(right_block,permutation_p_table);
                pom=right_block;
                right_block =exclusiveOr(right_block, left_block);
                left_block =pom;
            }
            block=merge(right_block, left_block);
            block=final_permutation(block);
            for (int x=0;x<8;x++){
                for (int y=0;y<8;y++){
                    output.append(block[x][y]);
                }
            }
        }
        StringBuilder checking_bit = new StringBuilder();
        for (int i=0;i<8;i++){
            checking_bit.append(block[7][i]);
        }
//        TODO: to dodać jak będzie działać
//        int added = Integer.parseInt(checking_bit.toString(),2);
//        System.out.println(checking_bit.toString());
//        System.out.println(added);
//        String encrypted=output.toString();
//        encrypted=encrypted.substring(0,encrypted.length()-added-1);
        return output.toString();
    }

    public String getBinaryInput(String input){
        StringBuilder binary_input= new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i=0;i<input.length();i++){
            binary_input.append(String.format("%8s",Integer.toBinaryString(chars[i])).replaceAll(" ","0"));
        }
        return binary_input.toString();
    }

//    działa
    private Character[][] initialPermutation(Character[][] block){
        Character[][] perm_block=new Character[block.length][block[0].length];
        for (int i=0;i<perm_block.length;i++){
            for (int j=0;j<perm_block[0].length;j++){
                if (i<4){
                    perm_block[i][j]=block[7-j][1+i*2];
                } else {
                    perm_block[i][j]=block[7-j][i*2-8];
                }
            }
        }
        return perm_block;
    }

//    działa
    private Character[][] leftSplit(Character[][] block,int number){
        Character[][] left= new Character[8][4];
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                left[i][j]=block[i][j];
            }
        }
        return left;
    }

//    działa
    private Character[][] rightSplit(Character[][] block,int number){
        Character[][] right= new Character[8][4];
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                right[i][j]=block[i][j+4];
            }
        }
        return right;
    }

//    działa
    private Character[][] permutedChoice(Character[][] key){
        Character[][] permuted_key = new Character[key.length][key[0].length-1];
        Character[] pom = new Character[(key.length)*(key[0].length-1)];
        int index=0;
        for (int j=0;j<key[0].length-1;j++){
            for (int i=key.length-1;i>=0;i--){
                if (index> (key.length*(key[0].length-1))/2)
                    break;
                pom[index]=key[i][j];
                index++;
            }
        }
        for (int j=key[0].length-2;j>=0;j--){
            for (int i=key.length-1;i>=0;i--){
                if (index>key.length*(key[0].length-1)-1)
                    break;
                if (j==3 && i==key.length-1)
                    i-=4;
                pom[index]=key[i][j];
                index++;
            }
        }
        index=0;
        for (int i=0;i<key.length;i++){
            for (int j=0;j<key[0].length-1;j++){
                permuted_key[i][j]=pom[index];
                index++;
            }
        }
        return permuted_key;
    }

//    działa
    private Character[][] up_split(Character[][] perm_key){
        Character[][] first_c = new Character[4][7];
        for (int i=0;i<4;i++){
            for (int j=0;j<7;j++){
                first_c[i][j]=perm_key[i][j];
            }
        }
        return first_c;
    }

//    działą
    private Character[][] down_split(Character[][] perm_key){
        Character[][] second_d = new Character[4][7];
        for (int i=0;i<4;i++){
            for (int j=0;j<7;j++){
                second_d[i][j]=perm_key[i+4][j];
            }
        }
        return second_d;
    }

//    działa
    private Character[][] shift(Character[][] half,int number){
        Character[][] shifted =new Character[half.length][half[0].length];
        for (int i=0;i<half.length;i++){
            for (int j=0;j<half[0].length;j++){
                if (j+number>half[0].length-1){
                    shifted[i][j] = half[(i+1)%half.length][(j + number) % half[0].length];
                } else {
                    shifted[i][j]=half[i][(j + number) % half[0].length];
                }
            }
        }
        return shifted;
    }

//    działa
    private Character[][] merge(Character[][] first, Character[][] second){
        int x,y;
        if (first.length<first[0].length){
            x=first.length*2;
            y=first[0].length;
        } else {
            x=first.length;
            y=first[0].length*2;
        }
        Character[][] merged = new Character[x][y];
        for (int i=0;i<x;i++){
            for (int j=0;j<y;j++){
                if (x>y){
                    merged[i][j] = ((i < x/2) ? first[i][j] : second[i - x/2][j]);
                } else {
                    merged[i][j]=((j<y/2)?first[i][j]:second[i][j-y/2]);
                }
            }
        }
        return merged;
    }

//    działa
    private Character[][] expandPermutation(Character[][] block){
        Character[][] expanded = new Character[8][block[0].length+2];
        Character[][] pom = new Character[8][block[0].length];
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                if (j-1<0){
                    pom[i][j] = block[((i==0) ? 7:i-1)%block.length][block[0].length-1];
                } else {
                    pom[i][j] = block[i][j - 1];
                }
            }
        }
        for (int i=0;i<8;i++){
            for (int j=0;j<6;j++){
                expanded[i][j]=((j<pom[0].length)? pom[i][j]:pom[(i+1)% pom.length][j-pom[0].length]);
            }
        }
        return expanded;
    }

//    działa
    private Character[][] exclusiveOr(Character[][] first,Character[][] second){
        Character[][] xored = new Character[first.length][first[0].length];
        for (int i=0;i<first.length;i++){
            for (int j=0;j<first[0].length;j++){
                xored[i][j]=Character.forDigit(first[i][j]^second[i][j],10);
            }
        }
        return xored;
    }

//    działa
    private Character[][] coordinates(Character[][] block,Integer[][][] all_s){
        Character[][] output = new Character[8][4];
        StringBuilder coordinate_rows = new StringBuilder();
        StringBuilder coordinate_columns = new StringBuilder();
        Integer number;
        int pom=0;
        for (int i=0;i<8;i++){
            coordinate_rows.append(block[i][0]);
            coordinate_rows.append(block[i][5]);
            coordinate_columns.append(block[i][1]);
            coordinate_columns.append(block[i][2]);
            coordinate_columns.append(block[i][3]);
            coordinate_columns.append(block[i][4]);
            number=all_s[i][Integer.parseInt(coordinate_rows.toString(),2)][Integer.parseInt(coordinate_columns.toString(),2)];
            pom=Integer.toBinaryString(number).length()-1;
            for (int j=3;j>=0;j--){
                if (pom>=0){
                    output[i][j] = Integer.toBinaryString(number).charAt(pom);
                    pom--;
                } else {
                    output[i][j]='0';
                }
            }
            coordinate_rows.setLength(0);
            coordinate_columns.setLength(0);
        }
        return output;
    }

//    działa
    private Character[][] permutationP(Character[][] block,Integer[][] permutation){
        Character[][] permutated = new Character[8][4];
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                for (int k=0;k<8;k++){
                    for (int m=0;m<4;m++){
                        if ((4*(k+1)-(4-(m+1)))==permutation[i][j]){
                            permutated[i][j]=block[k][m];
                        }
                    }
                }
            }
        }
        return permutated;
    }

//    działa
    private Character[][] final_permutation(Character[][] block){
        Character[][] perm_block = new Character[8][8];
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (i<4){
                    perm_block[7-j][1+i*2]=block[i][j];
                } else {
                    perm_block[7-j][i*2-8]=block[i][j];
                }
            }
        }
        return perm_block;
    }

    public String getGenerated_key() {
        return generated_key;
    }

    public void setGenerated_key(String generated_key) {
        this.generated_key = generated_key;
    }
}
