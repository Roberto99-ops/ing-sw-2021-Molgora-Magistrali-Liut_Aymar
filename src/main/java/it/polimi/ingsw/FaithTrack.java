package it.polimi.ingsw;

public class FaithTrack {
    private int position;
    private int PV_Track=0; //a seconda di dove si trova il segnalino riceve tot PV: ex cella #17==9PV
                            //Partirebbe da 0 così mentre avanza "raccoglie" i PV sul tracciato
                            //oppure si può usare il PV di PLayer?

    //prende la cella del tracciato in cui si trova il segnalino Fede
    public int GetPosition(){
        return position;
    }

    //verifica che il segnalino si trovi nell'area VaticanReport
    public void VaticanReport(int position){
        int i;
        int VR = Player.getVR(); // VR = vatican report
        // caso in cui un giocatore arrivi per primo a fare il VR
        if ((position>=8 && position<=15)&& VR==0){
            PV_Track+=2; //il VR della prima area vale 2
            //controllo che i giocatori si trovino nell'area del VR e se lo sono sommo i punti
            for (i=0; i<3; i++){
                if (Player/*numero*/.trackposition>4 && Player/*numero*/.trackposition<9){
                    Player/*numero*/.PV+=2;
                }
            }
            Player.setVR(1); //setta il VR di tutti i giocatori a 1 perchè la prima area è stata superata
        } else if ((position>=16 && position<=23)&& VR==1){
            PV_Track+=3; // seconda area vale 3
            for (i=0; i<3; i++){
                if (Player/*numero*/.trackposition>11 && Player/*numero*/.trackposition<17){
                    Player/*numero*/.PV+=3;
                }
            }
            Player.setVR(2);
        } else if ((position==24)&& VR==1){
            PV_Track+=4; // terza area vale 4
            for (i=0; i<3; i++){
                if (Player/*numero*/.trackposition>18) {
                    Player/*numero*/.PV=+ 4;
                }
            }
            //chiama EndGame() e Victory()
            Game.EndGame();
            Game.Victory();
        }

    }

    public void SetPosition(int position){
        this.position=position;
    }

}
