package ru.fa.software.engineering.dbms.repositories.external;

import ru.fa.software.engineering.dbms.orm.external.Employee;
import ru.fa.software.engineering.dbms.orm.internal.EmployeeSkill;
import ru.fa.software.engineering.dbms.orm.internal.Skill;
import ru.fa.software.engineering.dbms.repositories.AbstractRepository;
import ru.fa.software.engineering.dbms.repositories.internal.EmployeeSkillRepository;
import ru.fa.software.engineering.dbms.repositories.internal.SkillRepository;
import ru.fa.software.engineering.utils.KeycloakAdminClient;
import ru.fa.software.engineering.vars.KeycloakProps;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class EmployeeRepository extends AbstractRepository<Employee, String> {

    @Inject
    SkillRepository skillRepository;

    @Inject
    EmployeeSkillRepository employeeSkillRepository;

    @Inject
    KeycloakAdminClient keycloakAdminClient;

    @Inject
    KeycloakProps keycloakProps;

    public List<Skill> findSkillsByEmployeeId(String employeeId) {
        Employee employee = getOrCreateWithRemoteChecking(employeeId);

        if (employee != null) {
            return employee.getSkills()
                    .stream().map(EmployeeSkill::getSkill)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public boolean addSkillByEmployeeId(long skillId, String employeeId) {
        Employee employee = getOrCreateWithRemoteChecking(employeeId);
        if (employee == null) {
            return false;
        }
        Skill skill = skillRepository.getById(skillId);
        if (skill == null) {
            return false;
        }

        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setId(new EmployeeSkill.EmployeeSkillPK(employeeId, skillId));
        employeeSkill.persist();

        return true;
    }

    public boolean deleteSkillByEmployeeId(String employeeId, long skillId) {
        return employeeSkillRepository.deleteSoftById(new EmployeeSkill.EmployeeSkillPK(employeeId, skillId));
    }

    public Employee getOrCreateWithRemoteChecking(String id) {
        return getOrCreate(id, true);
    }

    public Employee getOrCreate(String id, boolean needCheck) {
        Employee employee = findById(id);

        if (employee != null) {
            return employee;
        } else {
            if (needCheck) {
                checkRemoteExistKeycloakUser(id);
            }

            employee = new Employee();
            employee.setId(id);
            create(employee);
        }
        return employee;
    }

    private void checkRemoteExistKeycloakUser(String id) {
        keycloakAdminClient
                .getKeycloak()
                .realm(keycloakProps.realm())
                .users()
                .get(id)
                .getConsents();
    }

    public boolean existsById(String id) {
        try {
            checkRemoteExistKeycloakUser(id);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }
}
