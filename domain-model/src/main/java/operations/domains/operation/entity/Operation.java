package operations.domains.operation.entity;



import operations.domains.resource.entity.ResourcePermission;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Operation  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name="description", nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "operation_roles", joinColumns = @JoinColumn(name = "operation_id", nullable = false), foreignKey = @ForeignKey(name = "FK_Operations_Roles"))
    @Column(name = "roles", nullable = false)
    private List<String> roles = new ArrayList<String>();


    @ElementCollection
    @CollectionTable(name="Resource_Permission")
    private List<ResourcePermission> resources = new ArrayList<>();

   /* @ElementCollection
    @CollectionTable(name = "operation_rosources", joinColumns = @JoinColumn(name = "operation_id", nullable = false), foreignKey = @ForeignKey(name = "FK_Operations_Resources"))
    @Column(name = "resources", nullable = false)
    private List<String> resources = new ArrayList<String>();

    @ManyToMany(mappedBy = "ResourcePermission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "resourcePermission")
    private List<ResourcePermission> resourcePermission = new ArrayList<>();*/




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
