package ru.fa.software.engineering;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;


@QuarkusTest
public abstract class AbstractTest {

    @Inject
    protected ObjectMapper objectMapper;

    @Inject
    protected TestEnvironment testEnvironment;

    public void initTestData() {
        testEnvironment.initData();
    }
}
