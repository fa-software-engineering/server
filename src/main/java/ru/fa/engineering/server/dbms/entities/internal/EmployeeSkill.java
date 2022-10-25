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
@Table(name = "employee_skills")
public class EmployeeSkill extends
        AbstractSuperEntity<EmployeeSkill.EmployeeSkillPK> {

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeSkillPK implements Serializable {
        @Column(name = "employee_id")
        private String employeeId;
        @Column(name = "skill_id")
        private long skillId;
    }

    @EmbeddedId
    private EmployeeSkillPK id;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private KeycloakUser employee;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Skill skill;
}
