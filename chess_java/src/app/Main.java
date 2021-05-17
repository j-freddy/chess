package app;

import game.Board;
import game.Game;
import game.misc.Position;
import game.pieces.Piece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

  /* ---------- Dimensions ---------- */
  private final int appWidth = 600;
  private final int appHeight = 600;
  // Note: Eventually I want to add a move history / other functionalities
  // So the app will have the canvas and some other panels.
  private final int boardWidth = 600;
  private final int boardHeight = 600;

  /* ---------- Graphics ---------- */
  private final Color colourCellLight = Color.rgb(233, 207, 190);
  private final Color colourCellDark = Color.rgb(172, 135, 72);

  private Game game;

  public Main() {
    game = new Game();
  }

  private int getCellWidth() {
    return boardWidth / game.getBoard().getNoCols();
  }

  private int getCellHeight() {
    return boardHeight / game.getBoard().getNoRows();
  }

  private Color getColourOfCell(int row, int column) {
    if (row % 2 == 1) {
      if (column % 2 == 0) {
        return colourCellDark;
      } else {
        return colourCellLight;
      }
    } else {
      if (column % 2 == 0) {
        return colourCellLight;
      } else {
        return colourCellDark;
      }
    }
  }

  // Draws board (without pieces)
  private void drawBoardBase(Canvas canvas) {
    GraphicsContext ctx = canvas.getGraphicsContext2D();
    Board board = game.getBoard();

    for (int i = 0; i < board.getNoRows(); i++) {
      // By changing the column on the inner loop
      // We are rendering the board from left to right, per row.
      for (int j = 0; j < board.getNoCols(); j++) {
        // x depends on column
        int x = getCellWidth() * j;
        // y depends on row
        int y = getCellHeight() * i;

        ctx.setFill(getColourOfCell(i, j));
        ctx.fillRect(x, y, getCellWidth(), getCellHeight());
      }
    }
  }

  private void drawPieces(Canvas canvas) {
    GraphicsContext ctx = canvas.getGraphicsContext2D();
    Board board = game.getBoard();

    for (Piece piece : board.getPieces()) {
      Position pos = piece.getPosition();

      // Temporary
      int x = getCellWidth() * pos.getColumn();
      int y = boardHeight - getCellHeight() * (pos.getRow() + 1);
      ctx.setFill(Color.BLACK);
      ctx.fillRect(x, y, getCellWidth(), getCellHeight());
    }
  }

  private void drawBoard(Canvas canvas) {
    drawBoardBase(canvas);
    drawPieces(canvas);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane root = new Pane();
    Scene scene = new Scene(root, appWidth, appHeight);

    // Canvas for board
    Canvas canvas = new Canvas(boardWidth, boardHeight);
    root.getChildren().add(canvas);
    drawBoard(canvas);

    primaryStage.setTitle("Chess Java");
    primaryStage.setScene(scene);
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
