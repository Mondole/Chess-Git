/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import GameSet.ChessBoard;
import GameSet.Player;
import GameSet.Tile;
import Pieces.Piece;
import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author VíctorEduardo
 */
public class jfBoardGUI extends javax.swing.JFrame
{
    ChessBoard board;
    JLabel current;
    Tile[] valids;
    Player py1;
    Player py2;
    
    private boolean helpingMode;
    
    private Color whitePiece = Color.LIGHT_GRAY;
    private Color blackPiece = Color.BLACK;
    private Color tileWhite = Color.WHITE;
    private Color tileBlack = Color.DARK_GRAY;
    
    
    /**
     * Creates new form jfBoardGUI
     */
    public jfBoardGUI(Player p1, Player p2, boolean _helpMode, boolean language, boolean complete)
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        board = new ChessBoard(p1 , p2, language, complete);
        py1 = p1;
        py2 = p2;
        helpingMode = _helpMode;
        setStartingGUI();
        
        lblPlayer1.setText(" " + p1.getName());
        lblPlayer2.setText(" " + p2.getName());
        lblDeath1.setForeground(whitePiece);
        lblDeath2.setForeground(blackPiece);
        setPlayersScore();
        
        if (!helpingMode)
        {
            for (JLabel j : getArray())
            {
                j.setOpaque(true);
            }
        }
        clearTiles();
    }

    private void setPlayersScore()
    {
        lblScore1.setText(String.valueOf(board.getPlayer(1).getScore()));
        lblScore2.setText(String.valueOf(board.getPlayer(2).getScore()));

        lblCurrentColor.setBackground(getCurrentColor());
        lblCurrentColor.setForeground(getCurrentColor());
        lblCurrentPlayer.setText(getCurrentPlayer());

        lblPosP1.setText(" " + board.getPlayer(1).getName());
        lblPosP2.setText(" " + board.getPlayer(2).getName());

        lblValueP1.setText(String.valueOf(board.getPlayer(1).getScorePerPosition()));
        lblValueP2.setText(String.valueOf(board.getPlayer(2).getScorePerPosition()));

        setDeadGUI();

    }
    
    private void setLog()
    {
        taMatchLog.setText(this.board.getLog());
        lblMoveNumber.setText(String.valueOf(board.getMoveNumber() - 1));
    }

    public void setStartingGUI()
    {
        JLabel[][] lbl = getBiArray();

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                try
                {
                    setImageForPiece(board.squares[i][j].getPiece());
                } catch (Exception e)
                {
                    lbl[i][j].setIcon(null);
                }
                try
                {
                    if (board.squares[i][j].getPiece().getPlayer().getSide()) // Piezas Blancas
                    {
                        if (board.squares[i][j].getPiece().getType() == Piece.PieceType.PAWN)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Pawn.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.QUEEN)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Queen.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.ROOK)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Rook.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.KNIGHT)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Knight.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.KING)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - King.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.BISHOP)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Bishop.png")));
                        }
                    } else                                                    //Piezas Negras
                    {
                        lbl[i][j].setForeground(blackPiece);
                        if (board.squares[i][j].getPiece().getType() == Piece.PieceType.PAWN)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Pawn.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.QUEEN)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Queen.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.ROOK)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Rook.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.KNIGHT)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Knight.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.KING)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - King.png")));
                        } else if (board.squares[i][j].getPiece().getType() == Piece.PieceType.BISHOP)
                        {
                            lbl[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Bishop.png")));
                        }
                    }
                } catch (NullPointerException e){}               
            }           
        }
    }
        
    public void setImageForPiece(Piece p)
    {
        JLabel[][] lbl = getBiArray();
        if (p.getPlayer().getSide())                              // Piezas Blancas
        {
            if (p.getType() == Piece.PieceType.PAWN)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Pawn.png")));
            } else if (p.getType() == Piece.PieceType.QUEEN)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Queen.png")));
            } else if (p.getType() == Piece.PieceType.ROOK)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Rook.png")));
            } else if (p.getType() == Piece.PieceType.KNIGHT)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Knight.png")));
            } else if (p.getType() == Piece.PieceType.KING)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - King.png")));
            } else if (p.getType() == Piece.PieceType.BISHOP)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/White - Bishop.png")));
            }
        } else                                                    //Piezas Negras
        {
            if (p.getType() == Piece.PieceType.PAWN)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Pawn.png")));
            } else if (p.getType() == Piece.PieceType.QUEEN)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Queen.png")));
            } else if (p.getType() == Piece.PieceType.ROOK)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Rook.png")));
            } else if (p.getType() == Piece.PieceType.KNIGHT)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Knight.png")));
            } else if (p.getType() == Piece.PieceType.KING)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - King.png")));
            } else if (p.getType() == Piece.PieceType.BISHOP)
            {
                lbl[p.getTile().getFirstIndex()][p.getTile().getSecondIndex()].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Black - Bishop.png")));
            }
        }
    }
    
    public void setDeadGUI()
    {
        String s1 = "";
        String s2 = "";
        try
        {
            for (Piece p : board.getPlayer(1).getLostPieces())
            {
                s1 += p.getSimbol(p.getChessBoard().getLang(), false);
                s1 += " ";
            }
        } catch (NullPointerException e)
        {
            System.out.println("NullPointerException < jfBoardGUI.setDeadGUI() > has occurred");
        }
        try
        {
            for (Piece p : board.getPlayer(2).getLostPieces())
            {
                s2 += p.getSimbol(p.getChessBoard().getLang(), false);
                s2 += " ";
            }
        } catch (NullPointerException e)
        {
            System.out.println("NullPointerException < jfBoardGUI.setDeaGUI() > has occurred");
        }
        lblDeath1.setText(s1);
        lblDeath2.setText(s2);
    }
    
    public JLabel[] getArray()
    {
        JLabel[] r = new JLabel[64];
        r[0] = a1;
        r[1] = b1;
        r[2] = c1;
        r[3] = d1;
        r[4] = e1;
        r[5] = f1;
        r[6] = g1;
        r[7] = h1;
        r[8] = a2;
        r[9] = b2;
        r[10] = c2;
        r[11] = d2;
        r[12] = e2;
        r[13] = f2;
        r[14] = g2;
        r[15] = h2;
        r[16] = a3;
        r[17] = b3;
        r[18] = c3;
        r[19] = d3;
        r[20] = e3;
        r[21] = f3;
        r[22] = g3;
        r[23] = h3;
        r[24] = a4;
        r[25] = b4;
        r[26] = c4;
        r[27] = d4;
        r[28] = e4;
        r[29] = f4;
        r[30] = g4;
        r[31] = h4;
        r[32] = a5;
        r[33] = b5;
        r[34] = c5;
        r[35] = d5;
        r[36] = e5;
        r[37] = f5;
        r[38] = g5;
        r[39] = h5;
        r[40] = a6;
        r[41] = b6;
        r[42] = c6;
        r[43] = d6;
        r[44] = e6;
        r[45] = f6;
        r[46] = g6;
        r[47] = h6;
        r[48] = a7;
        r[49] = b7;
        r[50] = c7;
        r[51] = d7;
        r[52] = e7;
        r[53] = f7;
        r[54] = g7;
        r[55] = h7;
        r[56] = a8;
        r[57] = b8;
        r[58] = c8;
        r[59] = d8;
        r[60] = e8;
        r[61] = f8;
        r[62] = g8;
        r[63] = h8;
        
        return r;
    }
    
    public JLabel[][] getBiArray()
    {
        JLabel[][] r = new JLabel[8][8];
        r[0][0] = a1;
        r[0][1] = b1;
        r[0][2] = c1;
        r[0][3] = d1;
        r[0][4] = e1;
        r[0][5] = f1;
        r[0][6] = g1;
        r[0][7] = h1;
        r[1][0] = a2;
        r[1][1] = b2;
        r[1][2] = c2;
        r[1][3] = d2;
        r[1][4] = e2;
        r[1][5] = f2;
        r[1][6] = g2;
        r[1][7] = h2;
        r[2][0] = a3;
        r[2][1] = b3;
        r[2][2] = c3;
        r[2][3] = d3;
        r[2][4] = e3;
        r[2][5] = f3;
        r[2][6] = g3;
        r[2][7] = h3;
        r[3][0] = a4;
        r[3][1] = b4;
        r[3][2] = c4;
        r[3][3] = d4;
        r[3][4] = e4;
        r[3][5] = f4;
        r[3][6] = g4;
        r[3][7] = h4;
        r[4][0] = a5;
        r[4][1] = b5;
        r[4][2] = c5;
        r[4][3] = d5;
        r[4][4] = e5;
        r[4][5] = f5;
        r[4][6] = g5;
        r[4][7] = h5;
        r[5][0] = a6;
        r[5][1] = b6;
        r[5][2] = c6;
        r[5][3] = d6;
        r[5][4] = e6;
        r[5][5] = f6;
        r[5][6] = g6;
        r[5][7] = h6;
        r[6][0] = a7;
        r[6][1] = b7;
        r[6][2] = c7;
        r[6][3] = d7;
        r[6][4] = e7;
        r[6][5] = f7;
        r[6][6] = g7;
        r[6][7] = h7;
        r[7][0] = a8;
        r[7][1] = b8;
        r[7][2] = c8;
        r[7][3] = d8;
        r[7][4] = e8;
        r[7][5] = f8;
        r[7][6] = g8;
        r[7][7] = h8;
        
        return r;
    }
    
    public int getFstIndex(JLabel c)
    {
        JLabel[][] lblArr = getBiArray();
        int r = 0;
        for (int i = 0 ; i < 8 ; i++)
        {
            for (int j = 0 ; j < 8 ; j++)
            {
                if (lblArr[i][j].equals(c))
                {
                    r = i;
                    return i;
                }
            }
        }
        return r;
    }
    public int getScdIndex(JLabel c)
    {
        JLabel[][] lblArr = getBiArray();
        int r = 0;
        for (int i = 0 ; i < 8 ; i++)
        {
            for (int j = 0 ; j < 8 ; j++)
            {
                if (lblArr[i][j].equals(c))
                {
                    r = j;
                    return j;
                }
            }
        }
        return r;
    }
    
    private void clearTiles()
    {
        if (helpingMode)
        {
            JLabel[] a = getArray();
            Color c = new Color(204, 204, 204);
            for (JLabel l : a)
            {
                l.setBackground(c);
            }
        }
        else
        {
            JLabel[] a = getArray();
            boolean zig = false;
            int c = 0;
            for (JLabel i : a)
            {
                if (c == 8)
                {
                    c = 0;
                    if (zig)
                    {
                        zig = false;
                    }
                    else 
                    {
                        zig = true;
                    }
                }
                if (zig)
                {
                   i.setBackground(tileWhite);
                    zig = false;
                    c++;
                }
                else
                {
                    i.setBackground(tileBlack);
                    zig = true;
                    c++;
                }
            }
        }
    }
    
    private void CheckForMove(JLabel selection)
    {
        int f1 = getFstIndex(selection);
        int d2 = getScdIndex(selection);
        for (Tile t : valids)
        {
            if (t.getFirstIndex() == f1 && t.getSecondIndex() == d2)
            {
                board.squares[getFstIndex(current)][getScdIndex(current)].getPiece().move(t);
            }
        }
        setStartingGUI();
    }
    
    private Color getCurrentColor()
    {
        if(board.getTurn() == board.getPlayer(1).getSide())
        {
            return this.whitePiece;
        }
        else
        {
            return this.blackPiece;
        }
    }
    private String getCurrentPlayer()
    {
        if(board.getPlayer(1).getSide() == board.getTurn())
        {
            return board.getPlayer(1).getName();
        }
        else
        {
            return board.getPlayer(2).getName();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        h9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblPlayer1 = new javax.swing.JLabel();
        lblPlayer2 = new javax.swing.JLabel();
        lblScore1 = new javax.swing.JLabel();
        lblCurrentPlayer = new javax.swing.JLabel();
        lblPlayer3 = new javax.swing.JLabel();
        lblCurrentColor = new javax.swing.JLabel();
        lblScore2 = new javax.swing.JLabel();
        lblPlayer4 = new javax.swing.JLabel();
        lblMoveNumber = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblPosP1 = new javax.swing.JLabel();
        lblPosP2 = new javax.swing.JLabel();
        lblValueP1 = new javax.swing.JLabel();
        lblValueP2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDeath1 = new javax.swing.JLabel();
        lblDeath2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbNextPromotion = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        taMatchLog = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        a1 = new javax.swing.JLabel();
        b1 = new javax.swing.JLabel();
        c1 = new javax.swing.JLabel();
        d1 = new javax.swing.JLabel();
        e1 = new javax.swing.JLabel();
        f1 = new javax.swing.JLabel();
        g1 = new javax.swing.JLabel();
        h1 = new javax.swing.JLabel();
        a2 = new javax.swing.JLabel();
        b2 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        d2 = new javax.swing.JLabel();
        e2 = new javax.swing.JLabel();
        f2 = new javax.swing.JLabel();
        g2 = new javax.swing.JLabel();
        h2 = new javax.swing.JLabel();
        c3 = new javax.swing.JLabel();
        f3 = new javax.swing.JLabel();
        h3 = new javax.swing.JLabel();
        e3 = new javax.swing.JLabel();
        g3 = new javax.swing.JLabel();
        b3 = new javax.swing.JLabel();
        a3 = new javax.swing.JLabel();
        d3 = new javax.swing.JLabel();
        c4 = new javax.swing.JLabel();
        f4 = new javax.swing.JLabel();
        h4 = new javax.swing.JLabel();
        e4 = new javax.swing.JLabel();
        g4 = new javax.swing.JLabel();
        b4 = new javax.swing.JLabel();
        a4 = new javax.swing.JLabel();
        d4 = new javax.swing.JLabel();
        c5 = new javax.swing.JLabel();
        f5 = new javax.swing.JLabel();
        h5 = new javax.swing.JLabel();
        e5 = new javax.swing.JLabel();
        g5 = new javax.swing.JLabel();
        b5 = new javax.swing.JLabel();
        a5 = new javax.swing.JLabel();
        d5 = new javax.swing.JLabel();
        c6 = new javax.swing.JLabel();
        f6 = new javax.swing.JLabel();
        h6 = new javax.swing.JLabel();
        e6 = new javax.swing.JLabel();
        g6 = new javax.swing.JLabel();
        b6 = new javax.swing.JLabel();
        a6 = new javax.swing.JLabel();
        d6 = new javax.swing.JLabel();
        c7 = new javax.swing.JLabel();
        f7 = new javax.swing.JLabel();
        h7 = new javax.swing.JLabel();
        e7 = new javax.swing.JLabel();
        g7 = new javax.swing.JLabel();
        b7 = new javax.swing.JLabel();
        a7 = new javax.swing.JLabel();
        d7 = new javax.swing.JLabel();
        c8 = new javax.swing.JLabel();
        f8 = new javax.swing.JLabel();
        h8 = new javax.swing.JLabel();
        e8 = new javax.swing.JLabel();
        g8 = new javax.swing.JLabel();
        b8 = new javax.swing.JLabel();
        a8 = new javax.swing.JLabel();
        d8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        h9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PUNTUACIONES");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPlayer1.setText("PLAYER 1");
        lblPlayer1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPlayer2.setText("PLAYER 1");
        lblPlayer2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblScore1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScore1.setText("PLAYER 1");
        lblScore1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCurrentPlayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentPlayer.setText("PLAYER 1");
        lblCurrentPlayer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPlayer3.setText(" JUEGA");
        lblPlayer3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCurrentColor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCurrentColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentColor.setText("K");
        lblCurrentColor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblScore2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScore2.setText("PLAYER 1");
        lblScore2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPlayer4.setText(" JUGADA");
        lblPlayer4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblMoveNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMoveNumber.setText("0");
        lblMoveNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblScore1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblScore2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCurrentPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCurrentColor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMoveNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblScore1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblScore2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMoveNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCurrentPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCurrentColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblPosP1.setText("PLAYER 1");
        lblPosP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPosP2.setText("PLAYER 1");
        lblPosP2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblValueP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValueP1.setText("PLAYER 1");
        lblValueP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblValueP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValueP2.setText("PLAYER 1");
        lblValueP2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("POSICION");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDeath1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblDeath1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeath1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDeath2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblDeath2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDeath2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDeath1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPosP1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblValueP1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPosP2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblValueP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblDeath2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPosP1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueP1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDeath1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPosP2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueP2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDeath2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SIGUIENTE CORONACION");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbNextPromotion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "REINA", "CABALLO", "TORRE", "ALFIL" }));
        cbNextPromotion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbNextPromotionActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taMatchLog.setEditable(false);
        taMatchLog.setColumns(20);
        taMatchLog.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        taMatchLog.setRows(5);
        taMatchLog.setText("CHESS\nLOG");
        jScrollPane1.setViewportView(taMatchLog);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("HISTORIAL DE MOVIMIENTOS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbNextPromotion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbNextPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout h9Layout = new javax.swing.GroupLayout(h9);
        h9.setLayout(h9Layout);
        h9Layout.setHorizontalGroup(
            h9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, h9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(h9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        h9Layout.setVerticalGroup(
            h9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(h9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        a1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        a1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        a1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        b1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        b1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        c1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        c1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        c1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        d1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        d1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        d1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        e1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        e1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        e1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        e1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        f1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        f1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        f1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        g1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        g1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        g1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        g1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        h1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        h1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        h1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        a2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        a2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        a2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        b2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        b2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        c2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        c2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        c2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        d2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        d2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        d2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        e2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        e2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        e2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        e2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        f2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        f2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        f2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        g2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        g2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        g2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        g2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        h2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        h2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        h2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        c3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        c3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        c3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        f3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        f3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        f3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        h3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        h3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        h3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        e3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        e3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        e3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        e3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        g3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        g3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        g3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        g3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        b3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        b3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        a3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        a3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        a3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        d3.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        d3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        d3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        c4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        c4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        c4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        f4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        f4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        f4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        h4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        h4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        h4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        e4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        e4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        e4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        e4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        g4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        g4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        g4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        g4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        b4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        b4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        a4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        a4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        a4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        d4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        d4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        d4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        c5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        c5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        c5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        f5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        f5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        f5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        h5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        h5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        h5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        e5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        e5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        e5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        e5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        g5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        g5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        g5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        g5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        b5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        b5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        a5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        a5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        a5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        d5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        d5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        d5.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        c6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        c6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        c6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        f6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        f6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        f6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        h6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        h6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        h6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        e6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        e6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        e6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        e6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        g6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        g6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        g6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        g6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        b6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        b6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        a6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        a6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        a6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        d6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        d6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        d6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        c7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        c7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        c7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        f7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        f7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        f7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        h7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        h7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        h7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        e7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        e7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        e7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        e7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        g7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        g7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        g7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        g7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        b7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        b7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        a7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        a7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        a7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        d7.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        d7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        d7.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        c8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        c8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        c8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        c8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        f8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        f8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        f8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        f8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        h8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        h8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        h8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        h8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        e8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        e8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        e8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        e8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        g8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        g8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        g8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        g8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        b8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        b8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        a8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        a8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        a8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        a8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        d8.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        d8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        d8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        d8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                a1MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("8");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("7");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("6");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("5");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("4");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("3");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("2");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("1");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("a");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("b");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("c");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("d");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("e");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("f");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("g");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("h");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(g1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(h1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(g2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(h2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(e3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(g3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(h3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(e4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(g4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(h4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(e5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(g5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(h5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(e6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(g6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(h6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(a7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(d7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(e7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(g7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(h7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(d8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(e8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(f8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(g8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(h8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(h9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(86, 86, 86))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(86, 86, 86))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(86, 86, 86))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(86, 86, 86))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(86, 86, 86))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(h9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(a8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(d8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(h8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(h7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(d7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(h6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(d6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(h5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(d5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(h4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(d4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(h3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(d3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(h2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(h1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(g1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(f1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void a1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_a1MouseClicked
    {//GEN-HEADEREND:event_a1MouseClicked
        JLabel src = (JLabel) evt.getSource();
        clearTiles();
        if (helpingMode)
        {
            try
            {
                CheckForMove(src);
            } catch (NullPointerException e)
            {
                System.out.println("Exception Ocurred When < Try CheckForMove(src) >");
            }
            src.setBackground(Color.blue);
            current = src;
            JLabel[][] lblArr = getBiArray();
            int fst = getFstIndex(src);
            int scd = getScdIndex(src);
            try
            {
                Piece p = board.squares[fst][scd].getPiece();

                Tile[] t = p.getValidTiles();
                valids = t;
                for (Tile ti : t)
                {
                    lblArr[ti.getFirstIndex()][ti.getSecondIndex()].setBackground(Color.red);
                }
            } catch (Exception e) {}
        }
        else
        {
           try
            {
                CheckForMove(src);
            } catch (NullPointerException e)
            {
                System.out.println("Exception Ocurred When < Try CheckForMove(src) >");
            }
            src.setBackground(Color.blue);
            current = src;
            JLabel[][] lblArr = getBiArray();
            int fst = getFstIndex(src);
            int scd = getScdIndex(src);
            try
            {
                Piece p = board.squares[fst][scd].getPiece();

                Tile[] t = p.getValidTiles();
                valids = t;
                for (Tile ti : t)
                {
                    //lblArr[ti.getFirstIndex()][ti.getSecondIndex()].setBackground(Color.red);
                }
            } catch (Exception e) {}
        }
        setPlayersScore();
        setLog();
    }//GEN-LAST:event_a1MouseClicked

    private void cbNextPromotionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbNextPromotionActionPerformed
    {//GEN-HEADEREND:event_cbNextPromotionActionPerformed
        Piece.PieceType p = null;
        String a = cbNextPromotion.getSelectedItem().toString();
        switch(a)
        {
            case "REINA":
                p = Piece.PieceType.QUEEN;
                break;
            case "TORRE":
                p = Piece.PieceType.ROOK;
                break;
            case "CABALLO":
                p = Piece.PieceType.KNIGHT;
                break;
            case "ALFIL":
                p = Piece.PieceType.BISHOP;
                break;
        }
        py1.setPromotionType(p);
        py2.setPromotionType(p);
    }//GEN-LAST:event_cbNextPromotionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a1;
    private javax.swing.JLabel a2;
    private javax.swing.JLabel a3;
    private javax.swing.JLabel a4;
    private javax.swing.JLabel a5;
    private javax.swing.JLabel a6;
    private javax.swing.JLabel a7;
    private javax.swing.JLabel a8;
    private javax.swing.JLabel b1;
    private javax.swing.JLabel b2;
    private javax.swing.JLabel b3;
    private javax.swing.JLabel b4;
    private javax.swing.JLabel b5;
    private javax.swing.JLabel b6;
    private javax.swing.JLabel b7;
    private javax.swing.JLabel b8;
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel c3;
    private javax.swing.JLabel c4;
    private javax.swing.JLabel c5;
    private javax.swing.JLabel c6;
    private javax.swing.JLabel c7;
    private javax.swing.JLabel c8;
    private javax.swing.JComboBox<String> cbNextPromotion;
    private javax.swing.JLabel d1;
    private javax.swing.JLabel d2;
    private javax.swing.JLabel d3;
    private javax.swing.JLabel d4;
    private javax.swing.JLabel d5;
    private javax.swing.JLabel d6;
    private javax.swing.JLabel d7;
    private javax.swing.JLabel d8;
    private javax.swing.JLabel e1;
    private javax.swing.JLabel e2;
    private javax.swing.JLabel e3;
    private javax.swing.JLabel e4;
    private javax.swing.JLabel e5;
    private javax.swing.JLabel e6;
    private javax.swing.JLabel e7;
    private javax.swing.JLabel e8;
    private javax.swing.JLabel f1;
    private javax.swing.JLabel f2;
    private javax.swing.JLabel f3;
    private javax.swing.JLabel f4;
    private javax.swing.JLabel f5;
    private javax.swing.JLabel f6;
    private javax.swing.JLabel f7;
    private javax.swing.JLabel f8;
    private javax.swing.JLabel g1;
    private javax.swing.JLabel g2;
    private javax.swing.JLabel g3;
    private javax.swing.JLabel g4;
    private javax.swing.JLabel g5;
    private javax.swing.JLabel g6;
    private javax.swing.JLabel g7;
    private javax.swing.JLabel g8;
    private javax.swing.JLabel h1;
    private javax.swing.JLabel h2;
    private javax.swing.JLabel h3;
    private javax.swing.JLabel h4;
    private javax.swing.JLabel h5;
    private javax.swing.JLabel h6;
    private javax.swing.JLabel h7;
    private javax.swing.JLabel h8;
    private javax.swing.JPanel h9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurrentColor;
    private javax.swing.JLabel lblCurrentPlayer;
    private javax.swing.JLabel lblDeath1;
    private javax.swing.JLabel lblDeath2;
    private javax.swing.JLabel lblMoveNumber;
    private javax.swing.JLabel lblPlayer1;
    private javax.swing.JLabel lblPlayer2;
    private javax.swing.JLabel lblPlayer3;
    private javax.swing.JLabel lblPlayer4;
    private javax.swing.JLabel lblPosP1;
    private javax.swing.JLabel lblPosP2;
    private javax.swing.JLabel lblScore1;
    private javax.swing.JLabel lblScore2;
    private javax.swing.JLabel lblValueP1;
    private javax.swing.JLabel lblValueP2;
    private javax.swing.JTextArea taMatchLog;
    // End of variables declaration//GEN-END:variables
}
