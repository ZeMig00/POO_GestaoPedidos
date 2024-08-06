package Models.Utilizador;

import Models.Sistema.PedidoCompleto;
import Models.Sistema.PedidoTransportadora;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utilizador implements Serializable {

    private String codUtilizador;
    private String nome;
    private GPS gps;

    private Map<String, PedidoTransportadora> pedidosPendentes;
    private List<String> notificacoes;
    private Map<String, PedidoCompleto> historico;

    public Utilizador(String codUtilizador, String nome, GPS gps) {
        this.codUtilizador = codUtilizador;
        this.nome = nome;
        this.gps = gps.clone();

        this.pedidosPendentes = new HashMap<>();
        this.notificacoes = new ArrayList<>();
        this.historico = new HashMap<>();
    }

    public Utilizador(Utilizador u){
        this.codUtilizador = u.getCodUtilizador();
        this.nome = u.getNome();
        this.gps = u.getGps();

        this.pedidosPendentes = u.getPedidosPendentes();
        this.notificacoes = u.getNotificacoes();
        this.historico = u.getHistorico();
    }

    public Map<String, PedidoCompleto> getHistorico() {
        Map<String,PedidoCompleto> r = new HashMap<>();
        for (PedidoCompleto p: this.historico.values()){
            r.put(p.getCodigoPedido(),p.clone());
        }
        return r;
    }

    public void setHistorico(Map<String, PedidoCompleto> historico) {
        this.historico = new HashMap<>();
        for (PedidoCompleto p: historico.values()){
            this.historico.put(p.getCodigoPedido(),p.clone());
        }
    }

    public String getCodUtilizador() {
        return codUtilizador;
    }

    public void setCodUtilizador(String codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GPS getGps() {
        return gps.clone();
    }

    public void setGps(GPS gps) {
        this.gps = gps.clone();
    }

    public Map<String, PedidoTransportadora> getPedidosPendentes() {
        Map<String, PedidoTransportadora> res = new HashMap<>();
        for (Map.Entry<String, PedidoTransportadora> e : this.pedidosPendentes.entrySet()){
            res.put(e.getKey(),e.getValue().clone());
        }
        return res;
    }

    public void setPedidosPendentes(Map<String, PedidoTransportadora> pedidosPendentes) {
        this.pedidosPendentes = new HashMap<>();
        for (Map.Entry<String, PedidoTransportadora> e : pedidosPendentes.entrySet()){
            this.pedidosPendentes.put(e.getKey(),e.getValue().clone());
        }
    }

    public List<String> getNotificacoes() {
        List<String> res = new ArrayList<>();

        for (String s : this.notificacoes){
            res.add(s);
        }

        return res;
    }

    public void limpaNotificacoes(){
        this.notificacoes = new ArrayList<>();
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = new ArrayList<>(notificacoes);
    }

    public Utilizador clone(){
        return new Utilizador(this);
    }

    public List<String> getListaHistorico(){
        return this.historico.values().parallelStream().sorted((p1,p2)->(p1.getEntregue().compareTo(p2.getEntregue())==0)? 1 : p1.getEntregue().compareTo(p2.getEntregue()))
                   .map(PedidoCompleto::toString).collect(Collectors.toList());
    }

    public List<PedidoCompleto> getTransportadoresPorAvaliar(){
        List<PedidoCompleto> res = new ArrayList<>();
        for (PedidoCompleto p : this.historico.values()){
            if(!p.isAvaliado()){
                res.add(p.clone());
            }
        }
        return res;
    }

    public void addHistorico(PedidoCompleto p){
        this.historico.put(p.getCodigoPedido(),p.clone());
    }

    public void addNotificacao(String notificacao){
        this.notificacoes.add(notificacao);
    }

    public void addPedidosPendentes(PedidoTransportadora p){
        this.pedidosPendentes.put(p.getCodigoPedido(),p);
    }

    public PedidoTransportadora aceitaTrasnportadora(String codEnc){
        PedidoTransportadora p = this.pedidosPendentes.get(codEnc);
        p.setAceite(true);
        p.setDataAceite(LocalDateTime.now());
        p = p.clone();
        this.pedidosPendentes.remove(codEnc);
        return p;
    }

    public int getNumeroNotificacaoes(){
        return this.notificacoes.size()+this.pedidosPendentes.size();
    }

    public  void removeDeAvaliacao(String codTransportador){
        for (PedidoCompleto p : this.historico.values()){
           if(p.getCodigoTransportadora().equals(codTransportador)){
               p.setAvaliado(true);
           }
        }
    }

}
