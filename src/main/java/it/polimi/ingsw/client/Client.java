package it.polimi.ingsw.client;

import it.polimi.ingsw.Server.Server;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Insert IP address of Server ");
        String ip = scan.nextLine();

        /*Per aprire connessione col server*/
        Socket server;
        try {
            server = new Socket(ip, Server.SOCKET_PORT);  //ipServer Roby: 95.250.236.230
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

        DataOutputStream output = new DataOutputStream(server.getOutputStream());
        String something;
        System.out.print("Write your name: ");
        something = scan.nextLine();
        output.writeUTF(something);
        output.flush();

        while(!server.isClosed()){
            System.out.print("Write something: ");
            something = scan.nextLine();
            output.writeUTF(something);
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