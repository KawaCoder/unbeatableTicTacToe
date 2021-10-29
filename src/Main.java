/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * License holder: DR34M-M4K3R#7751*/



import java.util.*;

public class Main {

    //static
    static String turn;
    static int nbofturn = 0;
    static int winindicator;


    static String[] board = new String[9];
    static String[] copyboard = new String[9];
    /*static void initiateBoard() {
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
    }*/


    // CheckWinner method will
    // decide the combination
    // of three box given below.
    static int checkWinner(String[] testedboard) {
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = testedboard[0] + testedboard[1] + testedboard[2];
                    break;
                case 1:
                    line = testedboard[3] + testedboard[4] + testedboard[5];
                    break;
                case 2:
                    line = testedboard[6] + testedboard[7] + testedboard[8];
                    break;
                case 3:
                    line = testedboard[0] + testedboard[3] + testedboard[6];
                    break;
                case 4:
                    line = testedboard[1] + testedboard[4] + testedboard[7];
                    break;
                case 5:
                    line = testedboard[2] + testedboard[5] + testedboard[8];
                    break;
                case 6:
                    line = testedboard[0] + testedboard[4] + testedboard[8];
                    break;
                case 7:
                    line = testedboard[2] + testedboard[4] + testedboard[6];
                    break;
            }
            // +1 = X winner
            if (line.equals("XXX")) {
                return 1;
            }

            // -2 = O winner
            else if (line.equals("OOO")) {
                return -2;            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(testedboard).contains(
                    String.valueOf(a + 1))) {
                break;
            }
            else if (a == 8) {
                // -1 for draw
                return -1;        }
        }

        //0 for nothing

        return 0;
    }


  /*  //https://www.softwaretestinghelp.com/add-elements-to-array-java/
    public static int[] add_element(int n, int[] myarray, int newitem){
        int i;

        int newArray[] = new int[n + 1];
        //copy original array into new array
        for (i = 0; i < n; i++){
            newArray[i] = myarray[i];
        }
        //add element to the new array
        newArray[n] = newitem;

        return newArray;
    }
*/


    // Picking up a random string for the bot's turn.
    public static void randomstring(){

        String[] arr={"Haha, I am so smart!", "Good luck for this one...", "What could I play next?", "You are smart. But not enough", "No one can defeat me!"};
        Random r=new Random();
        int randomString=r.nextInt(arr.length);
        System.out.println(arr[randomString]);
    }



    // Ai method to check all possibilities
    static int ai(String[] importedboard, String player){
        //System.out.println("[#] Begging simulation of "+player+" turn...");
        //ArrayList<Integer> nodesscore = new ArrayList<>();

        //System.out.println("[#] Clonage de la grille pour les tests...");
        //try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}

        //determine the number of free cases on the board.
        int freecases = 0;
        //System.out.println("[#] Calcul des cases libres... ");
        //try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.current().interrupt();}

        for (int i = 0; i< (importedboard.length-1) ; i++) {

            if (!importedboard[i].equals("X") || !importedboard[i].equals("O")){
                freecases++;

            }

        }
        //System.out.println("[#] Terminé. "+(freecases-1)+" trouvées.");
        //try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}


        int[] nodesscore=new int[freecases+1];

        for (int i = 0; i< (importedboard.length) ; i++) {

            System.out.println("\nTesting the slot "+(i+1));
            if (!importedboard[i].equals("X") && !importedboard[i].equals("O")){

                importedboard[i]=player;
                printBoard(importedboard);
                if (checkWinner(importedboard)==1){
                    //nodesscore = add_element(nodesscore.length, nodesscore, 1);
                    nodesscore[i]=nodesscore[i]+1;
                    System.out.println("Un point pour X"+Arrays.toString(nodesscore));
                    return i+1;



                }else if (checkWinner(importedboard)==-1){
                    //nodesscore = add_element(nodesscore.length, nodesscore, -1);
                    nodesscore[i]=nodesscore[i]-1;
                    System.out.println("Draw"+Arrays.toString(nodesscore));


                }else if (checkWinner(importedboard)==-2){
                    //nodesscore = add_element(nodesscore.length, nodesscore, -2);
                    nodesscore[i]=nodesscore[i]+1;
                    System.out.println("Un point pour O"+Arrays.toString(nodesscore));
                    return i+1;


                }else if (checkWinner(importedboard)==0) {

                    //simulate the other player's play
                    if (Objects.equals(player, "X")){
                        //System.out.println("Simulation du tour suivant");


                        return ai(importedboard, "O");


                    }else {

                        //System.out.println("Simulation du tour suivant");


                        return ai(importedboard, "X");

                    }


                }

//reset board
                importedboard[i]=Integer.toString(i+1);

            }else{

                //System.out.println("Cette case n'est pas vide!!");
            }




        }


        return 0;
    }







