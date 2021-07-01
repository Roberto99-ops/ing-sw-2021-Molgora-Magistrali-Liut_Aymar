# Software Engineering Project 2021

Group project #61 
Developed by :   

                 - Roberto Molgora

                 - Juan David Liut Aymar
                 
                 - Francesco Magistrali
                
Features developed : Complete rules + CLI + Socket
                
1) How to play the game : 

    You can play the game alone or in company. If you want to play in company it's sufficient to connect more than 1 computer to the server.
 
    1.1) How to run the server :
   
       The server can be run in several ways; the easiest one is to run it on IntelliJ just creating a configuration 
       that runs the ServerJar.jar file.
       N.B.
       It should be possible to run it on ubuntu or some other Linux-based virtual machines
       or extension, by writing java -jar ServerJar.jar on the command line, however it's not guaranteed 
       this method will work.
       
   1.2) How to run the client :
   
       The client use a CLI that could be bad visualized on IntelliJ or other editors non Linux-based, so: 
       if you are from Linux or Mac it should work well just running it on IntelliJ 
       (follow istructions given in the 1.1 section), else if you are from Windows is recommended to run 
       it on ubuntu by installing Bash on Ubuntu on Windows 
       (istructions here https://github.com/michele-bertoni/W10JavaCLI.git) 
       and running it with the java -jar ClientJar.jar command.
       N.B.
       The CLI it's been studied to work with "DejaVu sans mono for Powerline" font 
       (font size depends by your screen size; if you are form IntelliJ spaceline should be set to 0.8),
       so before starting you have to install it on your PC or it could be bad visualized 
       (you can find the font here https://github.com/michele-bertoni/W10JavaCLI.git, 
       just download it, run and set it as default font in the program you're using).
       
  N.B.
  The server will ask to the first player that connects, if he/she wants to play alone or in company, if you choose company then a timer of 20 seconds starts waiting for other       players to connect.
