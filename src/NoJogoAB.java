import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public abstract class NoJogoAB {
    private static int vez;
    private static int nivelMax;
    private static int TEMPO_LIMITE = 5;
    public static int VITORIA = 1000000;
    public static int DERROTA = -1000000;

    private Jogada noMelhor = null;
    private static Date horaInic;

    private int nivel;

    public NoJogoAB( int nivel) {
        this.nivel = nivel;
    }

//    public abstract String getJogada();

    public abstract ArrayList<Jogada> expandeAB();

    public abstract double getH();

    public static int getNivelMax() {
        return nivelMax;
    }

    public Jogada getMelhor() {
        return noMelhor;
    }

    public int getNivel() {
        return nivel;
    }

    protected static void setVez( String st) {
        vez = 0;
        try {
            vez = Integer.parseInt(st);
        }
        catch ( Exception ex) {
            ex.printStackTrace();
        }
    }

    protected static int getVez() {
        return vez;
    }


// --------------------------------------------------


    public int getSeg() {
        return (int)(new Date().getTime()-horaInic.getTime())/1000;
    }

    public String processaAB( JTextField tf) {
        ArrayList<Jogada> suc = expandeAB();
        double maior = DERROTA - 1;
        noMelhor = null;
        nivelMax = 5;       // no mínimo...
        horaInic = new Date();
        while (getSeg() < TEMPO_LIMITE && nivelMax < 50 && maior < VITORIA) {
            Jogada melhorNivel = null;
            maior = DERROTA - 1;
            for (Jogada cand : suc) {
                double vMin = cand.getEstado().valorMin( -99999999, 99999999);
                if (vMin > maior || (vMin == maior && talvez())) {
                    maior = vMin;
                    melhorNivel = cand;
                    if (tf != null)
                        tf.setText("Nivel:"+nivelMax+"  "+getSeg()+"s  "+maior+" : "+melhorNivel.getAccao());
                    else
                        System.out.println("Nivelx:"+nivelMax+"  "+getSeg()+"s  "+maior+" : "+melhorNivel.getAccao());
                }
            }
            nivelMax++;
            if (melhorNivel != null) {
                noMelhor = melhorNivel;
                if (tf != null)
                    tf.setText("Nivel:"+nivelMax+"  "+getSeg()+"s  "+maior+" : "+noMelhor.getAccao());
                else
                    System.out.println("Nivel:"+nivelMax+"  "+getSeg()+"s  "+maior+" : "+noMelhor.getAccao());
            }
            tf.repaint();
        }
        if (noMelhor != null)
            return noMelhor.getAccao();
        else
            return "passo";
    }

    public double valorMax( double alfa, double beta) {
        if (nivel >= nivelMax || getSeg() > TEMPO_LIMITE)
            return getH();
        ArrayList<Jogada> suc = expandeAB();
        if (suc.size() == 0)
            return getH();
        for (Jogada cand : suc) {
            double vMin = cand.getEstado().valorMin( alfa, beta);
            if (vMin > alfa) {
                alfa = vMin;
            }
            if (alfa >= beta)
                return beta;
        }
        return alfa;
    }

    public double valorMin( double alfa, double beta) {
        if (nivel >= nivelMax || getSeg() > TEMPO_LIMITE)
            return getH();
        ArrayList<Jogada> suc = expandeAB();
        if (suc.size() == 0)
            return getH();
        for (Jogada cand : suc) {
            double vMax = cand.getEstado().valorMax( alfa, beta);
            if (vMax < beta) {
                beta = vMax;
            }
            if (beta <= alfa)
                return alfa;
        }
        return beta;
    }

    private boolean talvez() {
        return Math.random() > 0.5;
    }

}