package game;

import game.misc.Colour;
import game.misc.Move;
import game.misc.Position;
import game.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Player {
  private Colour colour;
  private Board board;

  public Player (Colour colour, Board board) {
    this.colour = colour;
    this.board = board;
  }

  public List<Move> getValidMoves() {
    ArrayList<Move> moves = new ArrayList<>();

    for (Piece piece : board.getPieces()) {
      if (piece.getColour().equals(colour)) {
        moves.addAll(piece.getValidMoves(board));
      }
    }

    return moves;
  }

  public boolean makeMove(Move move) {
    if (!getValidMoves().contains(move)) {
      // Trying to make an invalid move
      return false;
    }

    // If there is a piece, capture that piece.
    Optional<Piece> maybePiece = board.findPieceAtPosition(move.getPosTo());
    if (maybePiece.isPresent()) {
      Piece pieceToCapture = maybePiece.get();
      board.removePiece(pieceToCapture);
    }

    // Move piece to square
    move.getPiece().setPosition(move.getPosTo());

    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Player) {
      // Player is the same if colour is the same
      return colour.equals(((Player) o).colour);
    } else {
      return false;
    }
  }
}
