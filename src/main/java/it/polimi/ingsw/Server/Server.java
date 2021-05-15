package it.polimi.ingsw.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

//dobbiamo usare javaSE??

public class Server {
    private static Random random = new Random();

    //non deve essere costante la porta
    //il numero di porta viene generato casualmente tra 1000 e 1050
    public static int SOCKET_PORT = 1000;//random.nextInt(8);

    public static void main(String[] args) throws IOException {
        System.out.println("Internal ip: " + InetAddress.getLocalHost());

        //apro la porta # ...
        ServerSocket socket;
        try {
            //... creando un socket per quel numero di porta
            socket = new ServerSocket(SOCKET_PORT);
            System.out.println("Server is running");
        } catch (IOException e) {
            System.out.println("cannot open server socket");
            System.exit(1);
            return;
        }
        //...e il server si mette in ascolto.
        // Per ogni nuova connessione stabilita, viene creato un nuovo thread di ClientHandlerù

        while (true) {
            try {
                /* accepts connections; for every connection we accept,
                 * create -- a new Thread executing a ClientHandler -- */
                Socket client = socket.accept();
                //il clientHandler si occupa di gestire la connessione con il client
                ClientHandler clientHandler = new ClientHandler(client);
                //bisogna creare un nuovo thread che si occupi di gestire il clienthandler
                Thread thread = new Thread(clientHandler, "server_" + client.getInetAddress());
                thread.start();
                //il client handler a questo punto è attivo e i messaggi vanno gestiti tra lui e il client
                //System.out.println("QUI");


                /* (IL SERVER NON MANDA MESSAGGI MA È SOLO IL CLIENTHANDLER E IL CLIENT CHE LO FANNO)
                //ora che la connessione è stata stabilita bisogna stabilire lo stream di uscita verso il client e di
                //di entrata nel server
                //- USCITA DAL SERVER
                PrintWriter outPut = new PrintWriter(client.getOutputStream(),true);
                outPut.println("Hi there friend");
                //- ENTRATA DAL SERVER
                BufferedReader inPut = new BufferedReader(new InputStreamReader(client.getInputStream()));


                //ora il server può cominciare a ricevere e a mandare
                //la prima cosa che fa è aspettare il 'name' da client
                String message = input.readLine();
                System.out.println(message);


                /*
                //la prima cosa che il client manda è il suo nome
                InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String message = inputStreamReader.read();


                /*
                InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String message;

                Boolean done = false;

                while (((message = bufferedReader.readLine()) != null) &&(!done)){
                    System.out.println("Received from Client: " + message);

                    if (message.compareToIgnoreCase("Exit") == 0) done = true;
                }
                 */

            } catch (IOException e) {
                System.out.println("connection dropped");
            }





        }
    }


}
