package Views.Transportadores;

import Controllers.I_Controllers;
import Views.LeituraDados;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View_EncomendasParaRecolha {

    private List<String> encomendasParaRecolha;
    private I_Controllers controller;

    public View_EncomendasParaRecolha(List<String> l, I_Controllers controller){
        this.encomendasParaRecolha = new ArrayList<>(l);
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
            System.out.println("Insira: S sair | Código da encomenda a aceitar.");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | S sair | Código da encomenda a aceitar.");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | S sair | Código da encomenda a aceitar.");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | S sair | Código da encomenda a aceitar.");
                }
            }

        }
    }

    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                System.out.println(this.encomendasParaRecolha.get(pos));
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
        int elem = this.encomendasParaRecolha.size();
        int totalPaginas = (elem<8)?1:((elem%8==0)?elem/8:(elem/8)+1);

        if(elem==0){
            do {
                this.showVazio(tamPag);
                this.showOpcoes(totalPaginas, index + 1);
                opcao = LeituraDados.lerString();
                opcao = opcao.toUpperCase();
            }
            while (!opcao.equals("S"));
        }
        else {
            do {
                this.showMenu(index, tamPag, elem);
                this.showOpcoes(totalPaginas, index + 1);
                opcao = LeituraDados.lerString();
                String opcaoTemp = opcao.toUpperCase();

                switch (opcaoTemp) {
                    case "+": {
                        index = this.avancaPagina(index, totalPaginas);
                        break;
                    }

                    case "-": {
                        index = this.recuaPagina(index);
                        break;
                    }

                    case "S": {
                        opcao = "S";
                        break;
                    }

                    default:{
                        if(opcao.charAt(0)=='e'){
                            List<String> l = new ArrayList<>();
                            l.add("PedeEncomenda");
                            l.add(opcao);
                            this.controller.processa(l);
                            opcao = "S";
                        }
                        else {
                            System.out.println("Opçao Invalida");
                        }
                    }
                }
            }
            while (!opcao.equals("S"));
        }
    }
}
