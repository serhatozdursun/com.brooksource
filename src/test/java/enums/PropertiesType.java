package enums;

public enum PropertiesType {

    BASE_URL("base.url"),
    WAIT_TIME("wait.time"),
    BROWSER("browser");
    public final String propertiesTypeText;

    PropertiesType(String value) {
        this.propertiesTypeText = value;
    }
    public String getText() {
        return propertiesTypeText;
    }
}