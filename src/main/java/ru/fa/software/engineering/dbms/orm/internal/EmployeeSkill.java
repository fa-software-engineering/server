package ru.fa.software.engineering.dbms.orm.internal;

import lombok.*;
import org.hibernate.annotations.Where;
import ru.fa.software.engineering.dbms.orm.AbstractSuperEntity;
import ru.fa.software.engineering.dbms.orm.external.Employee;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at IS NULL")
@Table(name = "employee_skills")
public class EmployeeSkill extends
        AbstractSuperEntity<EmployeeSkill.EmployeeSkillPK> {

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class EmployeeSkillPK implements Serializable {
        @Column(name = "employee_id")
        @NonNull
        private String employeeId;
        @Column(name = "skill_id")
        private long skillId;
    }

    @EmbeddedId
    private EmployeeSkillPK id;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Skill skill;
}
