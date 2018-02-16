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
public class Knight extends Piece
{
    private boolean hasMove = false;
    private int scoreByPos = 0;                //Score gotten for controlling a Tile
    public Knight (Player _player)
    {
        this.player = _player;
        this.type = Piece.PieceType.KNIGHT;
        _player.addActivePiece(this);
    }
    @Override
    public Tile[] getValidTiles()
    {
        Tile[] ret = null;
        int fstLine = this.getTile().getFirstIndex();
        int scdColumn = this.getTile().getSecondIndex();
        
        try // urr
        { 
            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn + 2];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try // uur
        { 
            Tile a = this.getChessBoard().squares[fstLine + 2][scdColumn + 1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try // ull
        { 
            Tile a = this.getChessBoard().squares[fstLine + 1][scdColumn - 2];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try // uul
        { 
            Tile a = this.getChessBoard().squares[fstLine + 2][scdColumn - 1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try // drr
        { 
            Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn + 2];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try // ddr
        { 
            Tile a = this.getChessBoard().squares[fstLine - 2][scdColumn + 1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try // ddl
        { 
            Tile a = this.getChessBoard().squares[fstLine - 2][scdColumn - 1];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        try // dll
        { 
            Tile a = this.getChessBoard().squares[fstLine - 1][scdColumn - 2];
            if (a.isAviableForPiece(this))
            {
                ret = Tile.addToArray(a, ret);
                scoreByPos ++;
            }
        }catch(ArrayIndexOutOfBoundsException e){}
        
        return ret;
    }

    @Override
    public String getSimbol(boolean spanish, boolean complete)
    {
        if (spanish)
        {
            if (complete)
            {
                return "CABALLO";
            }
            return "C";
        }
        else
        {
            if (complete)
            {
                return "KNIGHT";
            }
           return "N"; 
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
        return new Knight(this.player);
    }

    @Override
    public Piece clone(Player p)
    {
        return new Knight(p);
    }
}
