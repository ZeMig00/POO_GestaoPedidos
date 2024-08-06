package Models.Utilizador;

import java.io.Serializable;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

/**
 * Escreva a descrição da classe Models.GPS.Models.GPS aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class GPS implements Serializable {

    //Variáveis Instâncias
    private double x;
    private double y;

    /**
     * Construtor por omissão de GPS
     */
    public GPS(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construtor Parametrizado de Models.GPS.Models.GPS
     * Aceita como parâmetros os valores para cada Variável de Instância
     */
    public GPS(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Construtor de cópia de um Models.GPS.Models.GPS
     * Aceita como parâmetros outro Models.GPS.Models.GPS e utiliza os métodos
     * de acesso aos valores das Variáveis de Instância
     */
    public GPS(GPS g){
        this.x = g.getX();
        this.y = g.getY();
    }

    /**
     * Devolve valor referente à Coordenada x
     * @return valor x
     */
    public double getX(){
        return this.x;
    }

    /**
     * Devolve valor referente à Coordenada y
     * @return valor y
     */
    public double getY(){
        return this.y;
    }

    /**
     * Atualiza valor da Coordenada x
     * @param x novo de x
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * Atualiza valor da Coordenada y
     * @param y novo de y
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * Método que determina se dois Models.GPS.Models.GPS são iguais
     * @return booleano que é verdadeiro se todos os valores forem iguais
     */
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        GPS g = (GPS) o;

        return (this.x == g.getX() &&
                this.y == g.getY());
    }

    public double distancia(GPS gps){
        return sqrt(pow(this.x - gps.getX(), 2) + pow(this.y - gps.getY(), 2));
    }

    public double DistanciaRealEmKM(GPS gps2) {
        double _eQuatorialEarthRadius = 6378.1370D;
        double _d2r = (Math.PI / 180D);

        double dlong = (gps2.getY() - this.y) * _d2r;
        double dlat = (gps2.getX() - this.x) * _d2r;

        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(this.x * _d2r) * Math.cos(gps2.getX() * _d2r)
                * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c;

        return d;
    }

    /**
     * Método que devolve a representação em String do Models.GPS.Models.GPS
     * @return String com Informação do Models.GPS.Models.GPS
     */
    public String toString(){
        return "Longitude: " + this.y + "," +
                "Latitude: " + this.x;
    }

    /**
     * Método que faz uma cópia do objecto receptor da mensagem .
     * Para tal invoca o construtor de cópia
     *
     * @return objeto clone do objeto que recebe a mensagem
     */
    public GPS clone(){
        return new GPS(this);
    }
}


/*
@startuml
class Models.GPS.Models.GPS{
--x : double
--y : double
--
++Models.GPS.Models.GPS()
++Models.GPS.Models.GPS(x : double, y : double)
++Models.GPS.Models.GPS(g : Models.GPS.Models.GPS)
--
++getX() : double
++getY() : double
++setX(x : double) : void
++setY(y : double) : void
++equals(o : Object) : boolean
++toString() : String
++clone() : Models.GPS.Models.GPS
}
@enduml
*/
