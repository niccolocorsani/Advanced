package operations.domains.user.entity;

import operations.domains.useroperation.entity.UserOperation;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id ;

    @Column(name = "subject")
    private String subject = "subject da impostare";

    @Column(name = "username")
    private String username = "username da impostare";

    @Column(name = "email")
    private String email = "mail da impostare";

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", nullable = false), foreignKey = @ForeignKey(name = "FK_User_Roles"))
    @Column(name = "user_role")
    private List<String> roles = new ArrayList<String>();

    @Column(name = "external_id")
    private String externalId = "ext da impostare";

    @Column(name = "name")
    private String Name = "nome da impostare";
/*
    @Column(name = "active")
    private Boolean active;*/

    @Column(name = "surname")
    private String surname = "surname da impostare";
 //// Con i vari test fatti risulta che se faccio l'assocaizione completamente unidirezionale e con mappedBy, l'entity-relationship che esce fuori è uguale.(Con una tabella in più)
//// Se invece metto mappedBy, avrò una foreignKey sul lato relativo alla colonna di MappedBy....In questo caso user
//// Il fatto della bidirezionalità si riferisce a Java, nel senso che essendo la relazione dichiarata da una sola parte, dall'altra parte non c è il metodo get....

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOperation> userOperation = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public List<UserOperation> getUserOperation() {
        return userOperation;
    }

    public void setUserOperation(List<UserOperation> userOperation) {
        this.userOperation = userOperation;
    }
/*
    public void setActive(boolean active){this.active = active;}

    //// di default sui boolean il metodo creato da Intellij IDE è isActive, non getActive necessario a JPA per funzionare
   ///// Inoltre il tipo boolean non va bene, va messo Boolean   https://stackoverflow.com/questions/13013227/cannot-set-boolean-to-null
    public boolean getActive() {
        return this.active;
    }

    public boolean isActive() {
        return this.active;
    }*/
}
