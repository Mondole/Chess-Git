/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameSet;

import Pieces.Piece;

/**
 * @author VíctorEduardo
 */
public class Move
{
    private Piece piece;
    private Tile from;
    private Tile to;
    private Player player;
    private int ID;
    private Move parent;
    
    public Move (Piece p, Tile _from, Tile _to)
    {
        boolean correct = false;
        for (Tile ct : p.getValidTiles())
        {
            if (ct.equals(to))
            {
                correct = true;
            }
        }
        if (correct)
        {
            this.piece = p;
            this.from = _from;
            this.to = _to;
        } 
        else
        {
            throw new IllegalArgumentException("Argument not Valid at < Move.CTOR >");
        }
    }
    
    public void execute()
    {
        piece.move(to);
    }
    public void undo()
    {
        
    }
    public static Move[] addToArray(Move valueToAdd, Move[] array)
    {
        try
        {
            Move[] at = new Move[array.length + 1];
            for (int i = 0; i < array.length; i++)
            {
                at[i] = array[i];
            }
            at[array.length] = valueToAdd;
            return at;
        } 
        catch (NullPointerException e)
        {
            return new Move[]{valueToAdd};
        }
    }
    public static Move[] joinArrays(Move[] array1, Move[] array2) //CODE MISSING
    {
        Move[] m = new Move[array1.length + array2.length];
        for (int i = 0; i < array1.length ; i++)
        {
            m[i] = array1[i];
        }
        int n = 0;
        for (int i = array1.length; i < m.length; i++)
        {
            m[i] = array2[n];
            n++;
        }
        return m;
    }
    
    
    public Piece getPiece()
    {
        return this.piece;
    }
    public Tile getDestination()
    {
        return this.to;
    }
    public Tile getOrigin()
    {
        return this.from;
    }
    public Move getParent()
    {
        return this.parent;
    }
    
    
}
