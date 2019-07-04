/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioDAO;
import model.Permissao;
import model.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author felli
 */

@ManagedBean(name = "beanAutenticacao")
@SessionScoped
public class AutenticacaoBean{
    
    private Boolean novo = true;

    private Usuario usuario;
    private Usuario usuarioLogado;
    
    private String busca = "";
    
    List<Permissao> list = new ArrayList<>();
    List<Usuario> listUsuario = new ArrayList<>();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        System.out.println(busca);
        this.busca = busca;
    }

    public List<Permissao> getList() {
        return list;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    @PostConstruct
    public void iniciar() {
        usuario = new Usuario();
    }

    public void autenticar() {
        usuario.setSenhaMd5(usuario.getSenha());
        try {
            usuarioLogado = UsuarioDAO.autenticar(usuario.getLogin(), usuario.getSenha());

            if(usuarioLogado == null){
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("inputEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR,"acesso negado","login e/ou senha incorretos"));
                    return;
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");

        }  catch (ClassNotFoundException ex) {
            Logger.getLogger(AutenticacaoBean.class.getName()).log(Level.SEVERE, null, ex);
            //Messages.addGlobalError(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(AutenticacaoBean.class.getName()).log(Level.SEVERE, null, ex);
            //Messages.addGlobalError(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(AutenticacaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Logoff() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AutenticacaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvarUsuario(){
        if(novo){
            usuario.setSenhaMd5(usuario.getSenha());
            if(!UsuarioDAO.insert(usuario)){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Não foi possivel Criar"));
            }else{
                usuario = new Usuario();
            }
        }else{
            if(usuario.getSenha() != null){
                usuario.setSenhaMd5(usuario.getSenha());
            }
            if(!UsuarioDAO.update(usuario)){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Não foi possivel Editar"));
            }else{
                usuario = new Usuario();
            }
        }
        this.novo = true;
    }
    
    public void excluirUsuario(Usuario usuario){
        if(UsuarioDAO.delete(usuario)){
            listUsuario.remove(usuario);
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Não foi possivel Excuir"));
        }
    }
    
    public void editarUsuario(Usuario usuario){
        this.usuario = usuario;
        this.novo = false;
    }
    
    public boolean testefalse(){
        return false;
    }

    public boolean temPermissoes(String nome){
        if(usuarioLogado != null){
            for(Permissao permissao : usuarioLogado.getPermissoes()){
                if(nome.equals(permissao.getNome())){
                    return true;
                }
            }
            return false;
        }else{
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(AutenticacaoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }
    
    public void pesquisaPermissao(){
        System.out.println("1 --> "+busca);
        if(busca.length()>2){
            try {
                list = UsuarioDAO.buscarPermissoes(busca);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AutenticacaoBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AutenticacaoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        busca = "";
    }
    
    public void pesquisarUsuario(){
        System.out.println("2 --> "+busca);
        if(busca.length()>2){
            listUsuario = UsuarioDAO.pesquisarUsuario(busca);
        }
    }
    
    public boolean varificarAutorizacao(String nome){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Usuario usuario = (Usuario)externalContext.getSessionMap().get("usuarioLogado");
        boolean contem = false;
        for(Permissao p : usuario.getPermissoes()){
            if(p.getNome().equals(nome)){
                contem = true;
            }
        }
        return contem;
    }
    
}
