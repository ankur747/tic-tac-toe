package services;

import exceptions.InvalidMoveException;
import lombok.Getter;
import models.Game;
import models.Player;
import models.Symbol;

import java.util.List;

@Getter
public class GameService {
    private Game game;
    public void createGame(int size, List<Player> players)
    {
        game = new Game(size,players);
    }

    public void makeMove(int row, int col) throws InvalidMoveException
    {
       game.makeMove(row,col);
       game.getBoard().printBoard();
    }

    public Boolean continuePlayingGame()
    {
        if(getWinner() != null) {
            System.out.println(getWinner());
            return false;
        }
        else if(game.getBoard().getEmptyCells()>=0)
            return true;

        return false;
    }

    public Player getWinner()
    {
        Player winner = hozCrossed();
        if(winner == null)
            winner = verCrossed();
        if( winner ==  null)
            winner = leftDiagCrossed();
        if( winner == null)
            winner = rightDiagCrossed();
        return winner;
    }

    private Player getPlayerBySymbol(Symbol sy)
    {
        for (int k =0;k<game.getPlayers().size();k++)
        {
            if(game.getPlayers().get(k).getSymbol() == sy)
                return game.getPlayers().get(k);
        }
        return null;
    }

    private Player hozCrossed()
    {
        for(int i = 0;i<game.getBoard().getCells().size();i++)
        {
            Symbol sy =  game.getBoard().getCells().get(i).get(0).getSymbol();
            Boolean crossed = true;
            for(int j = 1;j<game.getBoard().getCells().size();j++)
            {
                if(sy == game.getBoard().getCells().get(i).get(j).getSymbol())
                    continue;

                else{
                    crossed=false;
                    break;
                }
            }
            if(crossed)
            {
                return getPlayerBySymbol(sy);
            }
        }
        return null;
    }

    private Player verCrossed()
    {
        for(int i = 0;i<game.getBoard().getCells().size();i++)
        {
            Symbol sy =  game.getBoard().getCells().get(0).get(i).getSymbol();
            Boolean crossed = true;
            for(int j = 1;j<game.getBoard().getCells().size();j++)
            {
                if(sy == game.getBoard().getCells().get(j).get(i).getSymbol())
                    continue;

                else{
                    crossed=false;
                    break;
                }
            }
            if(crossed)
            {
                return getPlayerBySymbol(sy);
            }
        }
        return null;
    }

    private Player leftDiagCrossed()
    {
        Symbol sy =  game.getBoard().getCells().get(0).get(0).getSymbol();
        Boolean crossed = true;
        for(int i = 1;i<game.getBoard().getCells().size();i++)
        {
                if(sy == game.getBoard().getCells().get(i).get(i).getSymbol())
                    continue;

                else{
                    crossed=false;
                    break;
                }
        }
        if(crossed)
        {
            return getPlayerBySymbol(sy);
        }
        return null;
    }

    private Player rightDiagCrossed()
    {
        Boolean crossed = true;
        int j=0;
        Symbol sy =  game.getBoard().getCells().get(game.getBoard().getCells().size()-1).get(j++).getSymbol();
        for(int i = game.getBoard().getCells().size()-2; i>=0;i--)
        {
            if(sy == game.getBoard().getCells().get(i).get(j++).getSymbol())
                continue;

            else{
                crossed=false;
                break;
            }
        }
        if(crossed)
        {
            return getPlayerBySymbol(sy);
        }
        return null;
    }

}
