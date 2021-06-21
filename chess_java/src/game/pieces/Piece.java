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
  boolean getHasMoved();
  void setHasMovedTrue();
  void setPosition(Position position);
  //Gets all valid moves, including moves that cause player's king to be in check
  List<Move> getValidMoves(Board board);
  // Gets all legal moves
  List<Move> getFilteredValidMoves(Board board);
  Piece copy();

  default boolean onLastRow(Board board) {
    return getPosition().getRow() == board.getLastRow(getColour());
  }
}
