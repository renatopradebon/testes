package py.com.datapar.artifactory;


import org.jfrog.artifactory.client.ArtifactoryClient;

import java.io.*;

import static py.com.datapar.artifactory.Constants.*;

public class TesteJfrog {

    public static void main(String[] args) throws IOException {

        ClienteArtifactoryAcoes clienteArtifactoryAcoes = new ClienteArtifactoryAcoes().clienteArtifactoryJava(
                new ArtifactoryClientJava()
                        .artifactory(ArtifactoryClient.create(URL_ARTIFACTORY, USUARIO_ARTIFACTORY, SENHA_ARTIFACTORY))
                        .fileName("teste-jfrog-client.txt")
                        .pathLocalFile("teste-renato")
                        .pathRemoteFile("arquivos")
                        .property(new String[]{"teste", "renato"}));


        clienteArtifactoryAcoes.realizaUpload();
//        dowloadFile(artifactory);


        System.out.println("Done");
    }


    public static void dowloadFile(ArtifactoryClientJava artifactory) throws IOException {

        InputStream iStream = artifactory.getArtifactory().repository(REPOSITORY_NOME)
                .download("teste-jfrog-client.txt")
                .withProperty("teste", "joao")
                .doDownload();

        OutputStream outputStream =
                new FileOutputStream(new File(PATH_ARQUIVOS + "\\teste-jfrog-client-download.txt"));

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = iStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

    }
}
