package py.com.datapar.maestro.domain.cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;
import py.com.datapar.maestro.domain.produto.model.Produto;
import py.com.datapar.maestro.lib.helper.XList;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Cliente extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = true)
    private long codigo;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = true)
    @NotNull(message = "El nombre es obligatorio!")
    @Size(max = 255)
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Empresa.class, orphanRemoval = true, mappedBy = "cliente")
    private List<Empresa> empresas;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Servidor.class, orphanRemoval = true, mappedBy = "cliente")
    private List<Servidor> servidores;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Produto.class, mappedBy = "clientes")
    private List<Produto> produtos;


    public long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Cliente codigo(long codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente nome(String nome) {
        this.nome = nome;
        return this;
    }

    public List<Empresa> getEmpresas(){
        return this.empresas = XList.createIfNull(this.empresas);
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Cliente empresas(List<Empresa> empresas) {
        this.empresas = empresas;
        return this;
    }

    public List<Servidor> getServidores() {
        return this.servidores = XList.createIfNull(this.servidores);
    }

    public void setServidores(List<Servidor> servidores) {
        this.servidores = servidores;
    }

    public Cliente servidores(List<Servidor> servidores) {
        this.servidores = servidores;
        return this;
    }

    public List<Produto> getProdutos() {
        return this.produtos = XList.createIfNull(this.produtos);
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente produtos(List<Produto> produtos) {
        this.produtos = produtos;
        return this;
    }

}
