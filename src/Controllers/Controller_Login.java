package Controllers;

import Controllers.Loja.Controller_Loja;
import Controllers.Transportadores.Controller_Transportadora;
import Controllers.Transportadores.Controller_Voluntario;
import Controllers.Utilizador.ControllerUtilizador;
import Excepitions.ValorErradoException;
import Models.Sistema.TiposUtilizadores;
import Models.Transportadores.I_Transportadores;
import Models.Utilizador.GPS;
import Models.Loja.Loja;
import Models.Sistema.Perfil;
import Models.TrazAqui;
import Models.Transportadores.Transportadora;
import Models.Transportadores.TransportadoraCertificada;
import Models.Transportadores.TransportadoraNormal;
import Models.Utilizador.Utilizador;
import Models.Transportadores.Voluntario;
import Models.Transportadores.VoluntarioEspecial;
import Models.Transportadores.VoluntarioNormal;
import Views.Loja.View_Loja;
import Views.Transportadores.View_Transportador;
import Views.Utilizador.View_Utilizador;
import Views.View_Sistema;
import Views.View_Erro;

import java.util.List;

public class Controller_Login implements I_Controllers{

    private TrazAqui sistema;

    public Controller_Login(TrazAqui s){
        this.sistema = s;
    }

    public void processa(List<String> opcao){
        switch (opcao.get(0)){
            case "Login":{
                //pos0-tipo de acao  login)
                //pos1-email
                //pos2-password
                String email = opcao.get(1);
                String pass = opcao.get(2);

                //este try valida o login
                try {
                    //comecamos por validar o o email.
                    //o metodo getPerfil se nao encontrar, lança uma excecao
                    Perfil p = this.sistema.getPerfil(email);

                    //jatemos o perfil, e se chegou aqui e porque encontrou (nao lancou excecao)
                    //email ja validado entao verificar a pass
                    if(pass.equals(p.getPass())){

                        //se a pass estiver correta entao vamos ver que tipo de perfil (que e do tipo ENUM)
                        switch (p.getTipo()) {
                            //conforme o tipo de Perfil vamos correr a view correspondente
                            case UTILIZADORES: {
                                //criamos o controller
                                I_Controllers controller = new ControllerUtilizador(sistema,p.getCodigo());
                                //obtemos o utilizador atraved do seu codigo (obtido atravse do perfil)
                                Utilizador u = sistema.getUtilizador(p.getCodigo());
                                //criamos a view e passamos-lhe
                                View_Utilizador view = new View_Utilizador(controller,this.sistema,u.getCodUtilizador(),u.getNome());
                                view.run();
                                break;
                            }

                            case LOJAS: {
                                Loja l = sistema.getLoja(p.getCodigo());
                                I_Controllers controller = new Controller_Loja(sistema,p.getCodigo());
                                View_Loja viewLoja = new View_Loja(controller,this.sistema,l.getCodigoLoja(),l.getNomeLoja());
                                viewLoja.run();
                                break;
                            }

                            case VOLUNTARIOS: {
                                I_Transportadores t = sistema.getTransportador(p.getCodigo());
                                I_Controllers controller = new Controller_Voluntario(sistema,p.getCodigo());
                                View_Transportador viewVoluntario = new View_Transportador(this.sistema,t.getCodigo(),controller);
                                viewVoluntario.run();
                                break;
                            }

                            case TRANSPORTADORA: {
                                I_Transportadores t = sistema.getTransportador(p.getCodigo());
                                I_Controllers controller = new Controller_Transportadora(sistema,p.getCodigo());
                                View_Transportador viewTransportadora = new View_Transportador(this.sistema,t.getCodigo(),controller);
                                viewTransportadora.run();
                                break;
                            }

                            case SISTEMA: {
                                I_Controllers controller = new Controller_SistemaTodo(sistema);
                                View_Sistema viewSistema = new View_Sistema(controller);
                                viewSistema.run();
                                break;
                            }
                        }
                    }else {
                        View_Erro view_erro = new View_Erro("PassWord Errada");
                        view_erro.run();
                    }

                }catch (ValorErradoException e){
                    View_Erro view_erro = new View_Erro("Email invalido");
                    view_erro.run();
                }

                break;
            }

            case "Creat":{
                switch (opcao.get(1)){
                    case "Voluntario" :{
                        //pos0-tipo de acao (criarConta (Creat) ou login)
                        //pos1-tipo de conta a criar
                        //pos2-email
                        //pos3-password
                        //pos4-primeiro nome
                        //pos5-ultimo nome
                        //pos6-longitude
                        //pos7-latitude
                        //pos8-raio acao
                        //pos9-se tem medicamente (S/N)
                        String codigo = this.sistema.geraCodigoVoluntario();
                        Perfil p = new Perfil(TiposUtilizadores.VOLUNTARIOS,codigo,opcao.get(2),opcao.get(3));
                        String nome = String.format("%s %s",opcao.get(4),opcao.get(5));
                        GPS gps = new GPS(Double.parseDouble(opcao.get(6)),Double.parseDouble(opcao.get(7)));

                        if(opcao.get(9).toUpperCase().equals("S")){
                            Voluntario v = new VoluntarioEspecial(codigo,nome,gps,Double.parseDouble(opcao.get(8)),true);
                            this.sistema.adicionaTransportador(v);
                            this.sistema.adicionaPerfil(p);
                        }
                        else {
                            Voluntario v = new VoluntarioNormal(codigo,nome,gps,Double.parseDouble(opcao.get(8)));
                            this.sistema.adicionaTransportador(v);
                            this.sistema.adicionaPerfil(p);
                        }

                        I_Transportadores t = sistema.getTransportador(p.getCodigo());
                        I_Controllers controller = new Controller_Voluntario(sistema,p.getCodigo());
                        View_Transportador viewVoluntario = new View_Transportador(this.sistema,t.getCodigo(),controller);
                        viewVoluntario.run();
                        break;
                    }
                    case "Utilizador" :{
                        //pos0-tipo de acao (criarConta (Creat) ou login)
                        //pos1-tipo de conta a criar
                        //pos2-email
                        //pos3-password
                        //pos4-primeiro nome
                        //pos5-ultimo nome
                        //pos6-longitude
                        //pos7-latitude
                        String codigo = this.sistema.geraCodigoUtilizador();
                        Perfil p = new Perfil(TiposUtilizadores.UTILIZADORES,codigo,opcao.get(2),opcao.get(3));
                        String nome = String.format("%s %s",opcao.get(4),opcao.get(5));
                        GPS gps = new GPS(Double.parseDouble(opcao.get(6)),Double.parseDouble(opcao.get(7)));
                        Utilizador u = new Utilizador(codigo,nome,gps);
                        this.sistema.adicionaUtilizador(u);
                        this.sistema.adicionaPerfil(p);

                        I_Controllers controller = new ControllerUtilizador(sistema,p.getCodigo());
                        Utilizador u2 = sistema.getUtilizador(p.getCodigo());
                        View_Utilizador view = new View_Utilizador(controller,this.sistema,u2.getCodUtilizador(),u2.getNome());
                        view.run();
                        break;
                    }
                    case "Transportadora" :{
                        //pos0-tipo de acao (criarConta (Creat) ou login)
                        //pos1-tipo de conta a criar  (transportadora)
                        //pos2-email
                        //pos3-password
                        //pos4-nome empresa
                        //pos5-nif empresa
                        //pos6-longitude
                        //pos7-latitude
                        //pos8-preco medio por KM
                        //pos9-raio acao
                        //pos10-medicamentos ou nao
                        String codigo = this.sistema.geraCodigoTransportadora();
                        Perfil p = new Perfil(TiposUtilizadores.TRANSPORTADORA,codigo,opcao.get(2),opcao.get(3));
                        GPS gps = new GPS(Double.parseDouble(opcao.get(6)),Double.parseDouble(opcao.get(7)));

                        if(opcao.get(10).toUpperCase().equals("S")){
                            Transportadora v = new TransportadoraCertificada(codigo,opcao.get(4),gps,opcao.get(5),Double.parseDouble(opcao.get(9)),Double.parseDouble(opcao.get(8)),true);
                            this.sistema.adicionaTransportador(v);
                            this.sistema.adicionaPerfil(p);
                        }
                        else {
                            Transportadora v = new TransportadoraNormal(codigo,opcao.get(4),gps,opcao.get(5),Double.parseDouble(opcao.get(9)),Double.parseDouble(opcao.get(8)));
                            this.sistema.adicionaTransportador(v);
                            this.sistema.adicionaPerfil(p);
                        }

                        I_Transportadores t = sistema.getTransportador(p.getCodigo());
                        I_Controllers controller = new Controller_Transportadora(sistema,p.getCodigo());
                        View_Transportador viewTransportadora = new View_Transportador(this.sistema,t.getCodigo(),controller);
                        viewTransportadora.run();
                        break;
                    }
                    case "Loja" :{
                        //pos0-tipo de acao Creat
                        //pos1-tipo de conta a criar  (Loja)
                        //pos2-email
                        //pos3-password
                        //pos4-nome loja
                        //pos5-longitude
                        //pos6-latitude
                        String codigo = this.sistema.geraCodigoLoja();
                        Perfil p = new Perfil(TiposUtilizadores.LOJAS,codigo,opcao.get(2),opcao.get(3));
                        GPS gps = new GPS(Double.parseDouble(opcao.get(5)),Double.parseDouble(opcao.get(6)));
                        Loja l = new Loja(codigo,opcao.get(4),gps);
                        this.sistema.adicionaLoja(l);
                        this.sistema.adicionaPerfil(p);

                        Loja l2 = sistema.getLoja(p.getCodigo());
                        I_Controllers controller = new Controller_Loja(sistema,p.getCodigo());
                        View_Loja viewLoja = new View_Loja(controller,this.sistema,l2.getCodigoLoja(),l2.getNomeLoja());
                        viewLoja.run();
                        break;
                    }
                }

                break;
            }
        }
    }
}
