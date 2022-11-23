package ru.fa.software.engineering;

import ru.fa.software.engineering.dbms.orm.internal.EmployeeSkill;
import ru.fa.software.engineering.dbms.orm.internal.Skill;
import ru.fa.software.engineering.dbms.repositories.external.EmployeeRepository;
import ru.fa.software.engineering.dbms.services.external.EmployeeService;
import ru.fa.software.engineering.utils.KeycloakAdminClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class TestEnvironment {

    @Inject
    KeycloakAdminClient keycloakAdminClient;

    @Inject
    EmployeeRepository employeeRepository;

    public static class TestData {

        public static class Skills {
            static {
                initSkill1 = new Skill();
                initSkill2 = new Skill();
                initSkill3 = new Skill();
                init();
            }
            public static Skill initSkill1;
            public static Skill initSkill2;
            public static Skill initSkill3;

            @Transactional
            static void init() {
                initSkill1.setTitle("initSkill1");
                initSkill2.setTitle("initSkill2");
                initSkill3.setTitle("initSkill3");

                initSkill1.persist();
                initSkill2.persist();
                initSkill3.persist();
            }
        }

        public static class EmployeeSkills {
            public static EmployeeSkill initEmployeeSkill1 = new EmployeeSkill();
            public static EmployeeSkill initEmployeeSkill2 = new EmployeeSkill();
        }
    }


    public void initEmployeeSkills() {
        String adminId = keycloakAdminClient.getKeycloak()
                .realm("resource-accounting-system")
                .users()
                .search("admin")
                .get(0).getId();

        employeeRepository.getOrCreateWithRemoteChecking(adminId);


        TestData.EmployeeSkills.initEmployeeSkill1.setId(
                new EmployeeSkill.EmployeeSkillPK(adminId, TestData.Skills.initSkill1.getId()));

        TestData.EmployeeSkills.initEmployeeSkill2.setId(
                new EmployeeSkill.EmployeeSkillPK(adminId, TestData.Skills.initSkill2.getId()));

        TestData.EmployeeSkills.initEmployeeSkill1.persist();
        TestData.EmployeeSkills.initEmployeeSkill2.persist();
    }


    @Transactional
    public void initData() {
        initEmployeeSkills();
    }
}
