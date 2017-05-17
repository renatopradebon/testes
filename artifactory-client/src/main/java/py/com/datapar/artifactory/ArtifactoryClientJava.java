package py.com.datapar.artifactory;

import org.jfrog.artifactory.client.Artifactory;

public class ArtifactoryClientJava {

    private Artifactory artifactory;

    private String fileName;

    private String pathLocalFile;

    private String pathRemoteFile;

    private String[] property;

    private String[] mandatoryProperty;

    public Artifactory getArtifactory() {
        return artifactory;
    }

    public void setArtifactory(Artifactory artifactory) {
        this.artifactory = artifactory;
    }

    public ArtifactoryClientJava artifactory(Artifactory artifactory) {
        this.artifactory = artifactory;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArtifactoryClientJava fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getPathLocalFile() {
        return pathLocalFile;
    }

    public void setPathLocalFile(String pathLocalFile) {
        this.pathLocalFile = pathLocalFile;
    }

    public ArtifactoryClientJava pathLocalFile(String pathLocalFile) {
        this.pathLocalFile = pathLocalFile;
        return this;
    }

    public String getPathRemoteFile() {
        return pathRemoteFile;
    }

    public void setPathRemoteFile(String pathRemoteFile) {
        this.pathRemoteFile = pathRemoteFile;
    }

    public ArtifactoryClientJava pathRemoteFile(String pathRemoteFile) {
        this.pathRemoteFile = pathRemoteFile;
        return this;
    }

    public String[] getProperty() {
        return property;
    }

    public void setProperty(String[] property) {
        this.property = property;
    }

    public ArtifactoryClientJava property(String[] property) {
        this.property = property;
        return this;
    }

    public String[] getMandatoryProperty() {
        return mandatoryProperty;
    }

    public void setMandatoryProperty(String[] mandatoryProperty) {
        this.mandatoryProperty = mandatoryProperty;
    }

    public ArtifactoryClientJava mandatoryProperty(String[] mandatoryProperty) {
        this.mandatoryProperty = mandatoryProperty;
        return this;
    }
}