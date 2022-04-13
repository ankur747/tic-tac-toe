import exceptions.InvalidMoveException;
import models.Player;
import models.Symbol;
import services.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        GameService gameService = new GameService();
        List<Player> playerList =  new ArrayList<>();
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter player details");
        List<String> player1Input = Arrays.stream(br.readLine().toString().split("\s")).toList();
        List<String> player2Input = Arrays.stream(br.readLine().toString().split("\s")).toList();
        playerList.add(new Player(player1Input.get(1), Symbol.valueOf(player1Input.get(0))));
        playerList.add(new Player(player2Input.get(1), Symbol.valueOf(player2Input.get(0))));
        gameService.createGame(3, playerList);
        gameService.getGame().getBoard().printBoard();
        while(true)
        {
            String input = br.readLine().toString();
            if(input.equalsIgnoreCase("exit"))
            {
                System.out.println("Game Over");
                break;
            }
            else{
                List<String> cellValues = Arrays.stream(input.split("\s")).toList();
                try {
                    gameService.makeMove(Integer.parseInt(cellValues.get(0))-1,Integer.parseInt(cellValues.get(1))-1);
                    if(gameService.continuePlayingGame())
                    {
                        System.out.println("Game Over.");
                        break;
                    }
                } catch (InvalidMoveException e)
                {
                    System.out.println(e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("provide correct input");
                }
            }
        }
    }
}
