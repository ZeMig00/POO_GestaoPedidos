package Views.Utilizador;

import Controllers.I_Controllers;
import Models.TrazAqui;
import Models.Utilizador.Utilizador;
import Views.LeituraDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View_Utilizador {

    private I_Controllers controller;
    private int notificacoes;

    private TrazAqui sistema;
    private Utilizador utilizador;
    private String nome;
    private String cod;

    public View_Utilizador(I_Controllers controller,TrazAqui s, String cod, String nome){
        this.controller = controller;
        this.sistema = s;
        this.utilizador = s.getUtilizador(cod);
        this.notificacoes = utilizador.getNumeroNotificacaoes();
        this.nome = nome;
        this.cod = cod;
    }

    private void relload(){
        this.utilizador = sistema.getUtilizador(this.cod);
        this.notificacoes = utilizador.getNumeroNotificacaoes();
    }

    private String showMenu(){
        String opcao = "";

        System.out.println("Bem Vindo : " + this.nome + "\n");
        System.out.println("1-> Pedir Encomenda.");
        System.out.println("2-> Avaliar Voluntarios/Transportadoras.");
        System.out.println("3-> Histórico de Encomendas.");
        if(this.notificacoes>0) System.out.println("4-> Notificações ("+this.notificacoes+").");
        System.out.println("S-> Sair");

        opcao = LeituraDados.lerString();
        return opcao.toUpperCase();
    }

    public void run(){
        String opcao;
        do {
            this.relload();
            opcao = this.showMenu();

            if(opcao.equals("4") && this.notificacoes<=0) opcao = "I";

            List<String> l = new ArrayList<>();
            switch (opcao){
                case "1" :{
                    l.add("CriaEncomenda");
                    this.controller.processa(l);
                    break;
                }

                case "2" :{
                    l.add("Avaliar");
                    this.controller.processa(l);
                    break;
                }

                case "3" :{
                    l.add("Historico");
                    this.controller.processa(l);
                    break;
                }

                case "4" :{
                    l.add("MensagensPendentes");
                    this.controller.processa(l);
                    break;
                }

                case "S" :{
                    break;
                }

                default:{
                    System.out.println("Opção Inválida.");
                    break;
                }
            }

        }
        while (!opcao.equals("S"));
    }
}
