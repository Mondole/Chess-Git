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
public class Pawn extends Piece
{   
    private boolean hasMoved = false;
    private boolean beginOnTop;
    private int scoreByPos = 0;
    private boolean hasJustMovedTwo = false;
    private Pawn captureToPass;
    
    
    public Pawn(Player _player)
    {
        player = _player;
        if(player.getSide())
        {
            beginOnTop = false;
        }
        else
        {
            beginOnTop = true;
        }
        this.type = Piece.PieceType.PAWN;
        _player.addActivePiece(this);
    }
  
    
    public void promotion(Piece.PieceType pt)
    {
        Tile t = this.getTile();
        Player p = this.getPlayer();
        switch (pt)
        {
            case QUEEN:
                t.setPiece(new Queen(p));
                t.getPiece().setTile(t);
                this.getPlayer().justTakeOffActivePice((Piece)this);
                break;
            case ROOK:
                t.setPiece(new Rook(p));
                t.getPiece().setTile(t);
                this.getPlayer().justTakeOffActivePice((Piece)this);
                break;
            case BISHOP:
                t.setPiece(new Bishop(p));
                t.getPiece().setTile(t);
                this.getPlayer().justTakeOffActivePice((Piece)this);
                break;
            case KNIGHT:
                t.setPiece(new Knight(p));
                t.getPiece().setTile(t);
                this.getPlayer().justTakeOffActivePice((Piece)this);
                break;
            default:
                throw new IllegalArgumentException("Promotion Type not Allowed at < Pawn.promotion() >");
        }
    }

    public Pawn getPawnToCaptureDuPass()
    {
        return this.captureToPass;
    }
    
    public void setHasJustMovedTwo(boolean value)
    {
        this.hasJustMovedTwo = value;
    }
    public boolean HasJustMovedTwo()
    {
        return this.hasJustMovedTwo;
    }

