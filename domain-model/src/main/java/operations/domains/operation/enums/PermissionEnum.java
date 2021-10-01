package operations.domains.operation.enums;

public enum PermissionEnum {

    READ("READ"),
    WRITE("WRITE"),
    AUTHORIZE("AUTHORIZE");

    private String value;

    PermissionEnum(String value) {
        this.value = value;
    }
}
