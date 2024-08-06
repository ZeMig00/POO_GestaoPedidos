package Views.Loja;

import Controllers.I_Controllers;
import Models.Loja.Produto;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class View_AceitarPedidos {

    private List<String> listaPedidos;
    private I_Controllers controller;

    public View_AceitarPedidos(List<String> listaPedidos, I_Controllers controllers){
        this.listaPedidos = listaPedidos;
        this.controller = controllers;
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
            System.out.println("Insira: A aceitar pedido | S sair");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | A aceitar pedido | S sair");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | A aceitar pedido | S sair");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | A aceitar pedido | S sair");
                }
            }
        }
    }

    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                System.out.println(this.listaPedidos.get(pos));
                pos++;
            }else{
                System.out.println("---");
            }
        }
    }

    public void run(){
        String opcao;
        int index = 0;
        int tamPag = 8;
        int elem = this.listaPedidos.size();
        int totalPaginas = (elem<8)?1:((elem%8==0)?elem/8:(elem/8)+1);

        do {
            this.showMenu(index,tamPag,elem);
            this.showOpcoes(totalPaginas,index+1);
            opcao = LeituraDados.lerString();
            opcao = opcao.toUpperCase();

            switch (opcao){
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

                case "A" :{
                    System.out.println("Insira o indice da encomenda a aceitar.");
                    try {
                        List<String> l = new ArrayList<>();
                        l.add("Aceitar");
                        l.add(LeituraDados.lerIntAnteriorComoString());
                        this.controller.processa(l);
                    }catch (InputMismatchException e){
                        System.out.println("Posição invalida.");
                    }
                    opcao = "S";
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
