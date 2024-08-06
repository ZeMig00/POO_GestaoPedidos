package Views;

public class View_Erro {
    private String mensagem;

    public View_Erro(String erro){
        this.mensagem = erro;
    }

    public void run(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println(this.mensagem);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        try { Thread.sleep (1000); } catch (InterruptedException ex) {}
    }
}
