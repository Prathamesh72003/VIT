import java.util.*;
class TicTac{
    
    static Scanner sc = new Scanner(System.in);

    static int[][] board = new int[3][3];



    public static void play(){
        // System.out.println("Welcome to the game!!");
        // System.out.print("Enter the name for player 1: ");
        // String player1 = sc.nextLine();
        // System.out.print("Enter the name for player 2: ");
        // String player2 = sc.nextLine();
        // System.out.println("****************************************");
        // System.out.println("Hello "+player1+" and "+player2+" lets start the game!");
        // System.out.println(player1+" would be playing X");
        // System.out.println(player2+" would be playing O");
        // System.out.println("****************************************");

        // while (true) {
            
        // }

        System.out.println(Arrays.deepToString(board));
        

    }


}


public class TicTacToe{
    public static void main(String[] args) {
        TicTac game = new TicTac();
        game.play();
    }
}