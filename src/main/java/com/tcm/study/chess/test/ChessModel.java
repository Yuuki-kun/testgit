/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tcm.study.chess.test;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tongcongminh
 */
public class ChessModel {

    private Set<ChessPiece> piecesBox = new HashSet<ChessPiece>();

    
    public static void main(String[] args) {
        ChessModel cm = new ChessModel();
        cm.testMovePiece();
    }

    @Override
    public String toString() {
        String desc = "";
        for (int row = 7; row >= 0; row--) {
            desc += "" + row;
            for (int col = 0; col < 8; col++) {
                ChessPiece p = pieceAt(col, row);
                if (p == null) {
                    desc += " .";
                } else {
                    desc += " ";
                    switch (p.rank) {
                        case KING:
                            desc += p.player == Player.WHITE ? "k" : "K";
                            break;
                        case QUEEN:
                            desc += p.player == Player.WHITE ? "q" : "Q";
                            break;
                        case ROOK:
                            desc += p.player == Player.WHITE ? "r" : "R";
                            break;
                        case KNIGHT:
                            desc += p.player == Player.WHITE ? "n" : "N";
                            break;
                        case BISHOP:
                            desc += p.player == Player.WHITE ? "b" : "B";
                            break;
                        case PAWN:
                            desc += p.player == Player.WHITE ? "p" : "P";
                            break;
                    }
                }

            }
            desc += "\n";
        }
        desc += "  0 1 2 3 4 5 6 7";
        return desc;
    }

    //thiet lap vi tri ban dau cho ban co
    public void reset() {
        piecesBox.removeAll(piecesBox);
        for (int i = 0; i < 2; i++) {
            piecesBox.add(new ChessPiece(i * 7, 0, Player.BLACK, Rank.ROOK, ChessConstants.rookBlackFileName));
            piecesBox.add(new ChessPiece(i * 7, 7, Player.WHITE, Rank.ROOK, ChessConstants.rookWhiteFileName));

            piecesBox.add(new ChessPiece(i * 5 + 1, 0, Player.BLACK, Rank.KNIGHT, ChessConstants.knightBlackFileName));
            piecesBox.add(new ChessPiece(i * 5 + 1, 7, Player.WHITE, Rank.KNIGHT, ChessConstants.knightWhiteFileName));

            piecesBox.add(new ChessPiece(i * 3 + 2, 0, Player.BLACK, Rank.BISHOP, ChessConstants.bishopBlackFileName));
            piecesBox.add(new ChessPiece(i * 3 + 2, 7, Player.WHITE, Rank.BISHOP, ChessConstants.bishopWhiteFileName));

        }

        for (int i = 0; i < 8; i++) {
            piecesBox.add(new ChessPiece(i, 1, Player.BLACK, Rank.PAWN, ChessConstants.pawnBlackFileName));
            piecesBox.add(new ChessPiece(i, 6, Player.WHITE, Rank.PAWN, ChessConstants.pawnWhiteFileName));
        }

        piecesBox.add(new ChessPiece(3, 0, Player.BLACK, Rank.QUEEN, ChessConstants.queenBlackFileName));
        piecesBox.add(new ChessPiece(3, 7, Player.WHITE, Rank.QUEEN, ChessConstants.queenWhiteFileName));

        piecesBox.add(new ChessPiece(4, 0, Player.BLACK, Rank.KING, ChessConstants.kingBlackFileName));
        piecesBox.add(new ChessPiece(4, 7, Player.WHITE, Rank.KING, ChessConstants.kingWhiteFileName));
    }

    //
    public ChessPiece pieceAt(int col, int row) {
        for (ChessPiece chessPiece : piecesBox) {
            if (chessPiece.col == col && chessPiece.row == row) {
                return chessPiece;
            }
        }
        return null;
    }

    public void testMovePiece() {
        ChessModel cm = new ChessModel();
        cm.reset();
        System.out.println(cm);
        cm.movePiece(0, 1, 0, 2);
        System.out.println(cm);
    }

    public void movePiece(int fromCol, int fromRow, int toCol, int toRow) {
        ChessPiece p = pieceAt((fromCol), fromRow);
        if (p == null) {
            return;
        }

        ChessPiece target = pieceAt(toCol, toRow);
        
        if(target!=null){
            //the same color with position  move to
            if(target.player == p.player){
                return;
            }else{
                //remove that enemy
                piecesBox.remove(pieceAt(toCol, toRow));
            }
        }
        
        p.col = toCol;
        p.row = toRow;

    }

}
