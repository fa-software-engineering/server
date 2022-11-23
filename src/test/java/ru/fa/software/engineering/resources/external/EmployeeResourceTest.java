package ru.fa.software.engineering.resources.external;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.fa.software.engineering.TestEnvironment;
import ru.fa.software.engineering.dbms.orm.internal.EmployeeSkill;
import ru.fa.software.engineering.dbms.orm.internal.Skill;
import ru.fa.software.engineering.utils.KeycloakAdminClient;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeResourceTest {

    @Inject
    KeycloakAdminClient keycloakAdminClient;

    @Inject
    TestEnvironment testEnvironment;

    @BeforeAll
    public void initTestData() {
        testEnvironment.initData();
    }

    private String getAdminId() {
        return keycloakAdminClient.getKeycloak()
                .realm("resource-accounting-system")
                .users()
                .search("admin")
                .get(0).getId();
    }

    @Test
    void getSkillsByEmployeeId() {

        String adminId = getAdminId();

        String ok = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .when().get("/employees/" + adminId + "/skills/")
                .then().statusCode(200).extract().asString();

        System.out.println(ok);
    }

    @Test
    void addSkillByEmployeeId() {
        Skill skill = TestEnvironment.TestData.Skills.initSkill3;

        String adminId = getAdminId();

        RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .when().post("/employees/" + adminId + "/skills/" + skill.getId().toString())
                .then().statusCode(200).extract().asString();
    }

    @Test
    void deleteSkillByEmployeeId() {

        String adminId = getAdminId();
        Long skillId = TestEnvironment.TestData.Skills.initSkill1.getId();

        String ok = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .when().delete("/employees/" + adminId + "/skills/" + skillId)
                .then().statusCode(200).extract().asString();

        System.out.println(ok);
    }
}