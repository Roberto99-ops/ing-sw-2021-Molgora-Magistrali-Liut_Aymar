package it.polimi.ingsw.client;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.cli.CliManager;
import it.polimi.ingsw.view.cli.DevelopeDecksView;
import it.polimi.ingsw.view.cli.Playerboard;
import it.polimi.ingsw.view.cli.Utility;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

public class Client {

    /**
     * this method starts a client, so:
     * 1) creates the connection with the server
     * 2) starts a loop where the client is always ready to receive some messages and:
     *   2.1) if is a string it prints on the screen and in some cases wait for an answer
     *   2.2) if is something else (so an update for the CLI) converts it into a readable CLI object and prints it calling CLIManager.Update
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Player player = new Player();
        Scanner scan = new Scanner(System.in);
        System.out.print("Insert IP address of Server: ");
        String ip = scan.nextLine();
        System.out.print("\nInsert Port number of Server:");
        String serverport = scan.nextLine();

        Socket server;
        try {
            server = new Socket(ip, Integer.parseInt(serverport));
        } catch (IOException e) {
            System.out.println("\nServer unreachable");
            return;
        }
        System.out.println("\nConnected");


        //try {
        ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(server.getInputStream());
        //} catch (IOException e) {   System.out.println("server has died");
        //        } catch (ClassCastException e) {
        //            System.out.println("protocol violation");
        //        }
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
        } catch (IOException ex) { System.out.println("\nServer close gone wrong");}
    }

}