package ru.fa.software.engineering.resources.internal;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.fa.software.engineering.AbstractTest;
import ru.fa.software.engineering.TestEnvironment;
import ru.fa.software.engineering.dbms.orm.internal.Resource;
import ru.fa.software.engineering.dto.internal.ResourceDto;
import ru.fa.software.engineering.utils.KeycloakAdminClient;
import ru.fa.software.engineering.utils.ModelMapperUtil;

import javax.inject.Inject;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResourceResourceTest extends AbstractTest {

    @Inject
    KeycloakAdminClient keycloakAdminClient;

    @Override
    @BeforeAll
    public void initTestData() {
        super.initTestData();
    }


    @Test
    void getById() {
        Resource resource = TestEnvironment.TestData.Resources.initResource1;

        String response = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .queryParam("employeeId", resource.getId().getEmployeeId())
                .queryParam("projectId", resource.getId().getProjectId())
                .when().get("/resources")
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }

    @Test
    void update() {
        Resource resource = TestEnvironment.TestData.Resources.initResource1;
        resource.setName("updated init resource1");

        String response = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .queryParam("employeeId", resource.getId().getEmployeeId())
                .queryParam("projectId", resource.getId().getProjectId())
                .body(resource)
                .when().put("/resources")
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }

    @Test
    void getAll() {
        String response = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .when().get("/resources")
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }

    @Test
    void delete() {
        Resource resource = TestEnvironment.TestData.Resources.initResource3;

        String response = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .queryParam("employeeId", resource.getId().getEmployeeId())
                .queryParam("projectId", resource.getId().getProjectId())
                .when().delete("/resources")
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }

    @Test
    void create() {
        Resource resource = TestEnvironment.TestData.Resources.initResource2;

        resource.setId(new Resource.EmployeeProjectPK(
                TestEnvironment.TestData.Employees.admin.getId(),
                TestEnvironment.TestData.Projects.initProject2.getId()
        ));
        resource.setName("created resource 4");

        ResourceDto resourceDto = ModelMapperUtil.map(resource, ResourceDto.class);

        String response = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .body(resourceDto)
                .when().post("/resources")
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }
}
