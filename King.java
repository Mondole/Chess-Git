/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import GameSet.Player;
import GameSet.Tile;

/**
 *
 * @author VíctorEduardo
 */
public class King extends Piece
{    
    private boolean hasMoved = false;
    private int scoreByPos = 0;
    private Rook shortCastlingR;
    private Rook longCastlingR;
    public King (Player _player)
    {
        this.player = _player;
        this.type = Piece.PieceType.KING;
        _player.addActivePiece(this);
    }

    
    public void setRooks()
    {
        if (this.player.getSide())
        {
            try
            {
                longCastlingR = (Rook) this.getChessBoard().squares[0][0].getPiece();
            } catch (NullPointerException e)
            {
                longCastlingR = null;
            }

            try
            {
                shortCastlingR = (Rook) this.getChessBoard().squares[0][7].getPiece();
            } catch (NullPointerException e)
            {
                shortCastlingR = null;
            }
        } else
        {
            try
            {
                longCastlingR = (Rook) this.getChessBoard().squares[7][0].getPiece();
            } catch (NullPointerException e)
            {
                longCastlingR = null;
            }
            try
            {
                shortCastlingR = (Rook) this.getChessBoard().squares[7][7].getPiece();
            } catch (NullPointerException e)
            {
                shortCastlingR = null;
            }
        }
    }
    
    public boolean isCastlingAviable(boolean longCastling)
    {
        if (!shortCastlingR.hasBeenMoved() && !this.hasBeenMoved() && !longCastling)
        {
            if (this.getChessBoard().squares[shortCastlingR.getTile().getFirstIndex()][shortCastlingR.getTile().getSecondIndex() - 1].isEmpty()
                    && this.getChessBoard().squares[shortCastlingR.getTile().getFirstIndex()][shortCastlingR.getTile().getSecondIndex() - 2].isEmpty())
            {
                return true;
            }
        }
        else if (!longCastlingR.hasBeenMoved() && !this.hasBeenMoved() && longCastling)
        {
            if (this.getChessBoard().squares[longCastlingR.getTile().getFirstIndex()][longCastlingR.getTile().getSecondIndex() + 1].isEmpty()
                    && this.getChessBoard().squares[longCastlingR.getTile().getFirstIndex()][longCastlingR.getTile().getSecondIndex() + 2].isEmpty()
                    && this.getChessBoard().squares[longCastlingR.getTile().getFirstIndex()][longCastlingR.getTile().getSecondIndex() + 3].isEmpty())
            {
                return true;
            }
        }
        return false;
    }
    
    public Rook getShortCastlingRook()
    {
        return shortCastlingR;
    }
    public Rook getLongCastlingRook()
    {
        return longCastlingR;
    }
    
    @Override
    public Tile[] getValidTiles()
    {
        Tile[] ret = null;
        int fstLine = this.getTile().getFirstIndex();
        int scdColumn = this.getTile().getSecondIndex();
        try  //UR
        {
            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn + 1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try  //UU
        {
            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try  //UL
        {  
            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn - 1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try  //LL
        {
            Tile a = this.getChessBoard().squares[fstLine][scdColumn -1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try  //DL
        {
            Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn - 1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try  //DD
        {
            Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try  //DR
        {
            Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn + 1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }           
        }catch(ArrayIndexOutOfBoundsException e){}
        try  //RR
        {
            Tile a = this.getChessBoard().squares[fstLine][scdColumn + 1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        
        
        
        //CASTLING
        try //LONG CASTLING
        {
            if (isCastlingAviable(true))
            {
                Tile a = this.getChessBoard().squares[fstLine][scdColumn - 2];
                if (a.isAviableForPiece(this))
                {
                    ret = Tile.addToArray(a, ret);
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        
        try //SHORT CASTLING
        {
            if (isCastlingAviable(false))
            {
                Tile a = this.getChessBoard().squares[fstLine][scdColumn + 2];
                if (a.isAviableForPiece(this))
                {
                    ret = Tile.addToArray(a, ret);
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){}
            
        return ret;
    }
    
    @Override
    public String getSimbol(boolean spanish,boolean complete)
    {
        if (spanish)
        {
            if (complete)
            {
                return "REY";
            }
            return "R";
        }
        else
        {
            if (complete)
            {
                return "KING";
            }
           return "K"; 
        }   
    }

    @Override
    public void moved()
    {
        this.hasMoved = true;
    }

    @Override
    public boolean hasBeenMoved()
    {
        return this.hasMoved;
    }

    @Override
    public int getScoreByPosition()
    {
        this.scoreByPos = 0;
        getValidTiles();
        return this.scoreByPos;
    }

    @Override
    public Piece clone()
    {
        return new King(this.player);
    }

    @Override
    public Piece clone(Player p)
    {
        return new King(p);
    }
    
}
