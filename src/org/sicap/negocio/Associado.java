/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sicap.negocio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "associados")
@Inheritance(strategy = InheritanceType.JOINED)
public class Associado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Associado")
    private long idAssociado;
    @Column(name = "nome", nullable = false, length = 255, insertable = true)
    private String nome;
    @Column(name = "sexo", nullable = false, length = 255)
    private String sexo;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_Nascimento", nullable = false, length = 15)
    private Date dataNascimento;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_Cadastro", nullable = false, length = 15)
    private Date dataCadastro;
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;
    @Column(name = "nit", nullable = false, length = 255, unique = true)
    private String NIT;
    @Column(name = "cie", nullable = false, length = 255, unique = true)
    private String CIE;
    @Column(name = "cpf", nullable = false, length = 255, unique = true)
    private String CPF;
    @Column(name = "rg", nullable = false, length = 255, unique = true)
    private String RG;
    @Column(name = "telefone_Celular", nullable = false, length = 255, unique = true)
    private String telefoneCelular;
    @Column(name = "telefone_Fixo", nullable = false, length = 255, unique = true)
    private String telefoneFixo;
    @Column(name = "estado", nullable = false, length = 255)
    private String estado;
    @Column(name = "cidade", nullable = false, length = 255)
    private String cidade;
    @Column(name = "endereco", nullable = false, length = 255)
    private String endereco;
    @Column(name = "numero", nullable = false, length = 255)
    private long numero;
    @Lob
    @Column(name = "foto")
    private byte[] foto;

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getFoto() {
        return foto;
    }
    
    
    

    @OneToOne
    @JoinColumn(name = "id_Associacao")
    private Associacao associacao;

    @OneToOne
    @JoinColumn(name = "id_Profissao")
    private Profissao profissao;

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getCIE() {
        return CIE;
    }

    public void setCIE(String CIE) {
        this.CIE = CIE;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Associacao getAssociacao() {
        return associacao;
    }

    public void setAssociacao(Associacao associacao) {
        this.associacao = associacao;
    }

    public long getIdAssociado() {
        return idAssociado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdAssociado(long idAssociado) {
        this.idAssociado = idAssociado;
    }

}
