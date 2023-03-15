/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tcm.study.chess.test;

/**
 *
 * @author tongcongminh
 */
enum Player {
    WHITE,
    BLACK,
}

enum Rank {
    KING,
    QUEEN,
    BISHOP,
    ROOK,
    KNIGHT,
    PAWN,
}

public class ChessPiece {

    int col;
    int row;
    Player player;
    Rank rank;
    String imgName;

    public ChessPiece(int col, int row, Player player, Rank rank, String imgName) {
        this.col = col;
        this.row = row;
        this.player = player;
        this.rank = rank;
        this.imgName = imgName;
    }
    
}
