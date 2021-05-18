package game;

import game.misc.*;
import game.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Board {
  private final int noRows = 8;
  private final int noCols = 8;
  private ArrayList<Piece> pieces = new ArrayList<>();

  public Board () {
    this.setUpBoard();
  }

  public int getNoRows() {
    return noRows;
  }

  public int getNoCols() {
    return noCols;
  }

  public ArrayList<Piece> getPieces() {
    return pieces;
  }

  public void removePiece(Piece piece) {
    pieces.remove(piece);
  }

  private void setUpBoard() {
    pieces.add(new Knight(Colour.WHITE, new Position(3, 1)));

    pieces.add(new Rook(Colour.WHITE, new Position(0, 0)));
    pieces.add(new Rook(Colour.WHITE, new Position(0, 7)));
    pieces.add(new Bishop(Colour.WHITE, new Position(0, 2)));
    pieces.add(new Bishop(Colour.WHITE, new Position(0, 5)));
    pieces.add(new Queen(Colour.WHITE, new Position(0, 3)));

    pieces.add(new Rook(Colour.BLACK, new Position(5, 0)));
    pieces.add(new Rook(Colour.BLACK, new Position(5, 7)));
  }

  public Optional<Piece> findPieceAtPosition(Position position) {
    List<Piece> foundPieces = pieces
        .stream()
        .filter(piece -> piece.getPosition().equals(position))
        .collect(Collectors.toList());

    assert foundPieces.size() <= 1;

    if (foundPieces.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of(foundPieces.get(0));
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int i = noRows - 1; i >= 0; i--) {
      for (int j = 0; j < noCols; j++) {
        Position targetPos = new Position(i, j);
        Optional<Piece> maybePiece = findPieceAtPosition(targetPos);

        if (maybePiece.isPresent()) {
          Piece piece = maybePiece.get();
          sb
              .append(piece)
              .append(" ");
        } else {
          sb.append("* ");
        }
      }

      sb.append("\n");
    }

    return sb.toString();
  }

  // Testing
  public static void main(String[] args) {
    Board b = new Board();
    System.out.println(b);

    List<Move> validMoves = b.pieces.get(0).getValidMoves(b);
    System.out.println(validMoves);
  }
}
