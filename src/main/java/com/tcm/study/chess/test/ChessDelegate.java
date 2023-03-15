/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tcm.study.chess.test;

/**
 *
 * @author tongcongminh
 */
public interface ChessDelegate {
    ChessPiece pieceAt(int col, int row);
    void movePiece(int fromCol, int fromRow, int toCol, int toRow);

}
