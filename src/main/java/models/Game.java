package models;

import exceptions.InvalidMoveException;
import lombok.Getter;
import services.Constants;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
public class Game {

    private Board board;
    private List<Player> players;
    private Queue<Player> playerTurnQueue;

    public Game(int size, List<Player> players)
    {
        board = new Board(size);
        this.players = players;
        setPlayerTurnQueue(players);
    }

    private void setPlayerTurnQueue(List<Player> players)
    {
        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Constants.symbolPriorityMap.get(o1.getSymbol()) - Constants.symbolPriorityMap.get(o2.getSymbol());
            }
        });
        playerTurnQueue = new LinkedList<>(players);
    }

    public Player getPlayerForThisTurn()
    {
        Player currentPlayer =  playerTurnQueue.peek();
        playerTurnQueue.poll();
        playerTurnQueue.add(currentPlayer);
        return currentPlayer;
    }

    public void makeMove(int row, int col) throws InvalidMoveException
    {
        getPlayerForThisTurn().makeMove(board,row,col);
    }
}
