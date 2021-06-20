# Software Engineering Project 2021

Group project #61 
Developed by :   - Roberto Molgora
                 - David Juan Liut-Aymar
                 - Francesco Magistrali
                
Feautres developed : Complete rules + CLI + Single game
                
1) How to play the game : 

    You can play the game alone or in company. If you want to play in company it's sufficient to connect more than 1 computer to the server.
 
    1.1) How to run the server :
   
       The server can be run in every way you want; on IntelliJ it can be run just creating a configuration 
       that runs the ServerJar.jar file. Else can be run on ubuntu or some other Linux-based virtual machines
       or extension, by writing java -jar ServerJar.jar on the command line.
       
   1.2) How to run the client :
   
       The client use a CLI that could be bad visualized on IntelliJ or toher editors non Linux-based, so: 
       if you are from Linux or Mac it should work well just running it on IntelliJ 
       (follow istructions given in the 1.1 section), else if you are from Windows is recommended to run it on ubuntu
       by creating a (...) and running it with the java -jar ClientJar.jar command.
       N.B.
       The CLI it's been studied to work with an exact tipe of font and spaceline, so it could be bad visualized if these
       istructions have not been followed.
       
  N.B.
  The server will ask to the first player that connects, if he/she wants to play alone or in company, if you choose company then a timer of 20 seconds starts waiting for other       players to connect.
