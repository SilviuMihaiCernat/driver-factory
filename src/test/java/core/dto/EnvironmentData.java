package core.dto;

public class EnvironmentData {

    private String environment = "sit";
    private String browser = "chrome";
    private String operatingSystem = "Windows";
    private String operatingSystemVersion = "7";
    private boolean remoteRun = false;

    public String getEnvironment() {
        return environment;
    }

    public String getBrowser() {
        return browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    public boolean isRemoteRun() {
        return remoteRun;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }

    public void setRemoteRun(boolean remoteRun) {
        this.remoteRun = remoteRun;
    }
}
