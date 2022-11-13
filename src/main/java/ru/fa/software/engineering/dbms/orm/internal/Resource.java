package ru.fa.software.engineering.dbms.orm.internal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import ru.fa.software.engineering.dbms.orm.AbstractSuperEntity;
import ru.fa.software.engineering.dbms.orm.external.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at IS NULL")
@Table(name = "resources")
public class Resource
        extends AbstractSuperEntity<Resource.EmployeeProjectPK> {

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

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    private String name;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Project project;

}
