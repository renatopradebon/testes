package py.com.datapar.maestro.domain.produto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import py.com.datapar.maestro.domain.commons.model.EntityWithIdentity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Artefato extends EntityWithIdentity {

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String nome;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String extensao;


    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String fileUri;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String versao;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Basic(optional = false)
    private String sha1;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "artefatos")
    private List<ProdutoAppVersao> versoes;

    public String getNome() {
        return nome;
    }

    public Artefato setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getExtensao() {
        return extensao;
    }

    public Artefato setExtensao(String extensao) {
        this.extensao = extensao;
        return this;
    }

    public String getFileUri() {
        return fileUri;
    }

    public Artefato setFileUri(String fileUri) {
        this.fileUri = fileUri;
        return this;
    }

    public String getVersao() {
        return versao;
    }

    public Artefato setVersao(String versao) {
        this.versao = versao;
        return this;
    }

    public List<ProdutoAppVersao> getVersoes() {
        return versoes;
    }

    public Artefato setVersoes(List<ProdutoAppVersao> versoes) {
        this.versoes = versoes;
        return this;
    }

    public String getSha1() {
        return sha1;
    }

    public Artefato setSha1(String sha1) {
        this.sha1 = sha1;
        return this;
    }

    @Override
    public String toString() {
        //datapar/dolphin-desktop/bootstrap/BplScanDependence.exe
        //dolphin-desktop/bootstrap/bootstrap-1.0.exe
        return fileUri;
    }
}
