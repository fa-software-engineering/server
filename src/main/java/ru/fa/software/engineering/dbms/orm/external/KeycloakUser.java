package ru.fa.software.engineering.dbms.orm.external;


import lombok.Getter;
import lombok.Setter;
import ru.fa.software.engineering.dbms.orm.internal.Department;
import ru.fa.software.engineering.dbms.orm.internal.EmployeeSkill;
import ru.fa.software.engineering.dbms.orm.internal.Resource;
import ru.fa.software.engineering.dbms.orm.internal.Task;

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
