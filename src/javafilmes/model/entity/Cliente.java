/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafilmes.model.entity;

/**
 *
 * @author Jefferson
 */
public class Cliente {
    
    private Integer id;
    private String nome;
    private String sobrenome;
    private String apelido;
    private String cpf;
    private boolean ehAdmin;
    
    public Cliente(String nome, String sobrenome, String apelido, String cpf, boolean ehAdmin) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.ehAdmin = ehAdmin;
    }
    
    public Cliente() {
        
    }

    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sobrenome
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * @param sobrenome the sobrenome to set
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * @return the apelido
     */
    public String getApelido() {
        return apelido;
    }

    /**
     * @param apelido the apelido to set
     */
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente(" + nome + "\t" + sobrenome;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the ehAdmin
     */
    public boolean isEhAdmin() {
        return ehAdmin;
    }

    /**
     * @param ehAdmin the ehAdmin to set
     */
    public void setEhAdmin(boolean ehAdmin) {
        this.ehAdmin = ehAdmin;
    }
    
}
