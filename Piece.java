package Pieces;

import GameSet.ChessBoard;
import GameSet.Player;
import GameSet.Tile;
import Pieces.Pawn;

/**
 *This class is the class of which all the chess pieces extends, contains the basic behavior of a Chess piece
 * @author VíctorEduardo
 */
public abstract class Piece
{
    protected Player player;
    private int code;
    private Tile square;
    private Tile[] validSquares;
    private ChessBoard board;
    protected PieceType type;
    
    /**
     *Return an Array of all the {@code Tile} aviable to move the current piece to from the current position
     */
    public abstract Tile[] getValidTiles(); 
    /**
     * @return the representative simbol of the {@code Piece} Type as a String
     */
    public abstract String getSimbol(boolean spanish, boolean complete);
    /**
     *establish that the current {@code Piece} has been moved
     */
    public abstract void moved();
    /**
     * @return whether the {@code Piece} has been moved or not 
     */
    public abstract boolean hasBeenMoved();
    /**
     * @return the score the current {@code Piece} has acording with its position and the {@code Tile}s it controls
     */
    public abstract int getScoreByPosition();
    public abstract Piece clone();
    public abstract Piece clone(Player p);
    
    private static int _code_setter = 0; 
    public static final boolean WHITE = true;
    public static final boolean BLACK = false;
    public Piece()
    {
        this.code = _code_setter;
        _code_setter++;
    }
    
    public enum PieceType
    {
        PAWN,
        QUEEN,
        KING,
        KNIGHT,
        BISHOP,
        ROOK      
    }
 
