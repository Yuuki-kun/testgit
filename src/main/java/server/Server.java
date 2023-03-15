/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author tongcongminh
 */
public class Server {

    //huyfnh lozz awn cuwscl,kmjnui
    
    private static ArrayList<ClientExecute> clientsList = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws IOException {
        ServerSocket svSocket = new ServerSocket(1212);
        while (true) {
                        System.out.println("Server is listening on Port 1111");

            Socket client = svSocket.accept();
            ClientExecute clientThread = new ClientExecute(client, clientsList);
            clientsList.add(clientThread);
            pool.execute(clientThread);
            System.out.println("Cac client da ket noi: ");
            for (ClientExecute clientOfClientList : clientsList) {
                System.out.println(clientOfClientList.getClient().getInetAddress());
            }
        }
    }
}
