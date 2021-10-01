package operations.domains.resource.entity;


import operations.domains.operation.enums.PermissionEnum;

import javax.persistence.*;

@Embeddable
public class ResourcePermission {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "permission", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PermissionEnum permission;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }





}
