package Excepitions;

public class ProdutoNotFoundException extends Exception{

    public ProdutoNotFoundException(){
        super();
    }

    public ProdutoNotFoundException(String erro){
        super(erro);
    }
}
