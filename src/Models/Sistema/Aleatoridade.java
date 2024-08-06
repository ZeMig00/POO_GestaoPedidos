package Models.Sistema;

import java.util.Random;

public class Aleatoridade {

    public static double geraTaxaAleatoridade(){
        Random gerador = new Random();
        int estadoTempoAtual = gerador.nextInt(3);

        double taxa = -1;

        if(CondicoesAtomosfericas.SOL.equals(estadoTempoAtual)){
            taxa = 1;
        }

        if(CondicoesAtomosfericas.NUBLADO.equals(estadoTempoAtual)){
            taxa = 1.07;
        }

        if(CondicoesAtomosfericas.CHUVA.equals(estadoTempoAtual)){
            taxa = 1.3;
        }

        return taxa;
    }
}
