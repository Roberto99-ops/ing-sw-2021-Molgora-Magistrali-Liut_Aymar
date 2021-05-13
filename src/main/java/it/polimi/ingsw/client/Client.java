package it.polimi.ingsw.client;

import it.polimi.ingsw.Server.Server;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Insert IP address of Server ");
        String ip = scan.nextLine();

        /*Per aprire connessione col server*/
        Socket server;
        try {
            server = new Socket(ip, Server.SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("server unreachable");
            return;
        }
        System.out.println("Connected");

        /*try {
            ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(server.getInputStream());
        } catch (IOException e) {
            System.out.println("server has died");
        } catch (ClassCastException e) {
            System.out.println("protocol violation");
        }*/

        PrintWriter output = new PrintWriter(server.getOutputStream());
        String something;
        System.out.print("Write your name: ");
        something = scan.nextLine();
        output.println(something);
        output.flush();

        while(!server.isClosed()){
            System.out.print("Write something: ");
            something = scan.nextLine();
            output.println(something);
            output.flush();
            if(something.equals("close")) {
                try {
                    output.close();
                    server.close();
                } catch (IOException e) {
                }
            }
        }

    }


}