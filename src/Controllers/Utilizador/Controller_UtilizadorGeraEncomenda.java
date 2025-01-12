package Controllers.Utilizador;

import Controllers.I_Controllers;
import Excepitions.CodigoNotFoundException;
import Excepitions.ValorErradoException;
import Models.Loja.Produto;
import Models.TrazAqui;
import Views.Utilizador.View_UtilizadorGeraLinhaEncomenda;
import Views.View_Erro;

import java.util.List;

public class Controller_UtilizadorGeraEncomenda implements I_Controllers {

    private TrazAqui trazAqui;
    private String utilizador;

    public Controller_UtilizadorGeraEncomenda(TrazAqui s, String u){
        this.trazAqui = s;
        this.utilizador = u;
    }

    public void processa(List<String> opcao){
        if (opcao.get(0).equals("CriaLinhaEncomenda")) {
            try {
                List<Produto> produtos = this.trazAqui.getProdutosLoja(opcao.get(1));
                View_UtilizadorGeraLinhaEncomenda view = new View_UtilizadorGeraLinhaEncomenda(produtos,new Controller_UtilizadorGeraLinhaEncomenda(this.trazAqui,this.utilizador,opcao.get(1)));
                view.run();
            }catch (CodigoNotFoundException e){
                View_Erro erro = new View_Erro(e.getMessage());
                erro.run();
            }
        }
    }
}
