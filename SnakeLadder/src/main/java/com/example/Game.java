package com.example;

import java.util.Deque;
import java.util.LinkedList;

public class Game {

    Board board;
    Dice dice;
    Deque<Player> players = new LinkedList<>();
    Player winner;

    public Game()
    {
        initializeGame();
    }

    public void initializeGame()
    {
        board = new Board(10, 5, 4);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }

    private void addPlayers()
    {
        Player players1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);

        players.add(players1);
        players.add(player2);
    }

    public void startGame()
    {
        while(winner == null)
        {
            Player player = playerTurn();

            System.out.println("player turn is:" + player.id + " current position is: " + player.currentPosition);

            int diceNumber = dice.rollDice();

            int newPlayerPosition = player.currentPosition + diceNumber;
            newPlayerPosition = checkJump(newPlayerPosition);

            player.currentPosition = newPlayerPosition;

            System.out.println("player turn is:" + player.id + " new Position is: " + newPlayerPosition);

            if(newPlayerPosition>=board.cells.length*board.cells.length-1)
            {
                winner = player;
            }
        }

        System.out.println("WINNER IS : " + winner.id);
    }

    private Player playerTurn()
    {
        Player p1 = players.removeFirst();
        players.addLast(p1);
        return p1;
    }

    private int checkJump(int currPos)
    {
        if(currPos > board.cells.length * board.cells.length-1)
        {
            return currPos;
        }

        Cell cell = board.getCell(currPos);

        if(cell.jump != null && cell.jump.start == currPos)
        {
            String jumpBy = (cell.jump.start < cell.jump.end) ? "ladder" : "snake";
            System.out.println("jump done by: " + jumpBy);
            return cell.jump.end;
        }

        return currPos;
    }
}
