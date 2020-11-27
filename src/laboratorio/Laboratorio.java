package laboratorio;

import java.util.ArrayList;

import projeto.*;
import usuario.colaborador.*;
import usuario.*;

public class Laboratorio {
    private String nome;
    private ArrayList<Colaborador> colaboradores;
    private ArrayList<Projeto> projetos;
    private Administrador administrador;

    public Laboratorio(String nome, Administrador administrador) {
        this.nome = nome;
        this.administrador = administrador;
        this.colaboradores = new ArrayList<Colaborador>();
        this.projetos = new ArrayList<Projeto>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(ArrayList<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public void addColaborador(Colaborador novoColaborador) {
        this.colaboradores.add(novoColaborador);
    }

    public void removeColaborador(Colaborador colaborador) {
        this.colaboradores.remove(colaborador);
    }

    public Colaborador procurarColaborador(String nome) {
        Colaborador colaborador;

        for (int i = 0; i < this.colaboradores.size(); i++) {
            colaborador = this.colaboradores.get(i);

            if (colaborador.getNome().equals(nome)) {
                return colaborador;
            }
        }

        return null;
    }

    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(ArrayList<Projeto> projetos) {
        this.projetos = projetos;
    }

    public void addProjeto(Projeto novoProjeto) {
        this.projetos.add(novoProjeto);
    }

    public void removeProjeto(Projeto projeto) {
        this.projetos.remove(projeto);
    }

    public Projeto procurarProjeto(String titulo) {
        Projeto projeto;

        for (int i = 0; i < this.projetos.size(); i++) {
            projeto = this.projetos.get(i);

            if (projeto.getTitulo().equals(titulo)) {
                return projeto;
            }
        }

        return null;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
