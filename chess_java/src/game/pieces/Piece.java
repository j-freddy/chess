package game.pieces;

import game.Board;
import game.misc.Colour;
import game.misc.Move;
import game.misc.Position;

import java.util.List;

public interface Piece {
  Colour getColour();
  PieceType getPieceType();
  Position getPosition();
  List<Move> getValidMoves(Board board);

  default boolean isOutOfBounds(Board board) {
    return getPosition().isOutOfBounds(board);
  }
}
