package GameSet;

import Pieces.King;
import Pieces.Rook;
import Pieces.Bishop;
import Pieces.Queen;
import Pieces.Knight;
import Pieces.Pawn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VíctorEduardo
 */
public class ChessBoard
{
    public Tile[][] squares;
    public static int[] lines = {1,2,3,4,5,6,7,8};
    public static char[] columns = {'a','b','c','d','e','f','g','h'};
    private Player player1;
    private Player player2;
    private int moveNumber = 1;
    private String moveLog = "";
    private boolean lang;
    private boolean complete;
    
    private King k1;
    private King k2;
    private boolean turn = true;
    
    public ChessBoard(Player p1 , Player p2, boolean _lang, boolean _complete)
    {
        player1 = p1;
        player2 = p2;
        player1.setOponent(player2);
        player2.setOponent(player1);
        createBoard();
        setStartPositions();
        lang = _lang;
        complete = _complete;
    }
    private ChessBoard()
    {
        
    }
    
    private void createBoard()
    {
        squares = new Tile[8][8];
        for (int i = 0; i < lines.length; i++)
        {
            for (int j = 0; j< columns.length ; j++)
            {
                squares[i][j] = new Tile(lines[i], columns[j]);
                squares[i][j].setBoard(this);
            }
        }
    }
    
    private void setStartPositions()
    {
//        this.squares[4][4].setPiece(new Rook(player1));
//        this.squares[4][5].setPiece(new Queen(player2));
//        this.squares[5][4].setPiece(new Queen(player1));
//        this.squares[3][4].setPiece(new Knight(player2));
//        this.squares[2][2].setPiece(new Bishop(player2));
        
        this.squares[1][0].setPiece(new Pawn(player1));
        this.squares[1][1].setPiece(new Pawn(player1));
        this.squares[1][2].setPiece(new Pawn(player1));
        this.squares[1][3].setPiece(new Pawn(player1));
        this.squares[1][4].setPiece(new Pawn(player1));
        this.squares[1][5].setPiece(new Pawn(player1));
        this.squares[1][6].setPiece(new Pawn(player1));
        this.squares[1][7].setPiece(new Pawn(player1));
        
        this.squares[0][0].setPiece(new Rook(player1));
        this.squares[0][7].setPiece(new Rook(player1));
        
        this.squares[0][1].setPiece(new Knight(player1));
        this.squares[0][6].setPiece(new Knight(player1));
        
        this.squares[0][2].setPiece(new Bishop(player1));
        this.squares[0][5].setPiece(new Bishop(player1));
        k1 = new King(player1);
        this.squares[0][4].setPiece(k1);
        this.squares[0][3].setPiece(new Queen(player1));

        
        this.squares[6][0].setPiece(new Pawn(player2));
        this.squares[6][1].setPiece(new Pawn(player2));
        this.squares[6][2].setPiece(new Pawn(player2));
        this.squares[6][3].setPiece(new Pawn(player2));
        this.squares[6][4].setPiece(new Pawn(player2));
        this.squares[6][5].setPiece(new Pawn(player2));
        this.squares[6][6].setPiece(new Pawn(player2));
        this.squares[6][7].setPiece(new Pawn(player2));
        
        this.squares[7][0].setPiece(new Rook(player2));
        this.squares[7][7].setPiece(new Rook(player2));
        
        this.squares[7][1].setPiece(new Knight(player2));
        this.squares[7][6].setPiece(new Knight(player2));
        
        this.squares[7][2].setPiece(new Bishop(player2));
        this.squares[7][5].setPiece(new Bishop(player2));
        
        k2 = new King(player2);
        this.squares[7][4].setPiece(k2);
        this.squares[7][3].setPiece(new Queen(player2));
        
        k1.setRooks();
        k2.setRooks();
        
        
    }
    
    public ChessBoard clone()
    {
        ChessBoard cs = new ChessBoard();
        return cs;
    }
    
    public boolean getTurn()
    {
        return this.turn;
    }
    public String getLog()
    {
        return this.moveLog;
    }
    public int getMoveNumber()
    {
        return this.moveNumber;
    }   
    public Player getPlayer(int playerNumber)
    {
        if (playerNumber == 1)
        {
            return this.player1;
        }
        else if (playerNumber == 2)
        {
            return this.player2;
        }
        else
        {
            throw new IllegalArgumentException("Player Number no valid at < Player.getPlayer() >");
        }
    }
    public boolean getLang()
    {
        return this.lang;
    }
    public boolean getComplete()
    {
        return this.complete;
    }
    
    public void SwitchTurn()
    {
        if (this.turn)
        {
            this.turn = false;
        }
        else
        {
            this.turn = true;
        }
    }
    
    public void updateLog(String s)
    {   
        this.moveLog += String.valueOf(moveNumber);
        this.moveLog += ".";
        this.moveLog += s;
        this.moveLog += " ";
        this.moveLog += "\n";
        this.moveNumber ++;
    }   
}
