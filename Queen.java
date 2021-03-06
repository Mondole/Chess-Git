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
public class Queen extends Piece
{
    private boolean hasMove = false;
    private int scoreByPos = 0;
    public Queen(Player _player)
    {
        this.player = _player;
        this.type = Piece.PieceType.QUEEN;
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
        
        boolean up = true;       //limit for obstruction on Up
        boolean down = true;     //limit for obstruction on Down
        boolean right = true;    //limit for obstruction on Right
        boolean left = true;     //limit for obstruction on Left
        
        //BISHOP MOVEMENT STARTS
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
        
        //ROOK MOVEMENT STARTS
        try
        {   
            Tile a = getNextLinearTile(3, this.getTile());
            int c = 0;
            do
            {
                if (c > 0)
                {
                    a = getNextLinearTile(3, a);
                }
                else
                {
                    c++;
                }
                if (a.isAviableForCapture(this))
                {
                    up = false;
                }
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            } while (up && isAviableNextLinearTile(3, a));          
        }catch(Exception e){}
        
        try
        {   
            Tile a = getNextLinearTile(2, this.getTile());
            int c = 0;
            do
            {
                if (c > 0)
                {
                    a = getNextLinearTile(2, a);
                }
                else
                {
                    c++;
                }
                if (a.isAviableForCapture(this))
                {
                    left = false;
                }
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            } while (left && isAviableNextLinearTile(2, a));          
        }catch(Exception e){}
        
        try
        {   
            Tile a = getNextLinearTile(1, this.getTile());
            int c = 0;
            do
            {
                if (c > 0)
                {
                    a = getNextLinearTile(1, a);
                }
                else
                {
                    c++;
                }
                if (a.isAviableForCapture(this))
                {
                    right = false;
                }
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            } while (right && isAviableNextLinearTile(1, a));          
        }catch(Exception e){}
        
        try
        {   
            Tile a = getNextLinearTile(4, this.getTile());
            int c = 0;
            do
            {
                if (c > 0)
                {
                    a = getNextLinearTile(4, a);
                }
                else
                {
                    c++;
                }
                if (a.isAviableForCapture(this))
                {
                    down = false;
                }
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            } while (down && isAviableNextLinearTile(4, a));          
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
        catch (ArrayIndexOutOfBoundsException e){}
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
        catch (ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return false;
    }
    
    /**
     *get the next linear {@code Tile} from the position of the current Piece in the given orientation
     * @param orientation an {@code int} for the orientation being 1 = right , 2 = left , 3 = up , 4 = down
     * @param t {@code Tile} to be the starting point
     * @return the Neighbor {@code Tile} of the current {@code Piece} Position
     */
    public Tile getNextLinearTile(int orientation, Tile t)
    {
        int fstLine = t.getFirstIndex();
        int scdColumn = t.getSecondIndex();
        Tile c;
        Tile r = null;
        try
        {
            if (orientation == 1)
            {
                c = this.getChessBoard().squares[fstLine][scdColumn + 1];
                if (c.isAviableForPiece(this))
                {
                    r = c;
                }
            }
            else if (orientation == 2)
            {
                c = this.getChessBoard().squares[fstLine][scdColumn - 1];
                if (c.isAviableForPiece(this))
                {
                    r = c;
                }
            }
            else if (orientation == 3)
            {
                c = this.getChessBoard().squares[fstLine + 1][scdColumn];
                if (c.isAviableForPiece(this))
                {
                    r = c;
                }
            }
            else if (orientation == 4)
            {
                c = this.getChessBoard().squares[fstLine - 1][scdColumn];
                if (c.isAviableForPiece(this))
                {
                    r = c;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){}
        return r;
    }
    
    /**
     *Check whether the next linear {@code Tile} from the position of the current Piece in the given orientation is Aviable
     * @param orientation an {@code int} for the orientation being 1 = right , 2 = left , 3 = up , 4 = down
     * @param t {@code Tile} to be the starting point
     * @return true if the next {@code Tile} of the current {@code Piece} Position in the current orientation is aviable
     */
    public boolean isAviableNextLinearTile(int orientation, Tile t)
    {
        int fstLine = t.getFirstIndex();
        int scdColumn = t.getSecondIndex();
        try
        {
            if (orientation == 1)
            {
                return this.getChessBoard().squares[fstLine][scdColumn + 1].isAviableForPiece(this);
            } 
            else if (orientation == 2)
            {
                return this.getChessBoard().squares[fstLine][scdColumn - 1].isAviableForPiece(this);
            } 
            else if (orientation == 3)
            {
                return this.getChessBoard().squares[fstLine + 1][scdColumn].isAviableForPiece(this);
            } 
            else if (orientation == 4)
            {
                return this.getChessBoard().squares[fstLine - 1][scdColumn].isAviableForPiece(this);
            }
        } 
        catch (ArrayIndexOutOfBoundsException e)
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
                return "DAMA";
            }
            return "D";
        }
        else
        {
            if (complete)
            {
                return "QUEEN";
            }
           return "Q"; 
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
        return hasMove;
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
        return new Queen(this.player);
    }

    @Override
    public Piece clone(Player p)
    {
        return new Queen(p);
    }
}
