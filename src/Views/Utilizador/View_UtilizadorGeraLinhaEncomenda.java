package Views.Utilizador;

import Controllers.I_Controllers;
import Models.Loja.Produto;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View_UtilizadorGeraLinhaEncomenda {

    private List<Produto> produtos;
    private I_Controllers controller;

    public View_UtilizadorGeraLinhaEncomenda(List<Produto> produtos, I_Controllers controller){
        this.produtos = produtos;
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
            System.out.println("Insira: Codigo do produto desejado | S finalizar encomenda");
        }
        else {
            if(paginaAtual == 1){
                System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | Codigo do produto desejado | S finalizar encomenda");
            }
            else{
                if(paginaAtual==totalPaginas){
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| - página anterior | Codigo do produto desejado | S finalizar encomenda");
                }
                else{
                    System.out.println("Insira: "+String.format("Página %d/%d ",paginaAtual,totalPaginas)+"| + próxima página | - página anterior | Codigo do produto desejado | S finalizar encomenda");
                }
            }
        }
    }

    private void showMenu(int index, int tamPag, int elem){
        int pos = (index*tamPag);
        for (int i=0; i<tamPag; i++){
            if(pos<elem){
                Produto p = this.produtos.get(pos);
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
        int elem = this.produtos.size();
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
                    List<String> l = new ArrayList<>();
                    l.add("Finaliza");
                    this.controller.processa(l);
                    System.out.println("Encomenda finalizada com sucesso.");
                    opcao = "S";
                    break;
                }

                default:{
                    if(opcao.charAt(0)=='p'){
                        List<String> l = new ArrayList<>();
                        l.add("Adiciona");
                        l.add(opcao);
                        System.out.println("Insira a quantidade desejada:");
                        l.add(LeituraDados.lerIntComoString());
                        this.controller.processa(l);
                    }
                    else{
                        System.out.println("Codigo produto invalido.");
                    }
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
