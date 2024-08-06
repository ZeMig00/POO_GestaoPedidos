package Models.Sistema;

import Models.Encomendas.Encomenda;
import Models.Encomendas.LinhaEncomenda;
import Models.Utilizador.GPS;
import Models.Transportadores.I_Transportadores;
import Models.Loja.Loja;
import Models.Loja.LojaComFilasEspera;
import Models.Loja.Produto;
import Models.Loja.Stock;
import Models.Transportadores.Transportadora;
import Models.Transportadores.TransportadoraNormal;
import Models.TrazAqui;
import Models.Utilizador.Utilizador;
import Models.Transportadores.Voluntario;
import Models.Transportadores.VoluntarioNormal;


import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeituraFicheiros {

    private static String nomeFicheiro = "logs_20200416.txt";
    private static String nomeFicheiroApresentacao = "logs_apresentacao.txt";

    /**
     * Funçao que realiza a leitura do ficheiro logs.
     * Retorna o TrazAqui com os dados defalt
     *
     * Como isto só serve (espero eu) para a primeira vez que se executa o programa não esta lá a grande beleza de código
     * Mas serve bem para o que é...deve ser apagado quando ja estiver tudo a dar...
     * Pois deve abrir a aplicaçãp com o estado em bianrio e nao com o ficheiro logs.
     */
    public static TrazAqui lerFicheiro() throws IOException{
        //[4.1] -> criamos as colecoes onde vaamos guardar a informacao do ficheiro.
        //estas colecaos depois de preenchidas vao servir para construir a class TrazAqui
        Map<String,Loja> ls = new HashMap<>();
        Map<String,Utilizador> us = new HashMap<>();
        Map<String, I_Transportadores> ts = new HashMap<>();
        Stock stock = new Stock();
        Map<String,Encomenda> encomendas = new HashMap<>();

        List<PedidoLoja> pls = new ArrayList<>();
        TrazAqui s = new TrazAqui();

        int numLojas =0;

        //[4.2] -> abrir o ficheiros logs
        FileReader arq = new FileReader(nomeFicheiroApresentacao);
        BufferedReader lerArq = new BufferedReader(arq);

        //le a primeira linha
        String linha = lerArq.readLine();
        int opcao = -1;
        while (linha != null) {
            //[4.3] -> vamos buscar o primeiro carater da linha (e subtrarimos 65, talvez par ao numero ficar mais pequeno?)
            //p. ex se a linha começar por Utilizador (85 na table ASCII) entao a opcao fica 20
            opcao = linha.codePointAt(0) - 65;

            //[4.4] -> partimos a linha em dois no carater ":"
            String[] r = linha.split(":",2);
            //[4.4.1] -> r={Utilizador,u48,Francisco Egídio Coutinho Martins Correia,-97.28862,59.067047..}
            //[4.5] -> agora partimos a segunda parte de r nos carateres ','
            String[] valoresLinha = r[1].split(",");
            //[4.6] -> confrome a opção vamos preenchar o map respetivo
            switch (opcao){
                case 0 : {      //Aceites
                    Encomenda ea = encomendas.get(r[1].trim());
                    Loja lea = ls.get(ea.getCodLoja());
                    Utilizador uea = us.get(ea.getCodUtilizador());

                    PedidoUtilizador pu = new PedidoUtilizador(ea,uea.getGps().clone(),LocalDateTime.now());

                    PedidoLoja pl = new PedidoLoja(pu, LocalDateTime.now(),lea.getCodigoLoja(),lea.getCord().clone(),false,"None");
                    pls.add(pl);
                    break;
                }
                case 4 : {      //Encomendas
                    //linha encomenda vai representar os produtos desta encomenda em especifico
                    List<LinhaEncomenda> linhaEnc = new ArrayList<>();
                    //exemplo de uma linha de encomenda e6813,u81,l8,77.31938,p34,Farinha de trigo,2.2728467,5.5159483,p40,Molho de pimenta,1.6600878,42.014664,p4,Detergente,9.424281,8.621707,p60,Banana,2.2497952,9.522067,p49,Leite condensado,5.5749025,0.8051496,p21,Leite integral litro,7.543862,20.61575,p17,Saco de lixo 50l,5.0960507,44.77305,p19,Sumo garrafa 1l,6.0144415,43.476116,p53,Batata,3.306492,38.945602,p48,Creme de leite,2.444421,17.503843,p61,Ovos,1.4774193,15.0444355,p13,Tira manchas,6.2518024,25.874397,p41,Ervilha,4.591317,30.43119,p56,Beterraba,5.814199,2.5673974,p33,Farofa pronta,4.687167,1.8703786,p20,Sumo caixa 500ml,9.236412,42.498604,p16,Saco de lixo 30l,2.5450313,31.850492
                    for(int i=4; i<valoresLinha.length; i+=4){ //salta de 4 em 4 porque o produto tem 4 propriedades
                        //a partir da posicao é que tem os produtos. Para cada produto:
                        //cria uma linha encomenda
                        LinhaEncomenda l = new LinhaEncomenda(valoresLinha[i],valoresLinha[i+1],Double.parseDouble(valoresLinha[i+2]),Double.parseDouble(valoresLinha[i+3]));

                        //cria um produto (TODO estudar os parametros)
                        Produto p = new Produto(valoresLinha[i],valoresLinha[i+1],0.0,(Double.parseDouble(valoresLinha[i+3])/Double.parseDouble(valoresLinha[i+2])),false);
                        //adicionar ao map do stock o produto
                        //adicionar ao stock GLOBAL
                        stock.addStock(p);

                        linhaEnc.add(l);
                    }

                    //cria a encomenda- que sao os primeiros campos da linha
                    Encomenda e = new Encomenda(valoresLinha[0],valoresLinha[1],valoresLinha[2],Double.parseDouble(valoresLinha[3]),new ArrayList<>());
                    e.setLinhasEncomenda(linhaEnc);
                    encomendas.put(e.getCodEncomenda(),e);
                    break;
                }
                case 11 : {    //Lojas
                    if(numLojas<3){
                        GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                        Loja loja = new LojaComFilasEspera(valoresLinha[0], valoresLinha[1], gps);
                        ls.put(loja.getCodigoLoja(), loja);
                        numLojas++;
                    }else{
                        GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                        Loja loja = new Loja(valoresLinha[0], valoresLinha[1], gps);
                        ls.put(loja.getCodigoLoja(), loja);
                    }

                    break;
                }
                case 19 : {     //Transportadoras
                    GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                    Transportadora p = new TransportadoraNormal(valoresLinha[0], valoresLinha[1], gps, valoresLinha[4], Double.parseDouble(valoresLinha[5]), Double.parseDouble(valoresLinha[6]));

                    ts.put(p.getCodigo(),p);

                    break;
                }
                case 20 : {     //Utilizador
                    GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                    Utilizador utilizador = new Utilizador(valoresLinha[0], valoresLinha[1], gps);

                    //adicionar o utilizador criado ao map
                    us.put(utilizador.getCodUtilizador(),utilizador);

                    break;
                }
                case 21 : {     //Voluntarios
                    GPS gps = new GPS(Double.parseDouble(valoresLinha[2]), Double.parseDouble(valoresLinha[3]));
                    Voluntario voluntario = new VoluntarioNormal(valoresLinha[0], valoresLinha[1], gps, Double.parseDouble(valoresLinha[4]));

                    ts.put(voluntario.getCodigo(),voluntario);

                    break;
                }

                default:{
                    System.out.println("Errou");
                    break;
                }

            }

            //passar para a proxima linha
            linha = lerArq.readLine(); // lê da segunda até a última linha
        }
        //fecha o ficheiro
        arq.close();

        //criad um medicament para testar? TODO perguntar
        Produto medicamento = new Produto("p1","Brufene",0.05,5.30,true);
        stock.addStock(medicamento);
        List<Produto> lp = stock.getListaProdutos();

        //[5] -> PREENCHER lojar.para todas as lojas adicionamos otodos os produtos (de modo a que lojas tenham stock),
        // aproveitou- se os produtos ja existentes no ficheiros logs
        for (Produto p : lp){
            for (Loja l : ls.values()){
                l.adicionaProdutoStock(p);
            }
        }

            /*
             * Proximos Codigos para ser gerados
             * Indice 0 -> encomendas -> Maior 8779 -> 8780
             * Indice 1 -> utilizadores -> Maior 97 -> 98
             * Indice 2 -> lojas -> Maior 83 -> 84
             * Indice 3 -> voluntarios -> Maior 73 -> 74
             * Indice 4 -> transportadoras -> Maior 51 -> 52
             * Indice 5 -> produtos -> Maior ??? -> 100
             */

        //[6] -> inicializar logica de IDS. cria-se esta lista que vai servir de base para  ageracao inicial dos codigos
        //para quando se criar um utilizador, transportador, etc. De cada vez que e criado um, é incrementado o codigo de modo a que nao haja iguais
        List<Integer> lprox = new ArrayList<>();
        lprox.add(0,8780);
        lprox.add(1,98);
        lprox.add(2,84);
        lprox.add(3,74);
        lprox.add(4,52);
        lprox.add(5,100);

        //[7] -> criar os perfis a apartir das colecoes ja criadas em cima
        Map<String,Perfil> perfis = new HashMap<>();

        for (Loja l : ls.values()){
            Perfil p = new Perfil(TiposUtilizadores.LOJAS,l.getCodigoLoja(),l.getCodigoLoja(),"1234");
            perfis.put(p.getEmail(),p.clone());
        }

        for (Utilizador l : us.values()){
            Perfil p = new Perfil(TiposUtilizadores.UTILIZADORES,l.getCodUtilizador(),l.getCodUtilizador(),"1234");
            perfis.put(p.getEmail(),p.clone());
        }

        for (I_Transportadores l : ts.values()){
            Perfil p;
            if(l instanceof Transportadora) p = new Perfil(TiposUtilizadores.TRANSPORTADORA,l.getCodigo(),l.getCodigo(),"1234");
            else p = new Perfil(TiposUtilizadores.VOLUNTARIOS,l.getCodigo(),l.getCodigo(),"1234");
            perfis.put(p.getEmail(),p.clone());
        }

        //[7.1] -> perfil de sistema, de administrador
        Perfil pS = new Perfil(TiposUtilizadores.SISTEMA,"Pedro Veloso","pedroVeloso","1234");
        perfis.put(pS.getEmail(),pS.clone());

        //[8] -> com tudo criado acima criamos a nossa classe principal TrazAqui
        //com os utilizador, lojas, transportadores, historico ( vazio porque estamos a inicializar a aplicacao)
        //.. perfis, pedidos a vazio (porque estamos a inicilizar a aplicacao)
        s = new TrazAqui(us, ls, ts, new HashMap<>(),lprox,perfis, new HashMap<>());

        //[9] -> tratar dos pedidos ja processados em cima (no case Aceite)
        //[9.1] para todos os pedidos vamos verificar se o dado pedido é possivel de realizar
        for (PedidoLoja pla : pls){
            //[9.2] -> se nos transportadores existentes do TrazAqui existe algum que aceitaCaracteristicasEncomenda do pedido pla
            boolean conseguiu = s.atribuiEntregador(pla);
            if(!conseguiu){
                //[9.3] -> se nao conseguiu vamos as lojas ls, obtemos a loja do pedido pla, e pomoso pedido em espera
                ls.get(pla.getLoja()).addPedidoUtilizador(new PedidoUtilizador(pla.getEncomenda(),us.get(pla.getUtilizador()).getGps().clone(),LocalDateTime.now()));
            }
        }
        //[10] -> retornamos o TrazAqui
        return s;
    }

    /**
     * Funcao que lê o estado dos nossos dados guardados em binario
     * @param nomeFicheiroBinario nome do ficheiro onde contem os dados a ler.
     * @return A estrutura carregado e pronta a utilizar.
     */
    //[2]-> tentar ler o ficheiro binario (leitura normal usando as funcoes nativas do java para abrir ficheiros)
    public static TrazAqui carregaEstado(String nomeFicheiroBinario) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFicheiroBinario);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TrazAqui s = (TrazAqui) ois.readObject();
        ois.close();

        return s;
    }
}
