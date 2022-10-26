public class Jogada {
    private String accao;
    private NoJogoAB estado;

    public Jogada( String accao, NoJogoAB estado)
    {
        this.accao = accao;
        this.estado = estado;
    }

    public String getAccao() {
        return accao;
    }

    public NoJogoAB getEstado() {
        return estado;
    }

    public String toString() {
        return "Jogada: "+ accao+"\n"+estado.toString();
    }


}