// To print out the board.
	/* |---|---|---|
	| 1 | 2 | 3 |
	|-----------|
	| 4 | 5 | 6 |
	|-----------|
	| 7 | 8 | 9 |
	|---|---|---|*/

    static void printBoard(String[] printedboard)
    {
        System.out.println("|---|---|---|");
        System.out.println("| " + printedboard[0] + " | "
                + printedboard[1] + " | " + printedboard[2]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + printedboard[3] + " | "
                + printedboard[4] + " | " + printedboard[5]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + printedboard[6] + " | "
                + printedboard[7] + " | " + printedboard[8]
                + " |");
        System.out.println("|---|---|---|\n\n\n");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //String[] board = new String[9];

        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
        //initiateBoard();
        //String[] copyboard=board;



        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }


    turn = "O";
    String winner = null;


    //board[2]="X";
    //board[1]="O";
        System.out.println("Welcome to the unbeatable Tic Tac Toe. Have a nice Defeat.\n\n");
        System.out.println("I will play first.\n");


    //first turn, play in the corner.
        randomstring();
        int[] arr={1, 3, 7, 9};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        //int firstInput=(arr[randomNumber]);
        int firstInput=1;

        board[firstInput - 1] = "X";
        printBoard(board);
        checkWinner(board);
//end of the first turn.



        while (winner == null) {
            int numInput = 0;


            if (turn.equals("X")) {

                System.arraycopy(board, 0, copyboard, 0, board.length);

                randomstring();
                numInput=ai(copyboard, "X");
                if (numInput==0){
                    numInput=ai(copyboard, "O");

                }

                System.out.println("[#] Simulation ended.");

                System.out.println("I choosed the slot "+numInput);
                //printBoard(board);
                //System.out.println(Arrays.toString(board));
                //System.exit(0);




            } else {

                System.out.println(
                        "It is your turn; enter a slot number to place "
                                + turn + " in:");


                // Exception handling.
                // numInput will take input from user like from 1 to 9.
                // If it is not in range from 1 to 9.
                // then it will show you an error "Invalid input."
                try {
                    numInput = in.nextInt();
                    if (!(numInput > 0 && numInput <= 9)) {
                        System.out.println(
                                "Invalid input; re-enter slot number:");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println(
                            "Invalid input; re-enter slot number:");
                    continue;
                }

                // This game has two player x and O.
                // Here is the logic to decide the turn.
            }
            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = turn;
                nbofturn++;

                if (turn.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }

                printBoard(board);
                if (checkWinner(board)==1){
                    winner = "X";

                }else if (checkWinner(board)==-1){
                    winner = "Draw";

                }else if (checkWinner(board)==-2){
                    winner = "O";


                }

            } else {
                System.out.println(
                        "Slot already taken; re-enter slot number:");
            }

        }
//test
        // If no one win or lose from both player x and O.
        // then here is the logic to print "draw".
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println(
                    "It's a draw! Thanks for playing.");
        }

        // For winner -to display Congratulations! message.
        else {
            System.out.println(
                    "Congratulations! " + winner
                            + "'s have won! Thanks for playing.");
        }
    }
}


//try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}
