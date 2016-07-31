import java.util.Scanner;
 
class Minesweeper {
 
    public static void main(String args[]) {
        System.out.println("Enter row and column of grid");
        Scanner kb = new Scanner(System.in);
        int row = kb.nextInt();
        int col = kb.nextInt();
        int gridSize = row*col;
 
        char[][] minesweeperGrid = new char[row][col];
        char[][] output = new char[row][col];
 
        for (int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                minesweeperGrid[i][j] = 'x';
                output[i][j] = 'x';
            }
        }
 
        int c = 1, mines = 0;
        while (c == 1) {
            int i,j;
            System.out.println("Enter i,j position of mines");
            i = kb.nextInt();
            j = kb.nextInt();
            minesweeperGrid[i][j] = 'm';
            mines++;
            System.out.println("Press 1 to add more mines");
            c = kb.nextInt();
        }
 
        for (int k=0;k<gridSize-mines;k++){
            int i,j;
            System.out.println("Enter i,j input");
            i = kb.nextInt();
            j = kb.nextInt();
            if (minesweeperGrid[i][j] == 'm'){
                printGrid(row, col, minesweeperGrid);
                System.out.println("Game over");
                System.exit(0);
            }
            else if (minesweeperGrid[i][j] == 'x' && output[i][j] != 'o') {
                System.out.println("Nice try");
                output[i][j] = 'o';
                printGrid(row, col, output);
                continue;
            }
            else {
                System.out.println("You have already explored this cell, enter a new value");
                k--;
            }
        }
        System.out.println("You Won");
        System.out.println("Actual mine");
        printGrid(row, col, minesweeperGrid);
    }
 
    private static void printGrid(int row, int col, char[][] grid) {
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
 
}