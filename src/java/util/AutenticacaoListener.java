/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import bean.AutenticacaoBean;
import model.Permissao;
import model.Usuario;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author felli
 */

public class AutenticacaoListener implements PhaseListener {
    
    // Essa class tem que ser declarada no face-config.xml para ser executada    
    @Override
    public void afterPhase(PhaseEvent event) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext(); 
        
        String paginaAtual = ((HttpServletRequest)externalContext.getRequest()).getRequestURI();
            System.out.println(paginaAtual);
            
            boolean ehPaginaDeAutenticacao = false;
            ehPaginaDeAutenticacao = paginaAtual.contains("login.xhtml");

            if(!ehPaginaDeAutenticacao){
                    
                      //Verificar se o Objeto Usuario Esta no map da sessação
                    try {

                        Usuario usuario = (Usuario)externalContext.getSessionMap().get("usuarioLogado");
                        if(usuario == null){
                            externalContext.redirect("login.xhtml");
                            return;
                        }else{
                            if(paginaAtual.contains("404.html")){
                                return;
                            }
                            for(Permissao p : usuario.getPermissoes()){
                                if(paginaAtual.contains(p.getNome())){
                                    return;
                                }
                            }
                        }
                        externalContext.redirect("index.xhtml");
                        
                    } catch (IOException ex) {
                        Logger.getLogger(AutenticacaoListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }		
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
            return PhaseId.RESTORE_VIEW;
    }
    
}
