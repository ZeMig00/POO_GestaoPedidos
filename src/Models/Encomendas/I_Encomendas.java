package Models.Encomendas;

import Models.Loja.Produto;

import java.util.List;

public interface I_Encomendas {
    /**
     * Devolve a string correspondente ao codigo de encomenda.
     *
     * @return string do codigo de encomenda.
     */
    public String getCodEncomenda();

    /**
     * Devolve a string correspondente ao codigo do utilizador.
     *
     * @return string do codigo do utilizador.
     */

    public String getCodUtilizador();

    /**
     * Devolve a string correspondente ao codigo da loja.
     *
     * @return string do codigo da loja.
     */
    public String getCodLoja();

    /**
     * Devolve o valor do peso da encomenda.
     *
     * @return valor do peso da encomenda.
     */
    public double getPeso();

    /**
     * Devolve a lista das linhas de encomenda.
     *
     * @return a lista das linhas de encomenda
     */
    public List<LinhaEncomenda> getLinhasEncomenda();

    /**
     * Atualiza o codigo da encomenda
     *
     * @param codEnc novo codigo de encomenda.
     */
    public void setCodEncomenda(String codEnc);

    /**
     * Atualiza o codigo do utilizador.
     *
     * @param codUtilidor novo codigo do Models.Utilizador.
     */
    public void setCodUtilizador(String codUtilidor);

    /**
     * Atualiza o codigo da Models.Loja
     *
     * @param codLoja novo codigo da Models.Loja.
     */
    public void setCodLoja(String codLoja);

    /**
     * Atualiza o valor do peso
     *
     * @param peso novo valor do peso.
     */
    public void setPeso(double peso);

    /**
     * Atualiza a lista da encomenda.
     *
     * @param lc nova lista da encomenda.
     */
    public void setLinhasEncomenda(List<LinhaEncomenda> lc);

    /**
     * Metodo que adiciona uma LinhaEncomenda a lista das linhasEncomenda.
     *
     * @param l LinhaEncomenda a adicionar.
     */
    public void addLinhaEncomenda(LinhaEncomenda l);

    public boolean equals(Object o);

    public boolean contemMedicamentos(List<Produto> medicamentos);

    /**
     * Método que devolve a representação em String de Encomenda.
     *
     * @return String com todos os parametros de Encomenda.
     */

    public String toString();

    /**
     * Método que faz uma cópia do objecto receptor da mensagem.
     * Para tal invoca o construtor de cópia.
     *
     * @return objecto clone do objecto que recebe a mensagem.
     */
    I_Encomendas clone();
}
