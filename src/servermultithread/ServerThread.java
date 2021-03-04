/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermultithread;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servermultithread.ServerMultiThread.risultato;

/**
 *
 * @author pogliani.mattia
 */
public class ServerThread implements Runnable {

    private Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Serverino  partito: "  + clientSocket.getInetAddress());
        try {

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            String richiesta = "";
//            while (!richiesta.equals("exit")) {
                System.out.println("serverino in ascolto...");
                richiesta = in.readLine();
                System.out.println("numero dal client: " + richiesta);

//            }
            int num = Integer.parseInt(richiesta);
            for(; num > 0; num--){
                Somma s = new Somma(num);
                risultato = risultato + s.getNum();
            }
            System.out.println(risultato);
            out.close();
            clientSocket.close();

            System.out.println("chiusura connessione effettuata");

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
