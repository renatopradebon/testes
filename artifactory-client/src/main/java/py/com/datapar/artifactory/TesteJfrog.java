package py.com.datapar.artifactory;


import org.jfrog.artifactory.client.ArtifactoryClient;

import java.io.IOException;

import static py.com.datapar.artifactory.Constants.*;

public class TesteJfrog {

    public static void main(String[] args) throws IOException {

        ClienteArtifactoryAcoes clienteArtifactoryAcoesUpload = new ClienteArtifactoryAcoes().clienteArtifactoryJava(
                new ArtifactoryClientJava()
                        .artifactory(ArtifactoryClient.create(CLIENT_ARTIFACTORY_URL, CLIENT_ARTIFACTORY_USERNAME, CLIENT_ARTIFACTORY_PASSWORD))
                        .fileName("teste-jfrog-client.txt")
                        .pathLocalFile("teste-renato")
                        .pathRemoteFile("arquivos")
                        .property(new String[]{"teste", "alexandre"})
        );

//        clienteArtifactoryAcoesUpload.realizaUpload();

        ClienteArtifactoryAcoes clienteArtifactoryAcoesDownload = new ClienteArtifactoryAcoes().clienteArtifactoryJava(
                new ArtifactoryClientJava()
                        .artifactory(ArtifactoryClient.create(CLIENT_ARTIFACTORY_URL, CLIENT_ARTIFACTORY_USERNAME, CLIENT_ARTIFACTORY_PASSWORD))
                        .fileName("teste-jfrog-client.txt")
                        .pathLocalFile("teste-renato\\baixados")
                        .pathRemoteFile("arquivos")
                        .property(new String[]{"teste", "renato"})
        );

//        clienteArtifactoryAcoesDownload.realizaDownload();


        "hey duke".chars().forEach(c -> System.out.println((char)c));
        System.out.println("---------------------------");
        "hey duke".chars().parallel().forEach(c -> System.out.println((char) c));

        System.out.println("Done");
    }
}
