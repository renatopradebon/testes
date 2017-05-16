package py.com.datapar.artifactory;

import java.io.*;

import static py.com.datapar.artifactory.Constants.CLIENT_ARTIFACTORY_REPOSITORY;
import static py.com.datapar.artifactory.Constants.LOCAL_PATH_ARQUIVOS;

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

        File arquivo = new File(LOCAL_PATH_ARQUIVOS + "\\" + fileWithPathLocal);

        org.jfrog.artifactory.client.model.File result = artifactoryClientJava
                .getArtifactory()
                .repository(CLIENT_ARTIFACTORY_REPOSITORY)
                .upload(fileWithPathRemote, arquivo)
                .withProperty(artifactoryClientJava.getProperty()[0], artifactoryClientJava.getProperty()[1])
                .doUpload();

    }

    public void realizaDownload() throws IOException {

        String fileWithPathLocal = artifactoryClientJava.getPathLocalFile() + "\\" +
                artifactoryClientJava.getFileName();

        String fileWithPathRemote = artifactoryClientJava.getPathRemoteFile() + "/" +
                artifactoryClientJava.getFileName();

        String firstParameterMandatory = (artifactoryClientJava.getProperty()[0]) == "" ? null : artifactoryClientJava.getProperty()[0];
        String secondParameterMandatory = (artifactoryClientJava.getProperty()[0]) == "" ? null : artifactoryClientJava.getProperty()[1];

        InputStream iStream = artifactoryClientJava.getArtifactory()
                .repository(CLIENT_ARTIFACTORY_REPOSITORY)
                .download(fileWithPathRemote)
                .withProperty(
                        firstParameterMandatory,
                        secondParameterMandatory)
//                .withMandatoryProperty(
//                        (artifactoryClientJava.getProperty()[0].isEmpty()) ? null : artifactoryClientJava.getProperty()[0],
//                        (artifactoryClientJava.getProperty()[1].isEmpty()) ? null : artifactoryClientJava.getProperty()[1])
                // todo verificar uma forma de adicionar esses caras dinamicamente (Property e MandatoryProperty)
                .doDownload();

        OutputStream outputStream =
                new FileOutputStream(new File(LOCAL_PATH_ARQUIVOS + "\\" + fileWithPathLocal));

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = iStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
    }
}
