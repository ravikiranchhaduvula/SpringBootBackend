package DesignPatterns.CreationalPatterns.Prototype;

public class ClientWithPrototype {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.addPiece(new GamePiece("Red", 1));
        gameBoard.addPiece(new GamePiece("Blue", 5));

        gameBoard.showBoardState();

        // Checkpoint Gameboard
        GameBoard copiedBoard = gameBoard.clone(); // Decoupled (And if new props added also no issues)

        System.out.println("copiedBoard");
        copiedBoard.showBoardState();
    }
}