    @Override
    public Tile[] getValidTiles()
    {
        Tile[] ret = null;
        int fstLine = this.getTile().getFirstIndex();
        int scdColumn = this.getTile().getSecondIndex();

        if (beginOnTop) //For "BLACK" pieces
        {
            // Pass Capture at left
            try
            {
                if (this.getChessBoard().squares[fstLine][scdColumn - 1].isAviableForCapture(this) && this.getChessBoard().squares[fstLine - 1][scdColumn - 1].isEmpty())
                {
                    if (this.getChessBoard().squares[fstLine][scdColumn - 1].getPiece().getType() == Piece.PieceType.PAWN)
                    {
                        Pawn pp = (Pawn) this.getChessBoard().squares[fstLine][scdColumn - 1].getPiece();
                        if (pp.HasJustMovedTwo())
                        {
                            captureToPass = pp;
                            Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn - 1];
                            ret = Tile.addToArray(a, ret);
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e){}
            // Pass Capture at Right
            try
            {
                if (this.getChessBoard().squares[fstLine][scdColumn + 1].isAviableForCapture(this) && this.getChessBoard().squares[fstLine - 1][scdColumn + 1].isEmpty())
                {
                    if (this.getChessBoard().squares[fstLine][scdColumn + 1].getPiece().getType() == Piece.PieceType.PAWN)
                    {
                        Pawn pp = (Pawn) this.getChessBoard().squares[fstLine][scdColumn + 1].getPiece();
                        if (pp.HasJustMovedTwo())
                        {
                            captureToPass = pp;
                            Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn + 1];
                            ret = Tile.addToArray(a, ret);
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e){}
            if (hasMoved)
            {
                try // Try capture at left
                {
                    if (this.getChessBoard().squares[fstLine - 1][scdColumn - 1].isAviableForCapture(this))
                    {
                        Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn - 1];
                        ret = Tile.addToArray(a, ret);
                        scoreByPos++;
                    }
                } catch (IndexOutOfBoundsException e){}

                try //try capture at right
                {
                    if (this.getChessBoard().squares[fstLine - 1][scdColumn + 1].isAviableForCapture(this))
                    {
                        Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn + 1];
                        ret = Tile.addToArray(a, ret);
                        scoreByPos++;
                    }
                } catch (IndexOutOfBoundsException e){}

                try // try advance one
                {
                    if (this.getChessBoard().squares[fstLine - 1][scdColumn].isEmpty())
                    {
                        Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn];
                        ret = Tile.addToArray(a, ret);
                    }
                } catch (IndexOutOfBoundsException e){}
            } else
            {
                // Try to move for first Time one
                if (this.getChessBoard().squares[fstLine - 1][scdColumn].isEmpty())
                {
                    Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn];
                    ret = Tile.addToArray(a, ret);
                }
                // Try to move for first Time two
                if (this.getChessBoard().squares[fstLine - 1][scdColumn].isEmpty()
                        && this.getChessBoard().squares[fstLine - 2][scdColumn].isEmpty())
                {
                    Tile b = this.getChessBoard().squares[fstLine - 2][scdColumn];
                    ret = Tile.addToArray(b, ret);
                }

                try // Try capture at left
                {
                    if (this.getChessBoard().squares[fstLine - 1][scdColumn - 1].isAviableForCapture(this))
                    {
                        Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn - 1];
                        ret = Tile.addToArray(a, ret);
                        scoreByPos++;
                    }
                } catch (IndexOutOfBoundsException e)
                {
                }

                try //try capture at right
                {
                    if (this.getChessBoard().squares[fstLine - 1][scdColumn + 1].isAviableForCapture(this))
                    {
                        Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn + 1];
                        ret = Tile.addToArray(a, ret);
                        scoreByPos++;
                    }
                } catch (IndexOutOfBoundsException e)
                {
                }
            }
        } else           //for WHITE pieces
        {
            // Pass Capture at left
            try
            {
                if (this.getChessBoard().squares[fstLine][scdColumn - 1].isAviableForCapture(this) && this.getChessBoard().squares[fstLine + 1][scdColumn - 1].isEmpty())
                {
                    if (this.getChessBoard().squares[fstLine][scdColumn - 1].getPiece().getType() == Piece.PieceType.PAWN)
                    {
                        Pawn pp = (Pawn) this.getChessBoard().squares[fstLine][scdColumn - 1].getPiece();
                        if (pp.HasJustMovedTwo())
                        {
                            captureToPass = pp;
                            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn - 1];
                            ret = Tile.addToArray(a, ret);
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e){}
            // Pass Capture at Right
            try
            {
                if (this.getChessBoard().squares[fstLine][scdColumn + 1].isAviableForCapture(this) && this.getChessBoard().squares[fstLine + 1][scdColumn + 1].isEmpty())
                {
                    if (this.getChessBoard().squares[fstLine][scdColumn + 1].getPiece().getType() == Piece.PieceType.PAWN)
                    {
                        Pawn pp = (Pawn) this.getChessBoard().squares[fstLine][scdColumn + 1].getPiece();
                        if (pp.HasJustMovedTwo())
                        {
                            captureToPass = pp;
                            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn + 1];
                            ret = Tile.addToArray(a, ret);
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e){ }

                
                if (hasMoved)
                {
                    try    // Try capture at left
                    {
                        if (this.getChessBoard().squares[fstLine + 1][scdColumn - 1].isAviableForCapture(this))
                        {
                            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn-1];
                            ret = Tile.addToArray(a, ret);
                            scoreByPos ++;
                        }
                    }
                    catch(IndexOutOfBoundsException e){}
                    
                    try //try capture at right
                    {
                        if (this.getChessBoard().squares[fstLine + 1][scdColumn + 1].isAviableForCapture(this))
                        {
                            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn+1];
                            ret = Tile.addToArray(a, ret);
                            scoreByPos ++;
                        }
                    }
                    catch(IndexOutOfBoundsException e){}
                    
                    try // try advance one
                    {
                        if (this.getChessBoard().squares[fstLine + 1][scdColumn].isEmpty())
                        {
                            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn];
                            ret = Tile.addToArray(a, ret);
                        }
                    }
                    catch(IndexOutOfBoundsException e){}
                } 
                else
                {
                    // Try to move for first Time one
                    if (this.getChessBoard().squares[fstLine + 1][scdColumn].isEmpty())
                    {
                        Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn];   
                        ret = Tile.addToArray(a, ret);
                    }
                    // Try to move for first Time two
                    if (this.getChessBoard().squares[fstLine + 1][scdColumn].isEmpty()
                            && this.getChessBoard().squares[fstLine + 2][scdColumn].isEmpty())
                    {
                        Tile b = this.getChessBoard().squares[fstLine + 2][scdColumn];
                        Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn];
                        ret = Tile.addToArray(b, ret);
                    }
                    try    // Try capture at left
                    {
                        if (this.getChessBoard().squares[fstLine + 1][scdColumn - 1].isAviableForCapture(this))
                        {
                            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn-1];
                            ret = Tile.addToArray(a, ret);
                            scoreByPos ++;
                        }
                    }
                    catch(IndexOutOfBoundsException e){}
                    
                    try //try capture at right
                    {
                        if (this.getChessBoard().squares[fstLine + 1][scdColumn + 1].isAviableForCapture(this))
                        {
                            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn+1];
                            ret = Tile.addToArray(a, ret);
                            scoreByPos ++;
                        }
                    }
                    catch(IndexOutOfBoundsException e){}
                }
            }
        return ret;
    }   

    @Override
    public String getSimbol(boolean spanish, boolean complete)
    {
        if (spanish)
        {
            if (complete)
            {
                return "PEON";
            }
            return "P";
        }
        else
        {
            if (complete)
            {
                return "PAWN";
            }
           return "P"; 
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
        Pawn p = new Pawn(this.player);
        p.beginOnTop = this.beginOnTop;
        p.captureToPass = this.captureToPass;
        return p;
    }

    @Override
    public Piece clone(Player p)
    {
        Pawn a = new Pawn(p);
        a.beginOnTop = this.beginOnTop;
        a.captureToPass = this.captureToPass;    
        return a;
    }
}
