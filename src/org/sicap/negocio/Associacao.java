/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.negocio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity

@Table(name = "Associacao")

@NamedQueries({
    @NamedQuery(name = "Associacao.todasAssociacoes", query = "SELECT a FROM Associacao a"),
    @NamedQuery(name = "Associacao.todasPorCNPJNome", query = "SELECT a FROM Associacao a WHERE a.associacao LIKE :filtro OR a.CNPJ LIKE :filtrarCNPJ"),
    @NamedQuery(name = "Associacao.consultaPorCnpj",query = "Select a From Associacao  a where a.CNPJ like :buscar")
})
public class Associacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Associacao", nullable = false)
    private long idAssociacao;
    @Column(name = "nome", nullable = false, unique = true)
    private String associacao;
    @Column(name = "endereco", nullable = false)
    private String endereco;
    @Column(name = "ordem_Utilidade", nullable = false)
    private String ordemUtilidade;
    @Column(name = "CNPJ",unique = true, nullable = false)
    private String CNPJ;
    @Column(name = "logo_Marca")
    private byte[] logo;
    @Column(name = "telefone_Celular", nullable = false)
    private String telefoneCelular;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "telefone_Fixo", nullable = false)
    private String fixo;
    @Column(name = "numero", nullable = false)
    private int numero;
    private String cidade;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "bairro", nullable = false)
    private String bairro;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getIdAssociacao() {
        return idAssociacao;
    }

    public void setIdAssociacao(long idAssociacao) {
        this.idAssociacao = idAssociacao;
    }

    public String getAssociacao() {
        return associacao;
    }

    public void setAssociacao(String associacao) {
        this.associacao = associacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getOrdemUtilidade() {
        return ordemUtilidade;
    }

    public void setOrdemUtilidade(String ordemUtilidade) {
        this.ordemUtilidade = ordemUtilidade;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFixo() {
        return fixo;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }

    @Override
    public String toString() {
        return associacao; //To change body of generated methods, choose Tools | Templates.
    }

    
}
