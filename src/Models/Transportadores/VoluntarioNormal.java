package Models.Transportadores;

import Models.Utilizador.GPS;

import java.io.Serializable;

public class VoluntarioNormal extends Voluntario implements Serializable{

    public VoluntarioNormal(){
        super();
    }

    public VoluntarioNormal(String codVoluntario, String nomeVoluntario, GPS cord, double raio){
        super(codVoluntario, nomeVoluntario, cord, raio);
    }

    public VoluntarioNormal(VoluntarioNormal v){
        super(v);
    }

    public VoluntarioNormal clone(){
        return new VoluntarioNormal(this);
    }

    public boolean aceitoTransporteMedicamentos(){
        return false;
    }
}
