package it.polimi.ingsw.client;

import it.polimi.ingsw.Server.Server;

import javax.swing.*;
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
            server = new Socket(ip, Server.SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("server unreachable");
            return;
        }
        System.out.println("Connected");

        try {
            ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(server.getInputStream());
        } catch (IOException e) {
            System.out.println("server has died");
        } catch (ClassCastException e) {
            System.out.println("protocol violation");
        }

        ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
        String something;
        System.out.print("Write your name: ");
        something=scan.nextLine();
        output.writeByte(1);
        output.writeUTF("rick");
        output.flush();
        //output.writeBytes(something);
        while(true){
            System.out.print("Write something: ");
            something = scan.nextLine();
            output.writeBytes(something);
            //System.out.println(something);
        }//while(something!="close");

        /*try {
            server.close();
        } catch (IOException e) { }*/

    }


}