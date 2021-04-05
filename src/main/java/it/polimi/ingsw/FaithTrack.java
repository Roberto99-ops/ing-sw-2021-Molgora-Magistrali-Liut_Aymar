package it.polimi.ingsw;

public class FaithTrack {
    private int position;
    private int PV; //a seconda di dove si trova il segnalino riceve tot PV: ex cella #17==9PV
                    //Partirebbe da 0 così mentre avanza "raccoglie" i PV sul tracciato
                    //oppure si può usare il PV di PLayer?
    private int VR;

    //prende la cella del tracciato in cui si trova il segnalino Fede
    public int GetPosition() {
        return position;
    }

    // ritorna il numero di PV in relazione alla posizione
    public int getPV (int position) {
        if (this.position < 3) {
            return 0;
        } else if (this.position > 2 && this.position < 6) {
            return 1;
        } else if (this.position > 5 && this.position < 9) {
            return 2;
        } else if (this.position > 8 && this.position < 12) {
            return 4;
        } else if (this.position > 11 && this.position < 15) {
            return 6;
        } else if (this.position > 14 && this.position < 18) {
            return 9;
        } else if (this.position > 17 && this.position < 21) {
            return 12;
        } else if (this.position > 20 && this.position < 24) {
            return 16;
        } else if (this.position == 24) {
           // Game.this.Endgame();
            return 20;
            /*
            Game.this.Endgame();
            é giusto scriverli così?
            dove li metto? in quale metodo? perchè potrei anche
            metterlo in vaticanreport oppure in incrementposittion
             */
        } else {
            return 25; // exception
        }
    }

    //verifica che il segnalino si trovi nell'area VaticanReport
    public void vaticanReport() {
        if (this.position < 5) {
            this.VR=0;
        } else if (this.position > 4 && this.position < 9) {
            this.VR=1;
        } else if (this.position > 11 && this.position < 17) {
            this.VR=2;
        } else if (this.position > 18) {
            this.VR = 3;
        } else {
            this.VR = 4; //exception
        }
    }

    public int GetVR() {
        return VR;
    }

    public void SetPosition(int pos) {
        this.position = pos;
    }

    public void incrementPosition() {
        this.position++;
    }
}
