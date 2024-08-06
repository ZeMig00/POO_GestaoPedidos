package Views.Utilizador;

import Controllers.I_Controllers;
import Models.Sistema.PedidoTransportadora;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View_PedidosPendentes {

    private List<PedidoTransportadora> pedidosPendentes;
    private List<String> notificacoes;
    private I_Controllers controllers;

    public View_PedidosPendentes(List<PedidoTransportadora> pedidosPendentes, I_Controllers c, List<String> notificacoes){
        this.pedidosPendentes = pedidosPendentes;
        this.controllers = c;
        this.notificacoes = notificacoes;
    }

    private void listaTransportadorasPendentes(){
        String opcao;

        for (PedidoTransportadora p : this.pedidosPendentes){
            System.out.println(p.toStringUtilizador());
            System.out.println("Aceita Proposta (S/N) | 1 sair");
            opcao = LeituraDados.lerString();
            opcao = opcao.toUpperCase();

            List<String> l = new ArrayList<>();
            switch (opcao) {

                case "S": {
                    l.add("Aceita");
                    l.add(p.getCodigoPedido());
                    this.controllers.processa(l);
                    break;
                }

                case "N": {
                    l.add("Rejeita");
                    l.add(p.getCodigoPedido());
                    this.controllers.processa(l);
                    break;
                }
            }

            if(opcao.equals("1")) break;
        }
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
            System.out.println("Insira: S sair | V ver transportadoras pendentes");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | V ver transportadoras pendentes | S sair");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | V ver transportadoras pendentes | S sair");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | V ver transportadoras pendentes | S sair");
                }
            }

        }
    }

    private void showMenu(int index, int tamPag, int elem){
        System.out.println(this.pedidosPendentes.size() +" Transportadoras Pendentes.");
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                System.out.println(this.notificacoes.get(pos));
                pos++;
            }else{
                System.out.println("---");
            }
        }
    }

    private void showVazio(int tamPag){
        for (int i=0; i<tamPag; i++){
            System.out.println("---");
        }
    }

    public void run(){
        String opcao;
        int index = 0;
        int tamPag = 8;
        int elem = this.notificacoes.size();
        int totalPaginas = (elem<8)?1:((elem%8==0)?elem/8:(elem/8)+1);

        do {
            this.showMenu(index, tamPag, elem);
            this.showOpcoes(totalPaginas, index + 1);
            opcao = LeituraDados.lerString();
            opcao = opcao.toUpperCase();

            switch (opcao) {
                case "+": {
                    index = this.avancaPagina(index, totalPaginas);
                    break;
                }

                case "-": {
                    index = this.recuaPagina(index);
                    break;
                }

                case "V": {
                    if(!this.pedidosPendentes.isEmpty()){
                        this.listaTransportadorasPendentes();
                        opcao = "S";
                    }
                    else System.out.println("Sem transportadoras Pendentes.");
                    break;
                }

                case "S": {
                        break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
