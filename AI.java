/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameSet;

import Pieces.Piece;

/**
 *This class is a {@code Player} extended class that simulate an oponent in a chess game
 * @author VíctorEduardo
 */
public class AI extends Player
{
    
    private static final int MAX_DEPTH = 10;
    
    public static AI BLACK_AI = new AI("COMPUTADORA", false, MAX_DEPTH);
    public static AI WHITE_AI = new AI("COMPUTADORA", true, 6);
    
    private int depth;
    
    private AI(String _name, boolean _side, int _depth)
    {
        super(_name, _side);
        if (_depth <= MAX_DEPTH)
        {
            this.depth = _depth;
        } else
        {
            throw new IllegalArgumentException("The given depth is greater than the maxim depth allowed at < AI.CTOR >");
        }
    }

    public void getNextMove()
    {

    }
    public int getDepth()
    {
        return this.depth;
    }
    public Move[] getPieceMoves(Piece piece) throws NullPointerException
    {
        Move[] moves;
        int i = 0;
        for (Tile t : piece.getValidTiles())
        {
            i++;
        }
        try
        {
            moves = new Move[i];
        } catch (Exception e)
        {throw new NullPointerException("Not posible to Initialize Array at < AI.getPieceMoves() >");}

        for (Tile t : piece.getValidTiles())
        {
            Move m = new Move(piece, piece.getTile(), t);
            Move.addToArray(m, moves);
        }
        if (moves[0] == null)
        {
            throw new NullPointerException("Array empty at < AI.getPieceMoves() >");
        }
        return moves;
    }
    public Move[] getAllMoves(boolean self) throws NullPointerException
    {
        Move[] moves = null;
        boolean initialized = false;
        if (self)
        {
            for (Piece p : this.getActivePieces())
            {
                try
                {
                    Move[] m = getPieceMoves(p);
                    moves = Move.joinArrays(moves, m);
                } 
                catch (NullPointerException e)
                {
                    if (initialized)
                    {
                        continue;
                    }
                    else
                    {
                        moves = getPieceMoves(p);
                        initialized = true;
                    }
                }
            }
        }
        else
        {
            for (Piece p : this.getOponent().getActivePieces())
            {
                try
                {
                    Move[] m = getPieceMoves(p);
                    moves = Move.joinArrays(moves, m);
                } 
                catch (NullPointerException e)
                {
                    if (initialized)
                    {
                        continue;
                    }
                    else
                    {
                        moves = getPieceMoves(p);
                        initialized = true;
                    }
                }
            }
        }
        if (moves[0] == null)
        {
            throw new NullPointerException("Element Null at < AI.getAllMoves >");
        }
        return moves;
    }
    public Move getBestMove()
    {
        Move ret;
        for(Move m : getAllMoves(true))
        {
            
        }
    }
    
    public void setDepth(int _depth)
    {
        this.depth = _depth;
    }

}
