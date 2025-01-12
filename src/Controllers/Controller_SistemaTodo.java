package Controllers;

import Excepitions.CodigoNotFoundException;
import Excepitions.ValorErradoException;
import Models.TrazAqui;
import Views.View_Erro;
import Views.View_Historico;

import java.util.ArrayList;
import java.util.List;

public class Controller_SistemaTodo implements I_Controllers {
    private TrazAqui sistema;

    public Controller_SistemaTodo(TrazAqui s){
        this.sistema = s;
    }

    public void processa(List<String> opcao){
        switch (opcao.get(0)){
            case "Faturado" : {
                try {
                    List<String> l = new ArrayList<>();
                    if(opcao.get(1).charAt(0) == 'l'){
                        l = this.sistema.totalFaturadoLoja(opcao.get(1));
                    }else{
                        if(opcao.get(1).charAt(0) == 't'){
                            l = this.sistema.totalFaturadoTransportadora(opcao.get(1));
                        }else{
                            l.add("Codigo Invalido");
                        }
                    }
                    View_Historico view = new View_Historico(l);
                    view.run();
                }catch (CodigoNotFoundException e){
                    View_Erro erro = new View_Erro("Codigo inválido");
                    erro.run();
                }
                break;
            }

            case "10Utilizadores" : {
                List<String> l = this.sistema.top10Utilizadores();
                View_Historico view = new View_Historico(l);
                view.run();
                break;
            }

            case "10Transportadoras" : {
                List<String> l = this.sistema.top10Transportadoras();
                View_Historico view = new View_Historico(l);
                view.run();
                break;
            }
        }
    }
}
