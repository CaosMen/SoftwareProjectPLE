package usuario.colaborador;

import publicacao.*;
import projeto.*;

import java.util.ArrayList;

public class Professor extends Colaborador {
    private ArrayList<Aluno> orientandos;

    public Professor(String nome, String email) {
        super(nome, email);
        this.orientandos = new ArrayList<Aluno>();
    }

    public Professor(String nome, String email, ArrayList<Publicacao> publicacoes, ArrayList<Projeto> projetos) {
        super(nome, email, publicacoes, projetos);
        this.orientandos = new ArrayList<Aluno>();
    }

    public ArrayList<Aluno> getOrientandos() {
        return orientandos;
    }

    public void setOrientandos(ArrayList<Aluno> orientandos) {
        this.orientandos = orientandos;
    }

    public void addOrientandos(Aluno orientando) {
        this.orientandos.add(orientando);
    }

    public void removeOrientandos(Aluno orientando) {
        this.orientandos.remove(orientando);
    }
}
