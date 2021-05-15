package game.pieces;

import game.Board;
import game.misc.Colour;
import game.misc.Move;
import game.misc.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Rook implements Piece {

  private Colour colour;
  private Position position;

  public Rook (Colour colour, Position position) {
    this.colour = colour;
    this.position = position;
  }

  @Override
  public Colour getColour() {
    return colour;
  }

  @Override
  public PieceType getPieceType() {
    return PieceType.ROOK;
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public List<Move> getValidMoves(Board board) {
    ArrayList<Move> moves = new ArrayList<>();

    Position newPos = position.copy();

    // Traverse left
    while (true) {
      // We can move first, since we don't have to validate our starting position
      // In fact, if we don't move first, it gets messed up
      // since the board detects our current piece at the start position
      newPos.setRow(newPos.getRow() - 1);

      if (newPos.isOutOfBounds(board)) {
        break;
      }

      Move move = new Move(this, newPos);

      Optional<Piece> maybePiece = board.findPieceAtPosition(newPos);

      if (maybePiece.isPresent()) {
        Piece piece = maybePiece.get();

        if (piece.getColour().equals(getColour())) {
          // Same colour means we stop immediately
          break;
        } else {
          // Different colour means we can capture piece
          moves.add(move);
          break;
        }
      } else {
        // We didn't hit a piece, so continue
        moves.add(move);
      }
    }

    return moves;
  }

  @Override
  public String toString() {
    if (colour.equals(Colour.WHITE)) {
      return "R";
    } else {
      return "r";
    }
  }
}
