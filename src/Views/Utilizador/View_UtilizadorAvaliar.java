package Views.Utilizador;

import Controllers.I_Controllers;
import Models.Sistema.PedidoCompleto;
import Models.Sistema.PedidoLoja;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class View_UtilizadorAvaliar {

    private I_Controllers controller;
    private List<PedidoCompleto> transportadoresParaAvaliar;

    public View_UtilizadorAvaliar(List<PedidoCompleto> t, I_Controllers controller){
        this.transportadoresParaAvaliar = new ArrayList<>(t);
        this.controller = controller;
    }

    private int avancaPagina(int index, int totalPaginas){
        if(index < totalPaginas-1) index++;
        return index;
    }

    private int recuaPagina(int index){
        if(index > 0) index--;
        return index;
    }

    private void showOpcoes(int totalPaginas, int paginaAtual){
        if(totalPaginas<=1){
            System.out.println("Insira: Codigo para avaliar | S sair");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | Codigo para avaliar | S sair");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | Codigo para avaliar | S sair");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | Codigo para avaliar | S sair");
                }
            }

        }
    }

    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){

                PedidoCompleto p = this.transportadoresParaAvaliar.get(pos);
                String s = p.getCodigoTransportadora();
                if (s.charAt(0) == 'v') {
                    String tempo = p.calculaTempoTransorte();
                    System.out.println(String.format("Voluntario: %s -> Entregou em %s",p.getCodigoTransportadora(),tempo));
                } else {
                    String tempo = p.calculaTempoTransorte();
                    System.out.println(String.format("Transportadora: %s -> Entregou em %s por %f euros",p.getCodigoTransportadora(),tempo,p.getPrecoSugerido()));
                }

                pos++;
            }else{
                System.out.println("---");
            }
        }
    }

    private boolean codigoValido(String cod){
        boolean res = false;
        Iterator<PedidoCompleto> it = this.transportadoresParaAvaliar.iterator();
        PedidoCompleto s;

        while (it.hasNext() && !res){
            s = it.next();
            res = s.getCodigoTransportadora().equals(cod);
        }

        return res;
    }

    public void run(){
        String opcao;
        int index = 0;
        int tamPag = 8;
        int elem = this.transportadoresParaAvaliar.size();
        int totalPaginas = (elem<8)?1:((elem%8==0)?elem/8:(elem/8)+1);

        do {
            this.showMenu(index,tamPag,elem);
            this.showOpcoes(totalPaginas,index+1);
            opcao = LeituraDados.lerString();
            String opcaoTemp = opcao.toUpperCase();

            switch (opcaoTemp){
                case "+" :{
                    index = this.avancaPagina(index,totalPaginas);
                    break;
                }

                case "-" :{
                    index = this.recuaPagina(index);
                    break;
                }

                case "S" :{
                    opcao = "S";
                    break;
                }

                default:{
                    if(this.codigoValido(opcao)){
                        List<String> l = new ArrayList<>();
                        l.add("Avalia");
                        l.add(opcao);
                        System.out.println("Insira a avaliação a atribuir :");
                        l.add(LeituraDados.lerIntComoString());
                        this.controller.processa(l);
                        opcao = "S";
                    }
                    else{
                        System.out.println("Codigo invalido.");
                    }
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
