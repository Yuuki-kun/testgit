/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tcm.study.chess.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author tongcongminh da xem toi video 6
 */
public class ChessView extends JPanel implements MouseListener, MouseMotionListener {

//    private ChessDelegate chessDelegate;
    private ChessController chessDelegate;

    double scaleFactor = 0.9;
    int originX = -1;
    int originY = -1;
    int cellSize = -1;

    int fromCol = -1;
    int fromRow = -1;

    //piece is moved
    private ChessPiece movingPiece;
    private Point movingPiecePoint;
//    BufferedImage bishopBlack;
//    BufferedImage bishopWhite;
//    BufferedImage knightBlack;
//    BufferedImage knightWhite;
//    BufferedImage kingBlack;
//    BufferedImage kingWhite;
//    BufferedImage queenBlack;
//    BufferedImage queenWhite;
//    BufferedImage pawnBlack;
//    BufferedImage pawnWhite;
//    BufferedImage rookBlack;
//    BufferedImage rookWhite;
//
//    String bishopBlackFileName = "Bishop-black";
//    String bishopWhiteFileName = "Bishop-white";
//    String knightBlackFileName = "Knight-black";
//    String knightWhiteFileName = "Kight-white";
//    String kingBlackFileName = "King-black";
//    String kingWhiteFileName = "King-white";
//    String queenBlackFileName = "queen-black";
//    String queenWhiteFileName = "queen-white";
//    String pawnBlackFileName = "Pawn-black";
//    String pawnWhiteFileName = "Pawn-white";
//    String rookBlackFileName = "Rook-black";
//    String rookWhiteFileName = "Rook-white";
    public String[] peacesName;
    Map<String, BufferedImage> keyNamePeace = new HashMap<String, BufferedImage>();

    //create hasmap (key, buffered image)
    public ChessView(ChessController chessDelegate) throws IOException {

        this.chessDelegate = chessDelegate;

        peacesName = new String[]{
            "Bishop-black",
            "Bishop-white",
            "Knight-black",
            "Knight-white",
            "King-black",
            "King-white",
            "queen-black",
            "queen-white",
            "Pawn-black",
            "Pawn-white",
            "Rook-black",
            "Rook-white"
        };
        for (String peaceName : peacesName) {
            keyNamePeace.put(peaceName, loadImage(peaceName));
        }

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        int smaller = Math.min(getSize().width, getSize().height);
        cellSize = (int) (((double) smaller) * scaleFactor / 8);

        originX = (getSize().width - 8 * cellSize) / 2;
        originY = (getSize().height - 8 * cellSize) / 2;

        Graphics2D g2 = (Graphics2D) g;

        drawBoard(g2);

        try {
            drawPieces(g2);
        } catch (IOException ex) {
            System.out.println("Draw Pieces Error: paintChildren function.");
            ex.printStackTrace();
        }

    }

    //draw all pieaces
    public void drawPieces(Graphics2D g2) throws IOException {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece p = chessDelegate.pieceAt(col, row);
                //dont draw the first position of moving piece
                if (p != null && p != movingPiece) {
                    drawPeace(g2, p.col, p.row, p.imgName);
                }
            }
        }
        if (movingPiece != null && movingPiecePoint != null) {
            ///draw image when drag mouse
            BufferedImage img = keyNamePeace.get(movingPiece.imgName);
            g2.drawImage(img, movingPiecePoint.x - cellSize / 2, movingPiecePoint.y - cellSize / 2, cellSize, cellSize, null);

        }
    }

    //return Image
    public BufferedImage loadImage(String fileName) throws FileNotFoundException, IOException {
        return ImageIO.read(new FileInputStream("res/pices-img/" + fileName + ".png"));
    }

    //draw peace
    public void drawPeace(Graphics2D g2, int col, int row, String peaceName) throws IOException {
        BufferedImage peaceImg = loadImage(peaceName);
        g2.drawImage(peaceImg, originX + col * cellSize, originY + row * cellSize, cellSize, cellSize, null);

    }

    //draw board
    public void drawBoard(Graphics2D g2) {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {

                drawSquare(g2, i * 2, 2 * j, true);
                drawSquare(g2, 1 + 2 * i, 1 + 2 * j, true);

                //https://color.adobe.com/create/color-wheel/
                drawSquare(g2, i * 2 + 1, 2 * j, false);
                drawSquare(g2, 2 * i, 1 + 2 * j, false);
            }
        }
    }

    //draw square
    public void drawSquare(Graphics2D g2, int col, int row, boolean light) {
        g2.setColor(light ? Color.white : (Color.decode("#69B8FF")));
        g2.fillRect(originX + col * cellSize, originY + row * cellSize, cellSize, cellSize);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int y = e.getPoint().y;

        fromCol = (int) (e.getPoint().x - originX) / cellSize;
        fromRow = (int) (e.getPoint().y - originY) / cellSize;
        //piece is moved
        movingPiece = chessDelegate.pieceAt(fromCol, fromRow);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getPoint().x;
        int y = e.getPoint().y;

        int toCol = (int) (e.getPoint().x - originX) / cellSize;
        int toRow = (int) (e.getPoint().y - originY) / cellSize;
        System.out.println(" from col:" + fromCol + ", row" + fromRow + ", to col:" + toCol + ", row:" + toRow);

        //moving piece from a to b
        chessDelegate.movePiece(fromCol, fromRow, toCol, toRow);
        
        //send data to server
        PrintWriter out = chessDelegate.getOut();
        out.println(fromCol);
        out.println(fromRow);
        out.println(toCol);
        out.println(toRow);

        //remove the last image of piece when drag
        movingPiece = null;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        movingPiecePoint = e.getPoint();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public ChessController getChessDelegate() {
        return this.chessDelegate;
    }
}
