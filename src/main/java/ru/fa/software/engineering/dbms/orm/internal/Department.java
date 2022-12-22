package ru.fa.software.engineering.dbms.orm.internal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import ru.fa.software.engineering.dbms.orm.external.Employee;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "deleted_at IS NULL")
@Table(name = "departments")
public class Department extends InternalEntity<Long> {
    private String title;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
