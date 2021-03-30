package it.polimi.ingsw;

public class FaithTrack {
    private int position;
    private int PV; //a seconda di dove si trova il segnalino riceve tot PV: ex cella #17==9PV
                    //Partirebbe da 0 così mentre avanza "raccoglie" i PV sul tracciato
                    //oppure si può usare il PV di PLayer?

    //prende la cella del tracciato in cui si trova il segnalino Fede
    public int GetPosition(){
        return position;
    }

    //verifica che il segnalino si trovi nell'area VaticanReport
    public int VaticanReport(int position){
        int VR=0; //lo inseriamo qui oppure in Player? (spiegazione in Player)
        /*
        se lo mettessimo qui
        if ((ogg position>=8 && ogg position<=15)&& VR==0){

        } else if ((ogg position>=16 && ogg position<=23)&& VR==1){

        } else if ((ogg position==24)&& VR==1){
            //chiama EndGame() e Victory()
        }
        */

    }

    public void SetPosition(int position){
        return position;
    }

}
