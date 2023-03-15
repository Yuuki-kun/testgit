/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author tongcongminh
 */
public class ClientExecute implements Runnable {

    private String name;
    private Socket client;
    private String enemyname;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientExecute> clientList;

    ClientExecute(Socket client, ArrayList<ClientExecute> clientsList) throws IOException {
        this.client = client;
        this.clientList = clientsList;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);

    }

    @Override
    public void run() {
        try {
            this.name = in.readLine();
            this.enemyname = in.readLine();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            while (true) {
                if (in.ready()) {
                    String mess = in.readLine();

                    for (ClientExecute client : clientList) {
                        if (client.getName().equals(this.enemyname)) {
//                            client.getOut().println("[" + this.name + "]: " + mess);
                            client.getOut().println(mess);

                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server closing.");
            try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            out.close();
        }
    }

    public String getName() {
        return name;
    }

    public String getEnemy() {
        return enemyname;
    }

    public PrintWriter getOut() {
        return out;
    }

    public Socket getClient() {
        return this.client;
    }

}
