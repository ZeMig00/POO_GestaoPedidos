package Excepitions;

public class ValorErradoException extends Exception {
    public ValorErradoException(){
        super();
    }

    public ValorErradoException(String erro){
        super(erro);
    }
}
