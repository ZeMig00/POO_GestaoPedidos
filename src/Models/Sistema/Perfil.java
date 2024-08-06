package Models.Sistema;

import java.io.Serializable;

public class Perfil implements Serializable {

    private TiposUtilizadores tiposUtilizadores;

    private String email;
    private String pass;
    private String codigo;

    public Perfil(TiposUtilizadores tipo, String codigo, String email, String pass) {
        this.codigo = codigo;
        this.tiposUtilizadores = tipo;
        this.email = email;
        this.pass = pass;
    }

    public Perfil(Perfil p){
        this.codigo = p.getCodigo();
        this.tiposUtilizadores = p.getTipo();
        this.email = p.getEmail();
        this.pass = p.getPass();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TiposUtilizadores getTipo() {
        return this.tiposUtilizadores;
    }

    public void setTipo(TiposUtilizadores tipo) {
        this.tiposUtilizadores = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Perfil clone(){
        return new Perfil(this);
    }
}
