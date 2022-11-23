package ru.fa.software.engineering.resources.internal;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import ru.fa.software.engineering.TestEnvironment;
import ru.fa.software.engineering.dbms.orm.internal.Skill;
import ru.fa.software.engineering.utils.KeycloakAdminClient;

import javax.inject.Inject;

@QuarkusTest
class SkillResourceTest {

    @Inject
    KeycloakAdminClient keycloakAdminClient;


    @Test
    void getAll() {
    }

    @Test
    void getById() {
        Skill skill = TestEnvironment.TestData.Skills.initSkill1;

        String gotSkill = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .when().get("/skills/" + skill.getId().toString())
                .then().statusCode(200).extract().asString();

        System.out.println(gotSkill);

    }

    @Test
    void create() {
        Skill skill = new Skill();
        skill.setTitle("Some skill test");
        skill.setDescription("Test-nothing");

        RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .body(skill)
                .when().post("/skills")
                .then().statusCode(200).extract().asString();
    }

    @Test
    void update() {
        Skill skill = TestEnvironment.TestData.Skills.initSkill2;
        skill.setTitle("Updated skill");

        String ok = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .body(skill)
                .when().put("/skills/" + skill.getId().toString())
                .then().statusCode(200).extract().asString();
        System.out.println(ok);
    }

    @Test
    void delete() {
        Skill skill = TestEnvironment.TestData.Skills.initSkill3;

        String ok = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .when().delete("/skills/" + skill.getId().toString())
                .then().statusCode(200).extract().asString();
        System.out.println(ok);
    }
}