package it.polimi.ingsw.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

//dobbiamo usare javaSE??

public class Server {
    private static Random random = new Random();
    private final static int SO_TIMEOUT = 10;
    //non deve essere costante la porta
    //il numero di porta viene generato casualmente tra 1000 e 1050 per il fisso e tra 2000 e 2050 per il portatile
    //public static int SOCKET_PORT = 1050;//random.nextInt(8);

    /*
    public static int SOCKET_PORT_A=1000;
    public static int SOCKET_PORT_B=1001;

    public static int SOCKET_PORT_C=1005;
    public static int SOCKET_PORT_D=1006;


    public static int SOCKET_PORT_E=1010;
    public static int SOCKET_PORT_F=1011;


    public static int SOCKET_PORT_G=1015;
    public static int SOCKET_PORT_H=1016;

    public static int SOCKET_PORT_I=1020;
    public static int SOCKET_PORT_J=1021;

    public static int SOCKET_PORT_K=1025;
    public static int SOCKET_PORT_L=1026;


    public static int SOCKET_PORT_M=1030;
    public static int SOCKET_PORT_N=1031;

    public static int SOCKET_PORT_O=1035;
    public static int SOCKET_PORT_P=1036;


    public static int SOCKET_PORT_Q=1040;
    public static int SOCKET_PORT_R=1041;

    public static int SOCKET_PORT_S=1045;
    public static int SOCKET_PORT_T=1046;
    */


    //public static int SOCKET_PORT_WELCOME=1000;

    public static void main(String[] args) throws IOException {
        System.out.println("Internal ip: " + InetAddress.getLocalHost());

        //apro le porte # ...
        ArrayList<ServerSocket> openSockets = new ArrayList<>();
        ServerSocket socket;
        try {
            //... creando un socket per quei numeri di porta
            for (int i=1000;i<1020;i++){
                socket = new ServerSocket(i);
                socket.setSoTimeout(SO_TIMEOUT);
                openSockets.add(socket);
            }
            System.out.println("Server is running");
        } catch (IOException e) {
            System.out.println("cannot open server socket");
            System.exit(1);
            return;
        }
        //...e il server si mette in ascolto.
        // Per ogni nuova connessione stabilita, viene creato un nuovo thread di ClientHandler
        int numberofsockets=0; //serve cosicchè il primo giocatore sappia di essere il primo e crei la partita
        while (true) {
            /* accepts connections; for every connection we accept,
             * create -- a new Thread executing a ClientHandler -- */
            Socket client= new Socket();
            for(int i=0; !client.isConnected(); i++) {
                if(i==20) i=0;
                try {
                    client = openSockets.get(i).accept();
                } catch (IOException e) {}
                //if (client.isConnected()) break;
            }
            //il clientHandler si occupa di gestire la connessione con il client
            numberofsockets++;
            ClientHandler clientHandler = new ClientHandler(client, numberofsockets);
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


        }
    }


}
