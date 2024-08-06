package Models.Loja;

import Excepitions.EncomendaNotFoundException;
import Models.Utilizador.GPS;
import Models.Sistema.PedidoLoja;
import Models.Sistema.PedidoUtilizador;

import java.time.LocalDateTime;

public class LojaComFilasEspera extends Loja{

    private int ocupacao;

    public LojaComFilasEspera(){
        super();
        this.ocupacao = 0;
    }

    public LojaComFilasEspera(String codLoja, String nomeLoja, GPS gps) {
        super(codLoja, nomeLoja, gps);
        this.ocupacao = 0;
    }

    public LojaComFilasEspera(LojaComFilasEspera l) {
        super(l);
        this.ocupacao = l.getOcupacao();
    }

    /**
     * Funcao que retorna o numero de pessoas na fila da loja (não confundir com o numero de pedidos na fila de espera)
     * @return a ocupaçao da loja
     */
    public int getOcupacao() {
        return this.ocupacao;
    }

    /**
     * Funcao que atualiza o numero de pessoas na fila da loja (não confundir com o numero de pedidos na fila de espera)
     * @param ocupacao nova ocupaçao da loja
     */
    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    /**
     * Devolve o numero de pessoas em espera de forma qualitativa e nao quantitativa
     * @return numero de pessoas em espera de forma qualitativa
     */
    private String pessoasEsperaQualitativo(){
        String res = "None";

        if(this.ocupacao>10) res = "Muito";
        if(this.ocupacao>5 && this.ocupacao<10) res = "Moderado";
        if(this.ocupacao<5 && this.ocupacao>0) res = "Pouco";

        return res;
    }

    /**
     * Funçao que devolve um pedido que estava em espera em formato de aceite pela loja
     * Funçao que vai a lista de espera e aceita um pedido devolvendo o pedido da loja
     * @param pos posiçao da lista de espera a retirar o pedido
     * @return pedido da loja para alguem vir buscar a encomenda
     * @throws EncomendaNotFoundException caso a posicao dada nao exista
     */
    public PedidoLoja getPedidoUtilizador(int pos) throws EncomendaNotFoundException {
        try {
            PedidoUtilizador p = super.getListaEspera().get(pos).clone();
            boolean temMedicamentos = super.temMedicamentos(p);
            return new PedidoLoja(p, LocalDateTime.now(),super.getCodigoLoja(),super.getCord().clone(),temMedicamentos,this.pessoasEsperaQualitativo());

        }catch (NullPointerException | IndexOutOfBoundsException e){
            throw new EncomendaNotFoundException();
        }
    }

    /**
     * Funçao que replica esta loja com fila de espera
     * @return copia da loja
     */
    public LojaComFilasEspera clone(){
        return new LojaComFilasEspera(this);
    }
}
