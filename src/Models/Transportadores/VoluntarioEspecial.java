package Models.Transportadores;

import Models.Utilizador.GPS;

import java.io.Serializable;

public class VoluntarioEspecial extends Voluntario implements Serializable{

    public boolean aceitoMedicamentos;

    public VoluntarioEspecial(){
        super();
    }

    public VoluntarioEspecial(String codVoluntario, String nomeVoluntario, GPS cord, double raio, boolean aceitoM){
        super(codVoluntario, nomeVoluntario, cord, raio);
        this.aceitoMedicamentos = aceitoM;
    }

    public VoluntarioEspecial(VoluntarioEspecial v){
        super(v);
        this.aceitoMedicamentos = v.isAceitoMedicamentos();
    }

    public boolean isAceitoMedicamentos() {
        return aceitoMedicamentos;
    }

    public void setAceitoMedicamentos(boolean aceitoMedicamentos) {
        this.aceitoMedicamentos = aceitoMedicamentos;
    }

    public VoluntarioEspecial clone(){
        return new VoluntarioEspecial(this);
    }

    public boolean aceitoTransporteMedicamentos(){
        return this.aceitoMedicamentos;
    }
}
