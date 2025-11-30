package com.example;

import java.util.concurrent.ThreadLocalRandom;

public class Board {

    Cell[][] cells;

    Board(int boardSize, int numberOfSnakes, int numberOfLadder)
    {
        initializeCells(boardSize);
        addSnakesLadder(cells, numberOfLadder, numberOfLadder);
    }

    private void initializeCells(int boardSize)
    {
        cells = new Cell[boardSize][boardSize];

        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                 Cell cellObj = new Cell();
                 cells[i][j] = cellObj;
            }
        }
    }

    private void addSnakesLadder(Cell[][] cells, int numberOfSnake, int numberOfLadder)
    {
        while(numberOfLadder > 0)
        {
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length*cells.length-1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length-1);

            if(snakeTail>=snakeHead)
            {
               continue;
            }

            Jump snakeObj = new Jump();
            snakeObj.start = snakeHead;
            snakeObj.end = snakeTail;

            Cell cell = getCell(snakeHead);
            cell.jump = snakeObj;

            numberOfSnake--;

        }

        while(numberOfLadder > 0)
        {
            int str = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length-1);
            int end = ThreadLocalRandom.current().nextInt(1, cells.length* cells.length-1);

            if(str>=end)
                continue;

            Jump ladderObj = new Jump();
            ladderObj.start = str;
            ladderObj.end = end;

            Cell cell = getCell(str);
            cell.jump = ladderObj;
        }
    }

    Cell getCell(int playerPosition)
    {
        int r = playerPosition/cells.length;
        int c = playerPosition%cells.length;

        return cells[r][c];
    }

}
