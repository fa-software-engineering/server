package ru.fa.engineering.server.dbms.entities.external;


import lombok.Getter;
import lombok.Setter;
import ru.fa.engineering.server.dbms.entities.internal.Department;
import ru.fa.engineering.server.dbms.entities.internal.EmployeeSkill;
import ru.fa.engineering.server.dbms.entities.internal.Resource;
import ru.fa.engineering.server.dbms.entities.internal.Task;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "keycloak_users")
public class KeycloakUser extends ExternalEntity<String> {

    @OneToMany(mappedBy = "employee")
    private List<Resource> projects;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeSkill> skills;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "author")
    private List<Task> createdTasks;

    @OneToMany(mappedBy = "executor")
    private List<Task> assignedTasks;
}
