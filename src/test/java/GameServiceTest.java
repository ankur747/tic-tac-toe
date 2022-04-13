import exceptions.InvalidMoveException;
import models.Player;
import models.Symbol;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.GameService;

import java.util.ArrayList;
import java.util.List;

public class GameServiceTest {
    private GameService gameService;

    @Before
    public void setGameService()
    {
        gameService = new GameService();
    }

    @Test
    public void createGame()
    {
        Assert.assertNull("Game is not crated",gameService.getGame());
        List<Player> playerList =  new ArrayList<>();
        playerList.add(new Player("Test1", Symbol.X));
        playerList.add(new Player("Test2", Symbol.O));
        gameService.createGame(3,playerList);
        Assert.assertNotNull("Game created",gameService.getGame());
        Assert.assertEquals("Game created with 2 players",2,gameService.getGame().getPlayers().size());
    }

    @Test
    public void makeValidMoves()
    {
        List<Player> playerList =  new ArrayList<>();
        playerList.add(new Player("Test1", Symbol.X));
        playerList.add(new Player("Test2", Symbol.O));
        gameService.createGame(3,playerList);
        try {
            gameService.makeMove(0,0);
            Assert.assertEquals(Symbol.X, gameService.getGame().getBoard().getCells().get(0).get(0).getSymbol());
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = InvalidMoveException.class)
    public void makeInvalidMoves() throws InvalidMoveException
    {
        List<Player> playerList =  new ArrayList<>();
        playerList.add(new Player("Test1", Symbol.X));
        playerList.add(new Player("Test2", Symbol.O));
        gameService.createGame(3,playerList);
        gameService.makeMove(0,0);
        gameService.makeMove(0,0);
    }

    @Test
    public void hozCrossedWinGame()
    {
        List<Player> playerList =  new ArrayList<>();
        playerList.add(new Player("Test1", Symbol.X));
        playerList.add(new Player("Test2", Symbol.O));
        gameService.createGame(3,playerList);
        try {
            gameService.makeMove(0,0);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(1,0);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(0,1);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(1,1);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(0,2);
            Assert.assertFalse(gameService.continuePlayingGame());
            Assert.assertEquals(playerList.get(0), gameService.getWinner());
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void verCrossedWinGame()
    {
        List<Player> playerList =  new ArrayList<>();
        playerList.add(new Player("Test1", Symbol.X));
        playerList.add(new Player("Test2", Symbol.O));
        gameService.createGame(3,playerList);
        try {
            gameService.makeMove(0,1);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(0,0);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(2,2);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(1,0);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(0,2);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(2,0);
            Assert.assertEquals(playerList.get(1), gameService.getWinner());
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void leftDiagCrossedWinGame()
    {
        List<Player> playerList =  new ArrayList<>();
        playerList.add(new Player("Test1", Symbol.X));
        playerList.add(new Player("Test2", Symbol.O));
        gameService.createGame(3,playerList);
        try {
            gameService.makeMove(0,1);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(0,0);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(2,1);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(1,1);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(0,2);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(2,2);
            Assert.assertFalse(gameService.continuePlayingGame());
            Assert.assertEquals(playerList.get(1), gameService.getWinner());
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void rightDiagCrossedWinGame()
    {
        List<Player> playerList =  new ArrayList<>();
        playerList.add(new Player("Test1", Symbol.X));
        playerList.add(new Player("Test2", Symbol.O));
        gameService.createGame(3,playerList);
        try {
            gameService.makeMove(2,0);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(0,0);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(1,1);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(1,0);
            Assert.assertTrue(gameService.continuePlayingGame());
            gameService.makeMove(0,2);
            Assert.assertFalse(gameService.continuePlayingGame());
            Assert.assertEquals(playerList.get(0), gameService.getWinner());
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }


}
