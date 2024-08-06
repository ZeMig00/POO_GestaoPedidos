package Models.Sistema;

public enum CondicoesAtomosfericas {

    SOL(0),NUBLADO(1),CHUVA(2);

    public int estadoTempo;

    CondicoesAtomosfericas(int estadoTempo){
        this.estadoTempo = estadoTempo;
    }

    public int getEstadoTempo(){
        return this.estadoTempo;
    }

    public boolean equals(int valor){
        return this.estadoTempo==valor;
    }
}
