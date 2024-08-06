package Views.Loja;

import Controllers.I_Controllers;
import Models.Loja.Produto;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View_RemoverProdutos {

    private List<Produto> listaProdutos;
    private I_Controllers controller;

    public View_RemoverProdutos(List<Produto> listaProdutos, I_Controllers controllers){
        this.listaProdutos = listaProdutos;
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
            System.out.println("Insira: R remover produto | S sair");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | R remover produto | S sair");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | R remover produto | S sair");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | R remover produto | S sair");
                }
            }
        }
    }

    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                Produto p = this.listaProdutos.get(pos);
                System.out.println(p.getCodigoProduto()+" -> "+p.getNomeProduto()+" ("+p.getPreco()+" €)");
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
        int elem = this.listaProdutos.size();
        int totalPaginas = (elem<8)?1:((elem%8==0)?elem/8:(elem/8)+1);

        do {
            this.showMenu(index,tamPag,elem);
            this.showOpcoes(totalPaginas,index+1);
            opcao = LeituraDados.lerString();
            String opcaoTemp = opcao.toUpperCase();

            switch (opcaoTemp.charAt(0)){
                case '+' :{
                    index = this.avancaPagina(index,totalPaginas);
                    break;
                }

                case '-' :{
                    index = this.recuaPagina(index);
                    break;
                }

                case 'S' :{
                    opcao = "S";
                    break;
                }

                case 'R' :{
                    List<String> l = new ArrayList<>();
                    l.add("R");
                    System.out.println("Insira o codigo do produto a remover.");
                    l.add(LeituraDados.lerString());
                    this.controller.processa(l);
                    opcao = "S";
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
