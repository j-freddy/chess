package game.pieces;

import game.Board;
import game.misc.Colour;
import game.misc.Move;
import game.misc.Position;

import java.util.List;

public class DefaultPiece implements Piece {

  protected Colour colour;
  protected Position position;

  public DefaultPiece (Colour colour, Position position) {
    this.colour = colour;
    this.position = position;
  }

  @Override
  public Colour getColour() {
    return colour;
  }

  @Override
  public PieceType getPieceType() {
    return null;
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public List<Move> getValidMoves(Board board) {
    return null;
  }
}