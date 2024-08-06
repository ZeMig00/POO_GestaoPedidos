import Controllers.Controller_Login;
import Models.Sistema.LeituraFicheiros;
import Models.TrazAqui;
import Views.View_Erro;
import Views.View_Login;

import java.io.IOException;

public class TrazerAquiApp {

    /**
     * Função que carrega os dados para o sistema.
     * Tenta carregar os dados em formato binário que permite recuperar o estado do ponto de paragem
     * Em caso de erro volta a recarregar os dados default que estão no ficheiro logs.
     * Se não conseguir ler os dados de nenhum dos ficheiros sai com codigo de erro 1.
     * @return o estado carregado do sistema.
     */
    public static TrazAqui carregaSitemaBinario(){
        TrazAqui s = null;

        try {
            //tentar ler ficheiro binario
            s = LeituraFicheiros.carregaEstado("EstadoBinario.txt");
        } catch (IOException | ClassNotFoundException e) {
            //[3] -> entra aqui caso nao tenha conseguido abrir o ficheiro binario (p ex nao existe)
            // imprime uma mensagem a informar
            View_Erro erro = new View_Erro("Erro ao carregar os dados. Recarregando sistema.");
            //imprime a view de erro (todas as views tem a funcao run)
            erro.run();

            try {
                //[4] -> se nao existe o ficheiro binario vamos ler o ficheiros logs...,.txt
                s = LeituraFicheiros.lerFicheiro();
                //[11] -> temos agora a instancia TrazAqui carregada a partir do ficheiro logs
            } catch (IOException ioException) {
                System.out.println("Ficheiro Default em falta.");
                System.exit(1);
            }
        }

        return s;
    }

    public static void main(String[] args) {
        //[1] - ao arrancar tenta ler o ficheiro binario
        // se o ficheiro binario existe entao le a partir dele[2], senao le a partir do logs[11]
        TrazAqui sistema = carregaSitemaBinario();

        //[12] -> carregar a view do login
        View_Login view = new View_Login(new Controller_Login(sistema));
        view.run();

        //Guardamos o nosso estado no ficheiro binario
        try {
            sistema.guardaEstado("EstadoBinario.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