    /**
     * @return the {@code Player} who owns this piece
     */
    public Player getPlayer()
    {
        return this.player;
    }
    public Tile[] getAviableMoves()
    {
        this.validSquares = this.getValidTiles();
        return validSquares;
    } 
    public ChessBoard getChessBoard()
    {
        return board;
    }
    public Tile getTile()
    {
        return this.square;
    }    
    public PieceType getType()
    {
        return this.type;
    }
    public int getSoreByCapture()
    {
        int i=0;
        switch(this.type)
        {
            case QUEEN:
                i=9;
                break;
            case KNIGHT:
                i=3;
                break;
            case PAWN:
                i=1;
                break;
            case ROOK:
                i=5;
                break;
            case BISHOP:
                i=3;
                break;
            case KING:
                i=200;
                break;
        }
        return i;
    }
    public static int getScoreByCapture(Piece p)
    {
        int i=0;
        switch(p.type)
        {
            case QUEEN:
                i=9;
                break;
            case KNIGHT:
                i=3;
                break;
            case PAWN:
                i=1;
                break;
            case ROOK:
                i=5;
                break;
            case BISHOP:
                i=3;
                break;
            case KING:
                i=200;
                break;
        }
        return i;
    }
    public static int getScoreByCapture(PieceType pt)
    {
        int i=0;
        switch(pt)
        {
            case QUEEN:
                i=9;
                break;
            case KNIGHT:
                i=3;
                break;
            case PAWN:
                i=1;
                break;
            case ROOK:
                i=5;
                break;
            case BISHOP:
                i=3;
                break;
            case KING:
                i=200;
                break;
        }
        return i;
    }
    
    
    /**
     *Perform the capture of a {@code Piece} from the oponent
     * @param p Piece to be captured
     */
    public void capture(Piece p)
    {
        increaseScoreByCapture(p);
        p.die();
    }   
    /**
     *Perform the capture of the current piece.
     *The piece is to be removed from the game
     */
    public void die()
    {
        //decreaseScoreByCapture();
        this.getTile().setEmpty();
        this.getPlayer().takeOffActivePice(this);
    }   
    /**
     *Perform the move for the piece
     * @param tl square of the board to where to piece is going to be moved
     */ 
    public void move(Tile cs)
    {
        String by = "";
        String to = "";
        String longCast = "";
        String shortCast = "";
        if (this.player.getSide() == this.getChessBoard().getTurn())   //INICIALIZA LA NOMENCLATURA DE MOVIMIENTO
        {
            if (this.getChessBoard().getComplete())
            {
                if (this.getChessBoard().getLang())
                {
                    by = " por ";
                    to = " a ";
                    longCast = "ENROQUE LARGO";
                    shortCast = "ENROQUE CORTO";
                }else
                {
                    by = " by ";
                    to = " to ";
                    longCast = "LONG CASTLING";
                    shortCast = "SHORT CASTLING";
                }
            }else
            {
                if (this.getChessBoard().getLang())
                {
                    by = "x";
                    longCast = "O-O-O";
                    shortCast = "O-O";
                }
                else
                {
                    by = "x";
                    longCast = "O-O-O";
                    shortCast = "O-O";
                }
            }
            
            
            boolean castling = false;
            boolean duPass = false;
            System.out.println("PIECE MOVE > START");
            Tile current = this.getTile();
            //System.out.println("PIECE MOVE > TILE GOT");
            if (this.type == PieceType.KING && ((cs.getSecondIndex() - this.getTile().getSecondIndex()) == 2))  //SHORT CASTLING
            {
                for (Tile t : this.getAviableMoves())
                {
                    if (t.equals(cs))
                    {
                        //System.out.println("PIECE MOVE > EMPTY TILE");
                        current.setEmpty();
                        this.setTile(cs);
                        cs.setPiece(this);

                        King k = (King) this;
                        Tile CurrentRook = k.getShortCastlingRook().getTile();
                        Tile newTile = k.getChessBoard().squares[CurrentRook.getFirstIndex()][CurrentRook.getSecondIndex() - 2];
                        CurrentRook.setEmpty();
                        k.getShortCastlingRook().setTile(newTile);
                        newTile.setPiece(k.getShortCastlingRook());
                        castling = true;
                        this.getPlayer().setPassAviable(false);
                        this.getChessBoard().updateLog(shortCast);
                    }
                }
            } else if (this.type == PieceType.KING && (cs.getSecondIndex() - this.getTile().getSecondIndex()) == -2)  //LONG CASTLING
            {
                for (Tile t : this.getAviableMoves())
                {
                    if (t.equals(cs))
                    {
                        //System.out.println("PIECE MOVE > EMPTY TILE");
                        current.setEmpty();
                        this.setTile(cs);
                        cs.setPiece(this);

                        King k = (King) this;
                        Tile CurrentRook = k.getLongCastlingRook().getTile();
                        Tile newTile = k.getChessBoard().squares[CurrentRook.getFirstIndex()][CurrentRook.getSecondIndex() + 3];
                        CurrentRook.setEmpty();
                        k.getLongCastlingRook().setTile(newTile);
                        newTile.setPiece(k.getLongCastlingRook());
                        castling = true;
                        this.getPlayer().setPassAviable(false);
                        this.getChessBoard().updateLog(longCast);
                    }
                }
            } 
            //Check if Pawn has just moved Two Tiles
            if (this.type == PieceType.PAWN)
            {
                if (((cs.getFirstIndex() - this.getTile().getFirstIndex()) == -2) || ((cs.getFirstIndex() - this.getTile().getFirstIndex()) == 2) )
                {
                    Pawn p = (Pawn) (this);
                    p.setHasJustMovedTwo(true);  
                    this.getPlayer().setPassAviable(false);
                }
                else 
                {
                   Pawn p = (Pawn) (this);
                   p.setHasJustMovedTwo(false);   
                   this.getPlayer().setPassAviable(false);
                }
            }
            //Check for Pass Capture
            if(this.type == PieceType.PAWN && cs.getColunm() != this.getTile().getColunm() && cs.isEmpty())
            {
                String CoorBefore = this.getTile().getCoordenate();
                System.out.println("CAPTURE DU PASS OCCURRED");
                Pawn p = (Pawn)(this);
                this.capture((Piece)p.getPawnToCaptureDuPass());
                current.setEmpty();
                this.setTile(cs);
                cs.setPiece(this);
                duPass = true;
                String CoorAfter = this.getTile().getCoordenate();
                this.getChessBoard().updateLog(CoorBefore + by + CoorAfter);
            }
            if (!castling && !duPass)
            {
                for (Tile t : this.getAviableMoves())
                {
                    System.out.println("PIECE MOVE > FOR ITERATIVE ENTER");
                    if (t.equals(cs) && t.isAviableForCapture(this))
                    {
                        System.out.println("PIECE MOVE > AVIABLE FOR CAPTURE");
                        String codeOne = this.getSimbol(this.getChessBoard().getLang(), this.getChessBoard().getComplete()) + by;
                        this.capture(cs.getPiece());
                        current.setEmpty();
                        this.setTile(cs);
                        cs.setPiece(this);
                        this.getPlayer().setPassAviable(false);
                        String codeTwo = this.getTile().getCoordenate();
                        this.getChessBoard().updateLog(codeOne + codeTwo);
                    } else if (t.equals(cs))
                    {
                        String One;
                        if (this.getType() == PieceType.PAWN)
                        {
                            One = cs.getCoordenate();
                            if (this.getChessBoard().getComplete())
                            {
                                One = this.getSimbol(this.getChessBoard().getLang(), this.getChessBoard().getComplete()) + to + cs.getCoordenate();
                            }
                        }
                        else
                        {
                            One = this.getSimbol(this.getChessBoard().getLang(), this.getChessBoard().getComplete()) + to + cs.getCoordenate();
                        }
                        System.out.println("PIECE MOVE > EMPTY TILE");
                        current.setEmpty();
                        this.setTile(cs);
                        cs.setPiece(this);
                        this.getPlayer().setPassAviable(false);
                        this.getChessBoard().updateLog(One);
                    }
                }
            }
            this.moved();
            this.getChessBoard().SwitchTurn();
            System.out.println("PIECE MOVE > FINISHED MOVE");
                       
            //PAWN PROMOTION
            if (this.type == PieceType.PAWN && this.getTile().getLine() == '8' && this.getPlayer().getSide())
            {
                Pawn p = (Pawn)this;
                //jfPawnPromotion.promotionFrame(this.getPlayer());
                p.promotion(p.getPlayer().getPromotionType());
            }
            else if(this.type == PieceType.PAWN && this.getTile().getLine() == '1' && !this.getPlayer().getSide())
            {
                Pawn p = (Pawn)this;
                //jfPawnPromotion.promotionFrame(this.getPlayer());
                p.promotion(p.getPlayer().getPromotionType());
            }
        }
    }   
    /**
     * Return the score obtained for capturing the current {@code Piece}
     */
    public void decreaseScoreByCapture()
    {
        int i=0;
        switch(this.type)
        {
            case QUEEN:
                i=9;
                break;
            case KNIGHT:
                i=3;
                break;
            case PAWN:
                i=1;
                break;
            case ROOK:
                i=5;
                break;
            case BISHOP:
                i=3;
                break;
            case KING:
                i=200;
                break;
        }
        this.player.decreaseScore(i);
    }   
    /**
     *
     * @param p piece captured
     * @return the score obtained for capturing the {@code Piece} p
     */
    public void increaseScoreByCapture(Piece p)
    {
        int i = 0;
        switch(p.type)
        {
            case QUEEN:
                i=9;
                break;
            case KNIGHT:
                i=3;
                break;
            case PAWN:
                i=1;
                break;
            case ROOK:
                i=5;
                break;
            case BISHOP:
                i=3;
                break;
            case KING:
                i=200;
                break;
        }
        this.player.increaseScore(i);
    }   
    public boolean equals(Piece p)
    {
        if (p.code == this.code)
        {
            return true;
        }
        return false;
    }
    
    

    /**
     *set the {@code Tile} for the current {@code Piece}
     * @param place Tile to be set as new position for the Piece
     */
    public void setTile(Tile place)
    {
        square = place;
    }
    
    /**
     *set the valid {@code Tile} to be moved to
     */
    public void setValidTiles()    
    {
        this.validSquares = this.getValidTiles();
    }
    
    /**
     *set the board to the current {@code Piece}
     */
    public void setBoard(ChessBoard cb)
    {
        this.board = cb;
    }
}
