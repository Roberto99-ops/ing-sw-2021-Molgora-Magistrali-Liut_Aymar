package it.polimi.ingsw.client;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.cli.CliManager;
import it.polimi.ingsw.view.cli.Utility;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

public class Client {

    /**
     * This method starts a client:
     * 1) creates the connection with the server
     * 2) starts a loop during which the client is always ready to receive some messages and:
     *   2.1) if it is a string, it prints it on screen and in some cases waits for an answer
     *   2.2) if it is something else (so an update for the CLI), it converts it into a readable CLI object and prints
     *   it calling CLIManager.Update
     * @param args parameter
     * @throws IOException if the choice made is not available
     * @throws ClassNotFoundException if the choice made is not available
     */

    public static void main (String[] args) throws IOException, ClassNotFoundException {
        Player player = new Player();
        Scanner scan = new Scanner(System.in);
        //for the connection
        System.out.print("Insert IP address of Server: ");
        String ip = scan.nextLine();
        System.out.print("\nInsert Port number of Server:");
        String serverPort = scan.nextLine();

        Socket server;
        try {
            server = new Socket(ip, Integer.parseInt(serverPort));
        } catch (IOException e) {
            System.out.println("\nServer unreachable");
            return;
        }
        System.out.println("\nConnected");


        ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(server.getInputStream());

        Object next = input.readObject();
        do{
            if(next.getClass().equals(String.class)) {
                if(next.equals("clean screen") || ((String) next).charAt(0) == '\n') {
                    if (next.equals("clean screen")) {
                        Utility.Clean();
                    }
                    if (((String) next).charAt(0) == '\n') {
                        System.out.print("\n" + next);
                    }
                }
                else {
                    System.out.print("\n" + next);
                    String string = scan.nextLine();
                    try {
                        output.writeObject(string);
                        output.flush();
                        output.reset();
                    } catch (IOException e){ break;}
                }
            }
            else
                CliManager.update(next, player);
            try {
                next = input.readObject();
            }catch (IOException e){
                break;
            }
        }while(!next.equals("Game Ended"));
        try {
            server.close();
            input.close();
            output.close();
        } catch (IOException ex) {}
    }

}