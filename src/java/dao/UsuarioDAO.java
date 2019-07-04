/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Permissao;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felli
 */
public class UsuarioDAO {
    
    private static Usuario usuario = new Usuario(1, "Fellipe", "fmaia", "123", Permissao.getListPesmissoes(new String[]{"index.xhtml","blank.xhtml"}));
    
    public static boolean insert(Usuario usuarioNovo) {
        throw new UnsupportedOperationException("Função não desenvolvida"); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static boolean update(Usuario usuario){
        throw new UnsupportedOperationException("Função não desenvolvida"); 
    }
    
    public static Boolean delete(Usuario usuario){
        throw new UnsupportedOperationException("Função não desenvolvida");         
    }
    
    public static List pesquisarUsuario(String pesquisa){
        throw new UnsupportedOperationException("Função não desenvolvida"); 
    }
    
    public static boolean addPermissao(Integer idUsuario ,String permissao){
        throw new UnsupportedOperationException("Função não desenvolvida"); 
    }
    
    public static boolean removerPermissao(Integer idUsuario ,String permissao){
        throw new UnsupportedOperationException("Função não desenvolvida"); 
    }
    
    public static Usuario autenticar(String login ,String senha) throws ClassNotFoundException, SQLException{
        if (!Objects.equals(usuario.getLogin(), login)) {
            return null;
        }
        if (!Objects.equals(usuario.getSenha(), senha)) {
            return null;
        }
        return usuario;
    }
    
    public static List buscarPermissoes(String busca) throws ClassNotFoundException, SQLException{
        throw new UnsupportedOperationException("Função não desenvolvida");         
    }

}
