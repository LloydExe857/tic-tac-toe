package com.company;

public class Board {

    Space[] spaces = new Space[9];

    Board() {
        for (int i = 1;i < 10;i++){
            String spaceValue = String.valueOf(i);
            spaces[i - 1] = new Space(spaceValue);
        }
    }

    public void displayBoard() {
        int spaceNum = 0;
        for (int i = 0;i < 5; i++){
            for (int k = 0;k < 5; k++){
                if (i % 2 == 0){
                    if (k % 2 == 0){
                        System.out.print(" " + spaces[spaceNum].getSpaceValue() + " ");
                        spaceNum++;
                    }
                    else{
                        System.out.print("|");
                    }
                }
                else{
                    if (k % 2 == 0) {
                        System.out.print("---");
                    }
                    else{
                        System.out.print("+");
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
