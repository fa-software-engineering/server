package ru.fa.engineering.server.dbms.entities.external;


import lombok.Getter;
import lombok.Setter;
import ru.fa.engineering.server.dbms.entities.internal.EmployeeProject;
import ru.fa.engineering.server.dbms.entities.internal.EmployeeSkill;
import ru.fa.engineering.server.dbms.entities.internal.Project;
import ru.fa.engineering.server.dbms.entities.internal.Skill;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "keycloak_users")
public class KeycloakUser extends ExternalEntity<String> {

    @OneToMany(mappedBy = "employee")
    private List<EmployeeProject> projects;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeSkill> skills;
}
