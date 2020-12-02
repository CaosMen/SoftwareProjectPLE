package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import laboratorio.*;
import publicacao.*;
import projeto.*;
import usuario.*;
import usuario.colaborador.*;

public class Controlador {
    public Administrador cadastroAdministrador(Scanner reader) {
        System.out.println("Bem vindo Administrador!\n");

        System.out.print("Digite o seu nome: ");
        String nome = reader.nextLine();
        System.out.print("Digite a sua e-mail: ");
        String email = reader.nextLine();
        System.out.print("Digite o seu login: ");
        String login = reader.nextLine();
        System.out.print("Digite a sua senha: ");
        String senha = reader.nextLine();

        return new Administrador(nome, email, login, senha);
    }

    public Laboratorio cadastroLaboratorio(Scanner reader, Administrador administrador) {
        System.out.println("Bem vindo " + administrador.getNome() + "!\n");

        System.out.print("Digite o nome do Laboratório: ");
        String nome = reader.nextLine();

        return new Laboratorio(nome, administrador);
    }

    public void menuPrint(Administrador administrador) {
        System.out.println("Bem vindo " + administrador.getNome() + "!\n");

        System.out.println("Escolha uma das opções abaixo:\n");
        System.out.println("Digite (1) para adicionar um Colaborador");
        System.out.println("Digite (2) para criar um Projeto");
        System.out.println("Digite (3) para alocar um colaborador a um Projeto");
        System.out.println("Digite (4) para alterar o status de um Projeto");
        System.out.println("Digite (5) para adicionar uma Publicação");
        System.out.println("Digite (6) para adicionar uma Orientação");
        System.out.println("Digite (7) para fazer a consulta de um Colaborador");
        System.out.println("Digite (8) para fazer a consulta de um Projeto");
        System.out.println("Digite (9) para gerar o relatório do Laboratório");
        System.out.println("Digite (10) para listar todos os Colaboradores");
        System.out.println("Digite (0) para Sair!\n");

        System.out.print("Opção: ");
    }

    public Colaborador criarColaborador(Scanner reader, Laboratorio laboratorio) {
        System.out.println("Criação de Colaborador!\n");

        System.out.print("Digite o nome do Colaborador: ");
        String nome = reader.nextLine();

        if (laboratorio.procurarColaborador(nome) == null) {
            System.out.print("Digite o e-mail do Colaborador: ");
            String email = reader.nextLine();
            System.out.println("Escolha um tipo de Colaborador:\n");

            System.out.println("Digite (1) para o tipo Aluno de Graduação");
            System.out.println("Digite (2) para o tipo Aluno de Mestrado");
            System.out.println("Digite (3) para o tipo Aluno de Doutorado");
            System.out.println("Digite (4) para o tipo Professor");
            System.out.println("Digite (5) para o tipo Pesquisador\n");

            System.out.print("Colaborador tipo: ");
            int tipo = Integer.parseInt(reader.nextLine());

            Colaborador novoColaborador;

            if (tipo == 4) {
                novoColaborador = new Professor(nome, email);
            } else if (tipo == 5) {
                novoColaborador = new Pesquisador(nome, email);
            } else {
                novoColaborador = new Aluno(nome, email, tipo - 1);
            }

            System.out.println("\nColaborador Criado!");

            return novoColaborador;
        } else {
            System.out.println("\nJá existe um colaborador com este nome!");

            return null;
        }
    }

    public Colaborador buscarColaborador(Scanner reader, Laboratorio laboratorio) {
        System.out.println("Consulta de Colaborador!\n");

        System.out.print("Digite o nome do Colaborador: ");
        String nome = reader.nextLine();

        Colaborador colaborador = laboratorio.procurarColaborador(nome);

        if (colaborador != null) {
            return colaborador;
        } else {
            System.out.println("\nNão existe um colaborador com este nome!");

            return null;
        }
    }

    public Projeto buscarProjeto(Scanner reader, Laboratorio laboratorio) {
        System.out.println("Consulta de Projeto!\n");

        System.out.print("Digite o título Projeto: ");
        String titulo = reader.nextLine();

        Projeto projeto = laboratorio.procurarProjeto(titulo);

        if (projeto != null) {
            return projeto;
        } else {
            System.out.println("\nNão existe um projeto com este título!");

            return null;
        }
    }

