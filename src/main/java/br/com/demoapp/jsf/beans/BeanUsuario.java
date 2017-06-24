/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.demoapp.jsf.beans;

import br.com.demoapp.jsf.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mateusgobo
 */

@ManagedBean(name = "beanUsuario")
@ViewScoped
public class BeanUsuario implements Serializable{
    
    private List<Usuario> listUsuarios;    
    private Usuario usuario;
    private String novoNome;
    private String novoEmail;
    
    @PostConstruct
    public void initialize(){
        this.createDataList();                        
    }    

    private void createDataList(){
        int count = 1;
        while(count <= 10){
            Usuario user = new Usuario();
            user.setId(Long.valueOf(count));
            user.setNome("TESTE DE INCLUSAO "+count);
            user.setEmail("teste"+count+"@teste"+count+".com.br");
            count++;
            
            this.getListUsuarios().add(user);
        }        
    }
    
    public void checkUser(Usuario usuario){
        this.setUsuario(usuario);
    }
    
    public void searchDataList(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getRequestMap().entrySet().stream().forEach(param->{
            if(param.getKey().equals("usuario")){                
                this.setUsuario((Usuario)param.getValue());
            }            
        });        
    }
    
    public void criarNovoUsuario(){
        this.getUsuario().setNome(this.getNovoNome());
        this.getUsuario().setEmail(this.getNovoEmail());
        
        RequestContext req = RequestContext.getCurrentInstance();
        Collection<String> fields = new ArrayList<>();
        fields.add("formEditUsuarios:novoEmailPanel");
        fields.add("formMainUsuarios:dataTableUsuarios");
        fields.stream().forEach(f->{
            req.update(f);
        });
//        for(String id : fields){
//            req.update(id);
//        }        
    }
    
    public List<Usuario> getListUsuarios() {
        if(this.listUsuarios == null){
            this.listUsuarios = new ArrayList<>();
        }
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }    

    public Usuario getUsuario() {
        if(this.usuario == null){
            this.usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    

    public String getNovoEmail() {
        if(this.novoEmail == null){
            this.novoEmail = "";
        }
        return novoEmail;
    }

    public void setNovoEmail(String novoEmail) {
        this.novoEmail = novoEmail;
    }    

    public String getNovoNome() {
        if(this.novoNome == null){
            this.novoNome = "";
        }
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }    
}
