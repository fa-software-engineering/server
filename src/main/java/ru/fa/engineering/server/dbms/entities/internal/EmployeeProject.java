package ru.fa.engineering.server.dbms.entities.internal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import ru.fa.engineering.server.dbms.entities.AbstractSuperEntity;
import ru.fa.engineering.server.dbms.entities.external.KeycloakUser;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at IS NULL")
@Table(name = "employee_projects")
public class EmployeeProject
        extends AbstractSuperEntity<EmployeeProject.EmployeeProjectPK> {

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeProjectPK implements Serializable {
        @Column(name = "employee_id")
        private String employeeId;
        @Column(name = "project_id")
        private long projectId;
    }

    @EmbeddedId
    private EmployeeProjectPK id;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private KeycloakUser employee;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Project project;

}
