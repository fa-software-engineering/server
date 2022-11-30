package ru.fa.software.engineering;

import ru.fa.software.engineering.dbms.orm.external.Employee;
import ru.fa.software.engineering.dbms.orm.internal.EmployeeSkill;
import ru.fa.software.engineering.dbms.orm.internal.Project;
import ru.fa.software.engineering.dbms.orm.internal.Resource;
import ru.fa.software.engineering.dbms.orm.internal.Skill;
import ru.fa.software.engineering.dbms.repositories.external.EmployeeRepository;
import ru.fa.software.engineering.dbms.services.external.EmployeeService;
import ru.fa.software.engineering.utils.KeycloakAdminClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class TestEnvironment {

    @Inject
    KeycloakAdminClient keycloakAdminClient;

    @Inject
    EmployeeRepository employeeRepository;

    public static class TestData {

        public static class Employees {
            public static Employee admin;
        }

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

        public static class Projects {
            public static Project initProject1;
            public static Project initProject2;
            public static Project initProject3;
        }

        public static class Resources {
            public static Resource initResource1;
            public static Resource initResource2;
            public static Resource initResource3;
        }

    }

    public void initEmployees() {
        String adminId = keycloakAdminClient.getKeycloak()
                .realm("resource-accounting-system")
                .users()
                .search("admin")
                .get(0).getId();

        Employee admin = new Employee();
        admin.setId(adminId);
        admin.persist();

        TestData.Employees.admin = admin;
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

    public void initProjects() {
        Project initProject1 = new Project();
        initProject1.setTitle("initProject1");
        initProject1.setOwner(TestData.Employees.admin);
        initProject1.setStartDate(OffsetDateTime.now());
        initProject1.setEndDate(OffsetDateTime.now().plus(5, ChronoUnit.DAYS));
        initProject1.persist();

        Project initProject2 = new Project();
        initProject2.setTitle("initProject2");
        initProject2.setOwner(TestData.Employees.admin);
        initProject2.setStartDate(OffsetDateTime.now());
        initProject2.setEndDate(OffsetDateTime.now().plus(3, ChronoUnit.DAYS));
        initProject2.persist();

        Project initProject3 = new Project();
        initProject3.setTitle("initProject3");
        initProject3.setOwner(TestData.Employees.admin);
        initProject3.setStartDate(OffsetDateTime.now());
        initProject3.setEndDate(OffsetDateTime.now().plus(15, ChronoUnit.DAYS));
        initProject3.persist();

        TestData.Projects.initProject1 = initProject1;
        TestData.Projects.initProject2 = initProject2;
        TestData.Projects.initProject3 = initProject3;
    }

    public void initResources() {
        Resource initResource1 = new Resource();
        initResource1.setId(
                new Resource.EmployeeProjectPK(
                        TestData.Employees.admin.getId(),
                        TestData.Projects.initProject1.getId()));
        initResource1.setStartDate(OffsetDateTime.now());
        initResource1.setEndDate(OffsetDateTime.now().plus(5, ChronoUnit.DAYS));
        initResource1.setName("initResource1");
        initResource1.persist();

        Resource initResource2 = new Resource();
        initResource2.setId(
                new Resource.EmployeeProjectPK(
                        TestData.Employees.admin.getId(),
                        TestData.Projects.initProject2.getId()));
        initResource2.setStartDate(OffsetDateTime.now());
        initResource2.setEndDate(OffsetDateTime.now().plus(3, ChronoUnit.DAYS));
        initResource2.setName("initResource2");
//        initResource2.persist();

        Resource initResource3 = new Resource();
        initResource3.setId(
                new Resource.EmployeeProjectPK(
                        TestData.Employees.admin.getId(),
                        TestData.Projects.initProject3.getId()));
        initResource3.setStartDate(OffsetDateTime.now());
        initResource3.setEndDate(OffsetDateTime.now().plus(15, ChronoUnit.DAYS));
        initResource3.setName("initResource3");
        initResource3.persist();

        TestData.Resources.initResource1 = initResource1;
        TestData.Resources.initResource2 = initResource2;
        TestData.Resources.initResource3 = initResource3;
    }


    @Transactional
    public void initData() {
        initEmployees();
        initEmployeeSkills();
        initProjects();
        initResources();
    }
}
