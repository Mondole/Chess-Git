/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameSet;

import Pieces.Piece;

/**
 *
 * @author VíctorEduardo
 */
public class Tile
{
    private char column;
    private int line;
    private ChessBoard board;
    
    private Piece piece;
    
    public Tile(int _line, char _col)
    {
        this.column = _col;
        this.line = _line;
    }
    public Tile clone()
    {
        Tile t = new Tile(this.line, this.column);
        t.board = this.board;
        t.piece = this.piece.clone();
        return t;
    }
    
    public String getCoordenate()
    {
        String c = String.valueOf(column);
        String l = String.valueOf(line);
        return c+l;
    }
    public char getColunm()
    {
        return this.column;
    }
    public char getLine()
    {
        String c = String.valueOf(this.line);
        return c.charAt(0);
    }
    public int getSecondIndex()
    {
        switch(column)
        {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                throw new IllegalArgumentException("Value of Column not allowed at < Square.getFirstIndex() >");
        }
    }
    public int getFirstIndex()
    {
        switch(line)
        {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            default:
                throw new IllegalArgumentException("Value of Column not allowed at < Square.getFirstIndex() >");
        }
    }
    
    public Piece getPiece()
    {
        return this.piece;
    }
    
    public static Tile getSquare(String coordenates, Tile[][] squaresOfBoard)
    {
        String coord = coordenates.toLowerCase();
        char line = coord.charAt(1);
        
        switch (line)
        {
            case '1':
                for (int i = 0 ; i< 8; i++)
                {
                    if (squaresOfBoard[0][i].getCoordenate().toLowerCase().equals(coord))
                    {
                        return squaresOfBoard[0][i];
                    }
                }
            case '2':
                for (int i = 0 ; i< 8; i++)
                {
                    if (squaresOfBoard[1][i].getCoordenate().toLowerCase().equals(coord))
                    {
                        return squaresOfBoard[1][i];
                    }
                }
            case '3':
                for (int i = 0 ; i< 8; i++)
                {
                    if (squaresOfBoard[2][i].getCoordenate().toLowerCase().equals(coord))
                    {
                        return squaresOfBoard[2][i];
                    }
                }
            case '4':
                for (int i = 0 ; i< 8; i++)
                {
                    if (squaresOfBoard[3][i].getCoordenate().toLowerCase().equals(coord))
                    {
                        return squaresOfBoard[3][i];
                    }
                }
            case '5':
                for (int i = 0 ; i< 8; i++)
                {
                    if (squaresOfBoard[4][i].getCoordenate().toLowerCase().equals(coord))
                    {
                        return squaresOfBoard[4][i];
                    }
                }
            case '6':
                for (int i = 0 ; i< 8; i++)
                {
                    if (squaresOfBoard[5][i].getCoordenate().toLowerCase().equals(coord))
                    {
                        return squaresOfBoard[5][i];
                    }
                }
            case '7':
                for (int i = 0 ; i< 8; i++)
                {
                    if (squaresOfBoard[6][i].getCoordenate().toLowerCase().equals(coord))
                    {
                        return squaresOfBoard[6][i];
                    }
                }
            case '8':
                for (int i = 0 ; i< 8; i++)
                {
                    if (squaresOfBoard[7][i].getCoordenate().toLowerCase().equals(coord))
                    {
                        return squaresOfBoard[7][i];
                    }
                }
            default:
                throw new IllegalArgumentException("Coordenate not Found at < Square.getSquare() >");
        }
    }
    public boolean isAviableForPiece(Piece p)
    {
        if (this.isEmpty())
        {
            return true;
        }
        else if(p.getPlayer().equals((this.piece.getPlayer())))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * indicates whether the current {@code Tile}'s {@code Piece} is aviable for the capture of the {@code Piece} p
     * @param p piece to be able to capture in this {@code Tile}
     * @return true if the {@code Tile} is aviable for capturing its {@code Piece}
     */
    public boolean isAviableForCapture(Piece p)
    {
        if (this.isEmpty())
        {
            return false;
        }
        else if(p.getPlayer().equals((this.piece.getPlayer())))
        {
            return false;
        }
        else if (!p.getPlayer().equals((this.piece.getPlayer())))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    public boolean isEmpty()
    {
        if (this.piece == null)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public void setPiece(Piece p)
    {
        this.piece = p;
        p.setTile(this);
        p.setBoard(board);
    }
    public void setEmpty()
    {
        this.piece = null;
    }
    public void setBoard(ChessBoard cb)
    {
        this.board = cb;
    }
    

    public static Tile[] addToArray(Tile valueToAdd,Tile[] array)
    {
        try
        {
            Tile[] at = new Tile[array.length + 1];
            for (int i = 0; i < array.length; i++)
            {
                at[i] = array[i];
            }
            at[array.length] = valueToAdd;
            return at;
        } 
        catch (NullPointerException e)
        {
            return new Tile[]{valueToAdd};
        }
    }
    
    
    
    public boolean equals(Tile t)
    {
        if (t.getCoordenate().equals(this.getCoordenate()))
        {
            return true;
        } 
        else
        {
            return false;
        }
    }

}
