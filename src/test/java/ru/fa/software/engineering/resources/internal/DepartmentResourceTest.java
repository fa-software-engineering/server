package ru.fa.software.engineering.resources.internal;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.fa.software.engineering.AbstractTest;
import ru.fa.software.engineering.TestEnvironment;
import ru.fa.software.engineering.dbms.orm.internal.Department;
import ru.fa.software.engineering.dto.internal.DepartmentDto;
import ru.fa.software.engineering.utils.ModelMapperUtil;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepartmentResourceTest extends AbstractTest {

    @Override
    @BeforeAll
    public void initTestData() {
        super.initTestData();
    }

    @Test
    void getAll() {
        String response = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .when().get("/departments/")
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }

    @Test
    void getById() {
        Department department = TestEnvironment.TestData.Departments.initDepartment1;

        String response = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .when().get("/departments/" + department.getId().toString())
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }

    @Test
    void create() {
        DepartmentDto department = new DepartmentDto();
        department.setTitle("created department");

        String response = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .body(department)
                .when().post("/departments/")
                .then().statusCode(200).extract().asString();

        System.out.println(response);

    }

    @Test
    void update() {
        Department department = TestEnvironment.TestData.Departments.initDepartment1;

        DepartmentDto departmentDto = ModelMapperUtil.map(department, DepartmentDto.class);
        departmentDto.setTitle("updated department 1");

        String response = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .header("Content-Type", "application/json")
                .body(departmentDto)
                .when().put("/departments/" + departmentDto.getId().toString())
                .then().statusCode(200).extract().asString();

        System.out.println(response);
    }

    @Test
    void delete() {
        Department department = TestEnvironment.TestData.Departments.initDepartment3;

        String ok = RestAssured
                .given()
                .auth()
                .oauth2(keycloakAdminClient.getAccessToken("admin", "admin"))
                .when().delete("/departments/" + department.getId().toString())
                .then().statusCode(200).extract().asString();
        System.out.println(ok);
    }
}