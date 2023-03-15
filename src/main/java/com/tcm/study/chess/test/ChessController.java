/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.tcm.study.chess.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;
import server.ServerRespone;

/**
 *
 * @author tongcongminh
 *
 * class client
 */
public class ChessController implements ChessDelegate {

    private ChessModel chessModel = new ChessModel();
    private ChessView chessPanel;

    private static BufferedReader in;
    private static PrintWriter out;

    public ChessController() throws IOException {

        Socket socket = new Socket("localhost", 1212);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);

        System.out.println("enter name:");
        String name = sc.nextLine();
        System.out.println("enter enemy name:");
        String enemyname = sc.nextLine();

        out.println(name);
        out.println(enemyname);

        chessModel.reset();

        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chessPanel = new ChessView(this);
        frame.add(chessPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        server.ServerRespone svrs = new ServerRespone(socket, chessPanel);
        svrs.start();
    }

    public static void main(String[] args) throws IOException {
        new ChessController();
    }

    @Override
    public ChessPiece pieceAt(int col, int row) {
        return chessModel.pieceAt(col, row);
    }

    @Override
    public void movePiece(int fromCol, int fromRow, int toCol, int toRow) {
        chessModel.movePiece(fromCol, fromRow, toCol, toRow);
        chessPanel.repaint();
    }
    public BufferedReader getIn(){
        return this.in;
    }
    public PrintWriter getOut(){
        return this.out;
    }
}
