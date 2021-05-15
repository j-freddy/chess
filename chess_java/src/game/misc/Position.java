package game.misc;

import game.Board;

public class Position {
  private int row;
  private int column;

  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public boolean isOutOfBounds(Board board) {
    // row and column are 0-indexed
    return row < 0
        && row >= board.getNoRows()
        && column < 0
        && column >= board.getNoCols();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Position) {
      return row == ((Position) o).row
          && column == ((Position) o).column;
    } else {
      return false;
    }
  }

  public Position copy() {
    return new Position(row, column);
  }

}
