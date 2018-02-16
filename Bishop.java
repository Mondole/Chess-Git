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
public class Bishop extends Piece
{
    private boolean hasMove = false;
    private int scoreByPos = 0;
    public Bishop(Player _player)
    {
        this.player = _player;
        this.type = Piece.PieceType.BISHOP;
        _player.addActivePiece(this);
    }

    @Override
    public Tile[] getValidTiles()
    {
        Tile[] ret = null;
        boolean ur = true;    //limit for obstruction on Uper Right
        boolean dr = true;    //limit for obstruction on Down Right
        boolean dl = true;    //limit for obstruction on Down Left
        boolean ul = true;    //limit for obstruction on Uper Left
             
        try
        {
            Tile a = getNextDiagonalTile(this.getTile(), true, true);
            int c = 0;
            do
            {
                if (c > 0)
                {
                    a = getNextDiagonalTile(a, true, true);
                }
                else
                {
                    c++;
                }
                if (a.isAviableForCapture(this))
                {
                    ur = false;
                    //scoreByPos ++;
                }
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            } while (ur && isAviableNextDiagonalTile(a, true, true));      
        }catch(Exception e){}
        
        try
        {
            Tile a = getNextDiagonalTile(this.getTile(), true, false);
            int c = 0;
            do
            {
                if (c > 0)
                {
                    a = getNextDiagonalTile(a, true, false);
                }
                else
                {
                    c++;
                }
                if (a.isAviableForCapture(this))
                {
                    ul = false;
                }
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            } while (ul && isAviableNextDiagonalTile(a, true, false));          
        }catch(Exception e){}
        
        try
        {
            Tile a = getNextDiagonalTile(this.getTile(), false, true);
            int c = 0;
            do
            {
                if (c > 0)
                {
                    a = getNextDiagonalTile(a, false, true);
                }
                else
                {
                    c++;
                }
                if (a.isAviableForCapture(this))
                {
                    dr = false;
                }
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            } while (dr && isAviableNextDiagonalTile(a, false, true));           
        }catch(Exception e){}
        
        try
        {
            Tile a = getNextDiagonalTile(this.getTile(), false, false);
            int c = 0;
            do
            {
                if (c > 0)
                {
                    a = getNextDiagonalTile(a, false, false);
                }
                else
                {
                    c++;
                }
                if (a.isAviableForCapture(this))
                {
                    dl = false;
                }
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            } while (dl && isAviableNextDiagonalTile(a, false, false));          
        }catch(Exception e){} 
        return ret;
    }
    public Tile getNextDiagonalTile(Tile t, boolean up, boolean right)
    {
        int fstLine = t.getFirstIndex();
        int scdColumn = t.getSecondIndex();
        Tile c;
        Tile r = null;
        try
        {
            if (up && right)
            {
                c = this.getChessBoard().squares[fstLine + 1][scdColumn + 1];
                if (c.isAviableForPiece(this))
                {
                    r = c;
                }
            }
            else if (!up &&right)
            {
                c = this.getChessBoard().squares[fstLine - 1][scdColumn + 1];
                if (c.isAviableForPiece(this))
                {
                    r = c;
                }
            }
            else if (up && !right)
            {
                c = this.getChessBoard().squares[fstLine + 1][scdColumn - 1];
                if (c.isAviableForPiece(this))
                {
                    r = c;
                }
            }
            else if (!up && !right)
            {
                c = this.getChessBoard().squares[fstLine - 1][scdColumn - 1];
                if (c.isAviableForPiece(this))
                {
                    r = c;
                }
            }
        }
        catch (Exception e){}
        return r;
    }
    public boolean isAviableNextDiagonalTile(Tile t, boolean up, boolean right)
    {
        int fstLine = t.getFirstIndex();
        int scdColumn = t.getSecondIndex();
        try
        {
            if (up && right)
            {
                return this.getChessBoard().squares[fstLine + 1][scdColumn + 1].isAviableForPiece(this);
            } 
            else if (!up && right)
            {
                return this.getChessBoard().squares[fstLine - 1][scdColumn + 1].isAviableForPiece(this);
            } 
            else if (up && !right)
            {
                return this.getChessBoard().squares[fstLine + 1][scdColumn - 1].isAviableForPiece(this);
            } 
            else if (!up && !right)
            {
                return this.getChessBoard().squares[fstLine - 1][scdColumn - 1].isAviableForPiece(this);
            }
        } 
        catch (Exception e)
        {
            return false;
        }
        return false;
    }

    @Override
    public String getSimbol(boolean spanish, boolean complete)
    {
        if (spanish)
        {
            if (complete)
            {
                return "ALFIL";
            }
            return "A";
        }
        else
        {
            if (complete)
            {
                return "BISHOP";
            }
           return "B"; 
        }      
    }

    @Override
    public void moved()
    {
        this.hasMove = true;
    }

    @Override
    public boolean hasBeenMoved()
    {
        return this.hasMove;
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
        return new Bishop(this.player);
    }

    @Override
    public Piece clone(Player p)
    {
        return new Bishop(p);
    }
}
