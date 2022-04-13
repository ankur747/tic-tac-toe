package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {
    private int emptyCells;
    private List<List<BoardCell>> cells = new ArrayList<>();

    public void printBoard()
    {
        StringBuilder sbuilder = new StringBuilder();
        for (List<BoardCell> row:cells)
        {
            for (BoardCell cell:row)
            {
                if( cell.getSymbol() == Symbol.EMPTY)
                    sbuilder.append("- ");
                else
                    sbuilder.append(cell.getSymbol().toString() +" ");
            }
            sbuilder.append(System.getProperty("line.separator"));
        }
        System.out.println(sbuilder.toString());
    }
    public Board(int size)
    {
        emptyCells = size* size;;
        for (int  i = 0;i<size;i++)
        {
            ArrayList<BoardCell> row = new ArrayList<>();
            for(int j = 0;j<size;j++)
            {
                row.add(new BoardCell());
            }
            this.cells.add(row);
        }
    }

    public Boolean isMoveValid(int row, int col) {
        if(cells.get(row).get(col).getSymbol() == Symbol.EMPTY)
            return true;
        return false;
    }

    public BoardCell markCell(int row, int col, Symbol symbol) {
        BoardCell cell = cells.get(row).get(col);
        cell.setSymbol(symbol);
        emptyCells--;
        return cell;
    }

}
