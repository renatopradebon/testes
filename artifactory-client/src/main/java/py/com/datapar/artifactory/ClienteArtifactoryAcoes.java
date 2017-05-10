package py.com.datapar.artifactory;


import java.io.File;

import static py.com.datapar.artifactory.Constants.PATH_ARQUIVOS;
import static py.com.datapar.artifactory.Constants.REPOSITORY_NOME;

public class ClienteArtifactoryAcoes {

    ArtifactoryClientJava artifactoryClientJava;

    public ArtifactoryClientJava getArtifactoryClientJava() {
        return artifactoryClientJava;
    }

    public void setArtifactoryClientJava(ArtifactoryClientJava artifactoryClientJava) {
        this.artifactoryClientJava = artifactoryClientJava;
    }

    public ClienteArtifactoryAcoes clienteArtifactoryJava(ArtifactoryClientJava artifactoryClientJava) {
        this.artifactoryClientJava = artifactoryClientJava;
        return this;
    }

    public void realizaUpload() {
        String fileWithPathLocal = artifactoryClientJava.getPathLocalFile() + "\\" +
                artifactoryClientJava.getFileName();

        String fileWithPathRemote = artifactoryClientJava.getPathRemoteFile() + "/" +
                artifactoryClientJava.getFileName();

        File arquivo = new File(PATH_ARQUIVOS + "\\" + fileWithPathLocal);

        org.jfrog.artifactory.client.model.File result = artifactoryClientJava
                .getArtifactory()
                .repository(REPOSITORY_NOME)
                .upload(fileWithPathRemote, arquivo)
                .withProperty(artifactoryClientJava.getProperty()[0], artifactoryClientJava.getProperty()[1])
                .doUpload();
    }


}
