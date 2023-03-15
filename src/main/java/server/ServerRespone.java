/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import com.tcm.study.chess.test.ChessView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author tongcongminh
 */
public class ServerRespone extends Thread {

    private Socket socket;
    private BufferedReader in;
    ChessView c;

    public ServerRespone(Socket socket, ChessView chessView) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.c = chessView;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int fromC = 0;
                int fromR = 0;
                int toC = 0;
                int toR = 0;

                if (in.ready()) {
                    //nhan vi tri moi
                    fromC = Integer.parseInt(in.readLine());
                    fromR = Integer.parseInt(in.readLine());
                    toC = Integer.parseInt(in.readLine());
                    toR = Integer.parseInt(in.readLine());
                    
                    c.getChessDelegate().movePiece(fromC, fromR, toC, toR);

                    c.repaint();

                    System.out.println(fromC+","+fromR+", "+toC+", "+toR);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
