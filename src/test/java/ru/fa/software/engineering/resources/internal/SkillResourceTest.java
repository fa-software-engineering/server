package ru.fa.software.engineering.resources.internal;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import ru.fa.software.engineering.AbstractTest;
import ru.fa.software.engineering.TestEnvironment;
import ru.fa.software.engineering.dbms.orm.internal.Skill;
import ru.fa.software.engineering.dto.internal.SkillDto;
import ru.fa.software.engineering.utils.KeycloakAdminClient;
import ru.fa.software.engineering.utils.ModelMapperUtil;

import javax.inject.Inject;

@QuarkusTest
class SkillResourceTest extends AbstractTest {

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

        SkillDto skillDto = ModelMapperUtil.map(skill, SkillDto.class);

        RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .body(skillDto)
                .when().post("/skills")
                .then().statusCode(200).extract().asString();
    }

    @Test
    void update() {
        Skill skill = TestEnvironment.TestData.Skills.initSkill2;
        skill.setTitle("Updated skill");

        SkillDto skillDto = ModelMapperUtil.map(skill, SkillDto.class);

        String ok = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .body(skillDto)
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