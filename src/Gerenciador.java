import java.util.Scanner;

import controlador.*;
import laboratorio.*;
import projeto.*;
import usuario.*;
import usuario.colaborador.*;

public class Gerenciador {
    static void clearconsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }

    private static void waitEnter(Scanner reader) {
        System.out.print("\nPressione Enter para continuar...");

        reader.nextLine();
    }
    
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        Controlador controlador = new Controlador();

        boolean loop = true;

        clearconsole();
        Administrador administrador = controlador.cadastroAdministrador(reader);

        clearconsole();
        Laboratorio laboratorio = controlador.cadastroLaboratorio(reader, administrador);

        while(loop) {
            clearconsole();
            controlador.menuPrint(administrador);

            int option = Integer.parseInt(reader.nextLine());

            switch (option) {
                case 1:
                    clearconsole();

                    Colaborador novoColaborador = controlador.criarColaborador(reader, laboratorio);

                    if (novoColaborador != null) {
                        laboratorio.addColaborador(novoColaborador);
                    }

                    waitEnter(reader);
                    break;
                case 2:
                    clearconsole();

                    Projeto novoProjeto = controlador.criarProjeto(reader, laboratorio);

                    if (novoProjeto != null) {
                        laboratorio.addProjeto(novoProjeto);
                    }

                    waitEnter(reader);
                    break;
                case 3:
                    clearconsole();

                    controlador.alocarColaborador(reader, laboratorio);

                    waitEnter(reader);
                    break;
                case 4:
                    clearconsole();

                    controlador.alterarStatus(reader, laboratorio);

                    waitEnter(reader);
                    break;
                case 5:
                    clearconsole();

                    controlador.addPublicacao(reader, laboratorio);

                    waitEnter(reader);
                    break;
                case 6:
                    break;
                case 7:
                    clearconsole();

                    Colaborador colaborador = controlador.buscarColaborador(reader, laboratorio);

                    if (colaborador != null) {
                        controlador.mostrarInformacoesColaborador(colaborador);
                    }

                    waitEnter(reader);
                    break;
                case 8:
                    clearconsole();

                    Projeto projeto = controlador.buscarProjeto(reader, laboratorio);

                    if (projeto != null) {
                        controlador.mostrarInformacoesProjeto(projeto);
                    }

                    waitEnter(reader);
                    break;
                case 9:
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    loop = false;
            }
        }

        reader.close();
    }
}
