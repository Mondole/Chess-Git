/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameSet;

import Pieces.Piece;
import Pieces.Piece.PieceType;

/**
 *
 * @author VíctorEduardo
 */
public class Player
{
    private String name;
    private int score = 0;
    private int scorePerPos = 0;
    private boolean side;
    private Piece[] activePieces;
    private Piece[] lostPieces;
    private PieceType promotionType = PieceType.QUEEN;
    private boolean pawnPassAvailable = false;
    private Player oponent;
       
    public Player(String _name, boolean _side)
    {
        this.name =_name;
        this.side = _side;  
    }
    public void increaseScore(int amount)
    {
        this.score += amount;
    }
    public void decreaseScore(int amount)
    {
        if (this.score > 0 && (this.score - amount) >= 0)
        {
            this.score -= amount;
        }
        else if (amount > this.score)
        {
            this.score = 0;
        }
    }
    public Player clone()
    {
        Player p = new Player(this.name, this.side);
        p.score = this.score;
        p.scorePerPos = this.scorePerPos;
        p.promotionType = this.promotionType;
        p.pawnPassAvailable = this.pawnPassAvailable;
        return p;
    }
    public void UpdateScorePerPos()
    {
        this.scorePerPos = 0;
        int i = 0;
        for (Piece p : activePieces)
        {
            scorePerPos += p.getScoreByPosition();
            i++;
        }
        System.out.println("PLAYER : " + this.name + " HAS " + i + " PIECES ");
    }
    public int getScorePerPosition()
    {
        UpdateScorePerPos();
        return this.scorePerPos;
    }
    
    public String getName()
    {
        return this.name;
    }
    public boolean getSide()
    {
        return this.side;
    }
    public int getScore()
    {
        return this.score;
    }
    public Piece[] getActivePieces()
    {
        return this.activePieces;
    }
    public Piece[] getLostPieces()
    {
        return this.lostPieces;
    }   
    public PieceType getPromotionType()
    {
        return this.promotionType;
    }
    public boolean getPassAvailable()
    {
        return this.pawnPassAvailable;
    }
    public Player getOponent()
    {
        return this.oponent;
    }
    
    
    
    public void addActivePiece(Piece p)
    {
        try
        {
            Piece[] np = new Piece[activePieces.length + 1];
            for (int i = 0; i < activePieces.length; i++)
            {
                np[i] = activePieces[i];
            }
            np[activePieces.length] = p;
            activePieces = np;
        }
        catch (NullPointerException e)
        {
            Piece[] np = new Piece[]{p};
            activePieces = np;
        }
    }
    public void addLostPiece(Piece p)
    {
        try
        {
            Piece[] np = new Piece[lostPieces.length + 1];
            for (int i = 0; i < lostPieces.length; i++)
            {
                np[i] = lostPieces[i];
            }
            np[lostPieces.length] = p;
            lostPieces = np;
        }
        catch (NullPointerException e)
        {
            Piece[] np = new Piece[]{p};
            lostPieces = np;
        }
    }
    
    public void takeOffActivePice(Piece p)
    {
        Piece[] ret = new Piece[activePieces.length - 1];
        int index = 0;
        boolean set = false;
        for (int i = 0; i < activePieces.length ; i++)
        {
            if (activePieces[i].equals(p))
            {
                index = i;
                set = true;
            }
        }
        if (set)
        {
            int f = 0;
            for (int i = 0 ; i <= ret.length ; i++)
            { 
                if (i == index)
                {
                    continue;
                }
                ret[f] = activePieces[i];
                f ++;
                String g = String.valueOf(f);
                System.out.println("PIECE AT INDEX :" + g +"");
            }
        }
        activePieces = ret;
        addLostPiece(p);
    }
    
    public void justTakeOffActivePice(Piece p)
    {
        Piece[] ret = new Piece[activePieces.length - 1];
        int index = 0;
        boolean set = false;
        for (int i = 0; i < activePieces.length ; i++)
        {
            if (activePieces[i].equals(p))
            {
                index = i;
                set = true;
                break;
            }
        }
        if (set)
        {
            int f = 0;
            for (int i = 0 ; i <= ret.length ; i++)
            { 
                if (i == index)
                {
                    continue;
                }
                ret[f] = activePieces[i];
                f ++;
                String g = String.valueOf(f);
            }
        }
        activePieces = ret;
    }
   
    
    public void setPromotionType(PieceType p)
    {
        this.promotionType = p;
    }
    public void setPassAviable(boolean value)
    {
        this.pawnPassAvailable = value;
    }
    public void setOponent(Player op)
    {
        this.oponent = op;
    }
    
    
    /**
     * return whether the current object {@code Player} is the same as the p {@code Player}
     * @param p the object{@code Player} which is goint to check the instance
     * @return {@code true} if the two objects are the same
     */
    public boolean equals(Player p)
    {
        if (this.name.equals(p.getName()) && this.side == p.getSide() && p.getScore() == this.score)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
