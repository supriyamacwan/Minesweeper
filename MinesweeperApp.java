import java.util.Scanner;
 
abstract class Cell {
    abstract boolean isDead();
    abstract char print();
}
class SafeCell extends Cell {
    boolean isDead() {
        return false;
    }
    char print() {
        return 'x';
    }
}
class MineCell extends Cell {
    boolean isDead() {
        return true;
    }
    char print() {
        return 'm';
    }
}
class MinesweeperBoard {
 
    private Cell[][] board;
    private char[][] output;
    private int rowSize;
    int opened;
    int boardSize;
    int mines;
 
    public MinesweeperBoard(int size) {
        board = new Cell[size][size];
        output = new char[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                board[i][j] = new SafeCell();
                output[i][j] = 'x';
            }
        }
        opened = 0;
        boardSize = size * size;
        mines = 0;
        rowSize = size;
    }
 
    void plotMine(int row, int col) {
        board[row][col] = new MineCell();
        mines++;
    }
 
    boolean isGameOver(int row, int col) {
        Cell cell = board[row][col];
        return cell.isDead();
    }
 
    boolean open(int row, int col) {
 
        if (isGameOver(row, col)) {
            System.out.println("Game over");
            printBoard();
            return false;
        }
        else if ( !isGameOver(row, col) && output[row][col] != 'o') {
            System.out.println("Nice try");
            output[row][col] = 'o';
            opened++;
            printOutput();
            return true;
        }
        else {
            System.out.println("You have already explored this cell, enter a new value");
            printOutput();
            return true;
        }
 
    }
 
    void printOutput() {
        for (int i=0; i<rowSize; i++) {
            for (int j=0; j<rowSize; j++) {
                System.out.print(output[i][j]);
            }
            System.out.println();
        }
    }
 
    void printBoard() {
        System.out.println("Actual minesweeper board was:");
        for (int i=0; i<rowSize; i++) {
            for (int j=0; j<rowSize; j++) {
                System.out.print(board[i][j].print());
            }
            System.out.println();
        }
    }
}
 
public class MinesweeperApp {
 
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
 
        System.out.println("Enter row size of square board");
        int size = kb.nextInt();
        MinesweeperBoard minesweeperBoard = new MinesweeperBoard(size);
 
        int c = 1;
        while (c == 1) {
            int i,j;
            System.out.println("Enter i,j position of mine");
            i = kb.nextInt();
            j = kb.nextInt();
            minesweeperBoard.plotMine(i, j);
            System.out.println("Press 1 to add more mines");
            c = kb.nextInt();
        }
 
        while (minesweeperBoard.opened < (minesweeperBoard.boardSize - minesweeperBoard.mines)){
            int i,j;
            System.out.println("Enter i,j input");
            i = kb.nextInt();
            j = kb.nextInt();
            if(minesweeperBoard.open(i, j)){
                System.out.println("Please enter next value");
            }
            else {
                System.out.println("Sorry");
                System.exit(0);
            }
        }
 
        System.out.println("You won");
        minesweeperBoard.printBoard();
 
    }
 
}
