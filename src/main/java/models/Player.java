package models;

import exceptions.InvalidMoveException;
import lombok.Data;

@Data
public class Player {
    private String name;
    private Symbol symbol;

    public Player(String name, Symbol symbol)
    {
        this.name = name;
        this.symbol = symbol;
    }
    public BoardCell makeMove(Board board, int row, int col) throws InvalidMoveException
    {
        if(board.isMoveValid(row,col))
            return board.markCell(row, col, symbol);
        throw new InvalidMoveException();
    }
}