    public void printProjetosByStatus(Colaborador colaborador, String status) {
        int counter = 0;

        ArrayList<Projeto> projetos = colaborador.getProjetos();

        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getStatus().equals(status)) {
                counter++;
            }
        }

        if (counter > 0) {
            System.out.println(status + ":");

            for (int i = 0; i < projetos.size(); i++) {
                if (projetos.get(i).getStatus().equals(status)) {
                    System.out.println("\n" + projetos.get(i));
                }
            }

            System.out.println("");
        } else {
            System.out.println("Sem projetos com o status " + status + "!");
        }
    }

    public void printPublicacoes(Colaborador colaborador) {
        ArrayList<Publicacao> publicacoes = colaborador.getPublicacoes();

        if (!publicacoes.isEmpty()) {
            for (int i = 0; i < publicacoes.size(); i++) {
                System.out.println("\n" + publicacoes.get(i));
            }
        } else {
            System.out.println("Sem Publicações!");
        }
    }

    public void mostrarInformacoesColaborador(Colaborador colaborador) {

        System.out.println("\nNome: " + colaborador.getNome());
        System.out.println("E-mail: " + colaborador.getEmail());
        System.out.println("Projetos: ");
        this.printProjetosByStatus(colaborador, "Em andamento");
        this.printProjetosByStatus(colaborador, "Concluído");
        System.out.println("Produções Acadêmicas: ");
        this.printPublicacoes(colaborador);
    }

    public void mostrarInformacoesProjeto(Projeto projeto) {
        System.out.print("\n" + projeto);

        ArrayList<Publicacao> publicacoes = projeto.getPublicacoes();

        if (!publicacoes.isEmpty()) {
            System.out.println("\n\nProdução Acadêmica:\n");

            for (int i = 0; i < publicacoes.size(); i++) {
                System.out.println(publicacoes.get(i));
            }
        }

        System.out.println("");
    }

    public String[] splitDate(String date) {
        return date.split("/");
    }

    public Projeto criarProjeto(Scanner reader, Laboratorio laboratorio) {
        System.out.println("Criação de Projeto!\n");

        System.out.print("Digite o título do Projeto: ");
        String titulo = reader.nextLine();

        if (laboratorio.procurarProjeto(titulo) == null) {
            System.out.print("Digite a Data de Ínicio do Projeto (Formato: dd/MM/yyyy): ");
            String[] dataInicioString = this.splitDate(reader.nextLine());
            LocalDate dataInicio = LocalDate.of(Integer.parseInt(dataInicioString[2]), Integer.parseInt(dataInicioString[1]), Integer.parseInt(dataInicioString[0]));

            System.out.print("Digite a Data de Termino do Projeto (Formato: dd/MM/yyyy): ");
            String[] dataTerminoString = this.splitDate(reader.nextLine());
            LocalDate dataTermino = LocalDate.of(Integer.parseInt(dataTerminoString[2]), Integer.parseInt(dataTerminoString[1]), Integer.parseInt(dataTerminoString[0]));

            System.out.print("Digite o nome da Agência Financiadora do Projeto: ");
            String agenciaFinanciadora = reader.nextLine();
            System.out.print("Digite o valor financiado do Projeto em R$: ");
            float valorFinanciado = Float.parseFloat(reader.nextLine());
            System.out.print("Digite o objetivo do Projeto: ");
            String objetivo = reader.nextLine();
            System.out.print("Digite a descrição do Projeto: ");
            String descricao = reader.nextLine();

            System.out.println("\n(Você deve alocar ao menos um professor ao novo projeto)");
            boolean professorCreate = true;

            Colaborador professor = null;

            while (professorCreate) {
                System.out.print("Informe o nome do professor: ");
                String nomeProfessor = reader.nextLine();

                Colaborador professorBusca = laboratorio.procurarColaborador(nomeProfessor);

                if (professorBusca != null) {
                    if (professorBusca instanceof Professor) {
                        System.out.print("\nJá existe um professor cadastrado com esse nome, deseja adicioná-lo? (Digite 'sim' ou 'não'): ");
                        String addOption = reader.nextLine();

                        if (addOption.equalsIgnoreCase("sim")) {
                            professor = professorBusca;

                            professorCreate = false;
                        } else {
                            System.out.println("");
                        }
                    } else {
                        System.out.print("\nJá existe um colaborador com este nome (informe outro nome)\n\n");
                    }
                } else {
                    System.out.print("Informe o e-mail do professor: ");
                    String emailProfessor = reader.nextLine();

                    professor = new Professor(nomeProfessor, emailProfessor);

                    laboratorio.addColaborador(professor);

                    professorCreate = false;
                }
            }

            Projeto novoProjeto = new Projeto(titulo, dataInicio, dataTermino, agenciaFinanciadora, valorFinanciado, objetivo, descricao, professor);

            professor.addProjeto(novoProjeto);

            System.out.println("\nProjeto Criado!");

            return novoProjeto;
        } else {
            System.out.println("\nJá existe um projeto com este título!");

            return null;
        }
    }

    public void alterarStatus(Scanner reader, Laboratorio laboratorio) {
        System.out.println("Alterador de status!\n");

        System.out.print("Digite o título Projeto: ");
        String titulo = reader.nextLine();

        Projeto projeto = laboratorio.procurarProjeto(titulo);

        if (projeto != null) {
            if (projeto.getStatus().equals("Em elaboração")) {
                System.out.print("\nVocê deseja alterar o status 'Em elaboração' para 'Em andamento'? (Digite 'sim' ou 'não'): ");
                String statusOption = reader.nextLine();

                if (statusOption.equalsIgnoreCase("sim")) {
                    if (projeto.alterarStatus()) {
                        System.out.println("\nStatus alterado com sucesso!");
                    } else {
                        System.out.println("\nStatus não alterado!\n\nOs seguintes 'alunos de gradução' estão em dois projetos com o status 'Em andamento': ");
                        ArrayList<Colaborador> colaboradores = projeto.getParticipantes();
                        ArrayList<Aluno> alunosRemover = new ArrayList<Aluno>();

                        for (int i = 0; i < colaboradores.size(); i++) {
                            if (colaboradores.get(i) instanceof Aluno) {
                                Aluno aluno = (Aluno)colaboradores.get(i);
            
                                if (aluno.getTipo().equals("Aluno de Graduação")) {
                                    if (aluno.getProjetosByStatus("Em andamento").size() == 2) {
                                        System.out.println((i + 1) + "º: Nome: " + aluno.getNome() + ", E-mail: " + aluno.getEmail());
                                        alunosRemover.add(aluno);
                                    }
                                }
                            }
                        }

                        System.out.print("\nDeseja remover estes alunos do projeto? (Digite 'sim' ou 'não'): ");
                        String deleteOption = reader.nextLine();

                        if (deleteOption.equalsIgnoreCase("sim")) {
                            for (int i = 0; i < alunosRemover.size(); i++) {
                                alunosRemover.get(i).removeProjeto(projeto);
                                projeto.removeParticipante(alunosRemover.get(i));
                            }

                            System.out.println("\nTente alterar novamente!");
                        }
                    }
                }
            } else if (projeto.getStatus().equals("Em andamento")) {
                System.out.print("\nVocê deseja alterar o status 'Em andamento' para 'Concluído'? (Digite 'sim' ou 'não'): ");
                String statusOption = reader.nextLine();

                if (statusOption.equalsIgnoreCase("sim")) {
                    if (projeto.alterarStatus()) {
                        System.out.println("\nStatus alterado com sucesso!");
                    } else {
                        System.out.println("\nStatus não alterado! (Não existem publicações associadas ao projeto)");
                    }
                }
            } else {
                System.out.println("\nO projeto já foi concluído!");
            }
        } else {
            System.out.println("\nNão existe um projeto com este título!");
        }
    }

    public void addColaborador(Projeto projeto, Colaborador colaborador) {
        projeto.addParticipante(colaborador);
        colaborador.addProjeto(projeto);

        System.out.println("\nColaborador alocado!");
    }

    public void alocarColaborador(Scanner reader, Laboratorio laboratorio) {
        System.out.println("Alocação de Colaborador!\n");

        System.out.print("Digite o título do projeto ao qual deseja alocar um Colaborador: ");
        String titulo = reader.nextLine();

        Projeto projeto = laboratorio.procurarProjeto(titulo);

        if (projeto != null) {
            if (projeto.getStatus().equals("Em elaboração")) {
                System.out.print("\nDigite o nome do Colaborador: ");
                String nome = reader.nextLine();

                Colaborador colaborador = laboratorio.procurarColaborador(nome);

                if (colaborador != null) {
                    addColaborador(projeto, colaborador);
                } else {
                    System.out.println("\nNão existe um colaborador com este nome!");
                }
            } else {
                System.out.println("\nO projeto não está 'Em elaboração' (A alocação deve ser permitida apenas quando o projeto estiver 'Em elaboração')");
            }
        } else {
            System.out.println("\nNão existe um projeto com este título!");
        }
    }

    public void addPublicacao(Scanner reader, Laboratorio laboratorio) {
        if (laboratorio.getColaboradores().size() > 0) {
            Projeto projeto = null;

            System.out.println("Adicionar uma Publicação!\n");

            System.out.print("Digite o título da Publicação: ");
            String titulo = reader.nextLine();
            System.out.print("Digite o nome da conferência onde foi Publicada: ");
            String conferencia = reader.nextLine();
            System.out.print("Digite o ano de Publicação (Formato: yyyy): ");
            int ano = Integer.parseInt(reader.nextLine());
            System.out.print("A publicação tem algum projeto associado? (Digite 'sim' ou 'não'): ");
            String optProjeto = reader.nextLine();

            if (optProjeto.equalsIgnoreCase("sim")) {
                boolean loop = true;

                while (loop) {
                    System.out.print("Digite o título do Projeto (Para cancelar digite 'sair'): ");
                    String projetoTitulo = reader.nextLine();

                    if (!projetoTitulo.equalsIgnoreCase("sair")) {
                        projeto = laboratorio.procurarProjeto(projetoTitulo);

                        if (projeto != null) {
                            if (projeto.getStatus().equals("Em andamento")) {
                                loop = false;
                                
                                System.out.println("\nProjeto associado!");
                            } else {
                                System.out.println("\nO Projeto não está 'Em andamento'!\n");
                            }
                        } else {
                            System.out.println("\nProjeto não encontrado!\n");
                        }
                    } else {
                        loop = false;

                        projeto = null;
                    }
                }
            }

            System.out.println("\nSeleção dos autores da Publicação: ");
            System.out.println("\nColaboradores:");

            ArrayList<Colaborador> colaboradores = laboratorio.getColaboradores();

            for (int i = 0; i < colaboradores.size(); i++) {
                System.out.println("    (" + (i + 1) + ") - Nome: " + colaboradores.get(i).getNome() + ", E-mail: " + colaboradores.get(i).getEmail());
            }

            System.err.print("\nDigite o número de no mínimo um colaborador para associá-lo a Publicação (Formato: 1, 2, 3, ...): ");
            String list = reader.nextLine();

            String[] addColaboradores = list.replaceAll("\\s+","").split(",");

            ArrayList<Colaborador> autores = new ArrayList<Colaborador>();

            for (int i = 0; i < addColaboradores.length; i++) {
                if (addColaboradores[i].matches("\\d+")) {
                    Colaborador colaborador = colaboradores.get(Integer.parseInt(addColaboradores[i]) - 1);

                    if (colaborador != null) {
                        autores.add(colaborador);
                    }
                }
            }

            Publicacao novaPublicacao;
            
            if (autores.size() > 0) {
                if (projeto == null) {
                    novaPublicacao = new Publicacao(titulo, conferencia, ano, autores);

                    for (int i = 0; i < autores.size(); i++) {
                        autores.get(i).addPublicacao(novaPublicacao);
                    }
                } else {
                    novaPublicacao = new Publicacao(titulo, conferencia, ano, autores, projeto);

                    for (int i = 0; i < autores.size(); i++) {
                        autores.get(i).addPublicacao(novaPublicacao);
                    }

                    projeto.addPublicacao(novaPublicacao);
                }

                System.out.println("\nPublicação Adicionada!");
            } else {
                System.out.println("\nPublicação não adicionada! (Sem autores)");
            }
        } else {
            System.out.println("Adicione no mínimo um colaborador para adicionar uma Publicação!");
        }
    }

    public void adicionarOrientacao(Scanner reader, Laboratorio laboratorio) {
        System.out.println("Adicionar uma Orientação!\n");

        System.out.print("Digite o nome do Orientador: ");
        String nomeOrientador = reader.nextLine();

        Colaborador orientador = laboratorio.procurarColaborador(nomeOrientador);

        if (orientador != null) {
            if (orientador instanceof Professor) {
                Professor professor = (Professor)orientador;
                
                System.out.print("Digite o nome do colaborador que vai ser orientado pelo professor " + professor.getNome() + ": ");
                String nomeOrientando = reader.nextLine();

                Colaborador orientando = laboratorio.procurarColaborador(nomeOrientando);

                if (orientando != null) {
                    if (orientando instanceof Aluno) {
                        Aluno aluno = (Aluno)orientando;

                        aluno.setOrientador(professor);
                        professor.addOrientandos(aluno);

                        System.out.println("\nOrientação Adicionada!");
                    } else {
                        System.out.println("\nO orientando deve ser um Aluno!");
                    }
                } else {
                    System.out.println("\nNão foi encontrado nenhum colaborador com este nome!");
                }
            } else {
                System.out.println("\nO colaborador não é um professor!");
            }
        } else {
            System.out.println("\nNão foi encontrado nenhum colaborador com este nome!");
        }
    }

    public int getProjetosByStatus(String status, Laboratorio laboratorio) {
        int counter = 0;

        ArrayList<Projeto> projetos = laboratorio.getProjetos();

        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getStatus().equals(status)) {
                counter++;
            }
        }

        return counter;
    }

    public ArrayList<Publicacao> getAllPublicacoes(Laboratorio laboratorio) {
        ArrayList<Colaborador> colaboradores = laboratorio.getColaboradores();
        ArrayList<Publicacao> publicacoesResultado = new ArrayList<Publicacao>();

        for (int i = 0; i < colaboradores.size(); i++) {
            ArrayList<Publicacao> publicacoesColaborador = colaboradores.get(i).getPublicacoes();

            for (int j = 0; j < publicacoesColaborador.size(); j++) {
                if (!publicacoesResultado.contains(publicacoesColaborador.get(j))) {
                    publicacoesResultado.add(publicacoesColaborador.get(j));
                }
            }
        }

        return publicacoesResultado;
    }

    public int getTotalOrientacoes(Laboratorio laboratorio) {
        int counter = 0;

        ArrayList<Colaborador> colaboradores = laboratorio.getColaboradores();

        for (int i = 0; i < colaboradores.size(); i++) {
            if (colaboradores.get(i) instanceof Professor) {
                Professor professor = (Professor)colaboradores.get(i);

                counter += professor.getOrientandos().size();
            }
        }

        return counter;
    }

    public void mostrarLaboratorio(Laboratorio laboratorio) {
        System.out.println("Relatório de produção acadêmica do Laboratório:\n");

        System.out.println("Número de colaboradores: " + laboratorio.getColaboradores().size());
        System.out.println("Número de projeto em elaboração: " + this.getProjetosByStatus("Em elaboração", laboratorio));
        System.out.println("Número de projeto em andamento: " + this.getProjetosByStatus("Em andamento", laboratorio));
        System.out.println("Número de projeto concluídos: " + this.getProjetosByStatus("Concluído", laboratorio));
        System.out.println("Número total de projeto: " + laboratorio.getProjetos().size());
        System.out.println("Número total de Publicações: " + this.getAllPublicacoes(laboratorio).size());
        System.out.println("Número total de Orientação: " + this.getTotalOrientacoes(laboratorio));
        System.out.println("Número total de Produções : " + (this.getAllPublicacoes(laboratorio).size() + this.getTotalOrientacoes(laboratorio)));
    }

    public void mostrarColaboradores(Laboratorio laboratorio) {
        System.out.println("Lista de Colaboradores:\n");

        ArrayList<Colaborador> colaboradores = laboratorio.getColaboradores();

        if (!colaboradores.isEmpty()) {
            for (int i = 0; i < colaboradores.size(); i++) {
                Colaborador colaborador = colaboradores.get(i);

                String tipo = "Colaborador";

                if (colaborador instanceof Aluno) {
                    Aluno aluno = (Aluno)colaborador;

                    tipo = aluno.getTipo();
                } else if (colaborador instanceof Pesquisador) {
                    tipo = "Pesquisador";
                } else if (colaborador instanceof Professor) {
                    tipo = "Professor";
                }
                
                System.out.println((i + 1) + ": Nome: " + colaborador.getNome() + ", E-mail: " + colaborador.getEmail() + ", Tipo de colaborador: " + tipo);
            }
        } else {
            System.out.println("Sem colaboradores!");
        }
    }
}
