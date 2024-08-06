package Excepitions;

public class EncomendaNotFoundException extends Exception{
    public EncomendaNotFoundException(){
        super();
    }

    public EncomendaNotFoundException(String erro){
        super(erro);
    }
}
