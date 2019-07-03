/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import factory.FactoryMd5;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author felli
 */
public class Usuario{
    
    private Integer id;
    private String nome;
    private String login;
    private String senha;
    private List<Permissao> permissoes = new ArrayList<>();

    public Usuario() {
    }
    
    public Usuario(Integer id, String nome, String login, String senha, List<Permissao> permissoes) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.setSenhaMd5(senha);
        this.permissoes = permissoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void setSenhaMd5(String senha) {
        this.senha = FactoryMd5.criptografar(senha);
    }
    
    public void setPemissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }
    
    public void addPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
    }

    public void setPermissoes(Permissao permissao) {
        this.permissoes.remove(permissao);
    }
    
    public void removerPermissao(Permissao permissao) {
        this.permissoes.remove(permissao);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        return true;
    }
    
    
}
