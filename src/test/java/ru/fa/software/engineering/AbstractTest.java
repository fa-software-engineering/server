package ru.fa.software.engineering;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import ru.fa.software.engineering.utils.KeycloakAdminClient;

import javax.inject.Inject;


@QuarkusTest
public abstract class AbstractTest {

    @Inject
    protected ObjectMapper objectMapper;

    @Inject
    protected KeycloakAdminClient keycloakAdminClient;

    @Inject
    protected TestEnvironment testEnvironment;

    public void initTestData() {
        testEnvironment.initData();
    }
}
