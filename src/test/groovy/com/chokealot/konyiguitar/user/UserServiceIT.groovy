package com.chokealot.konyiguitar.user

import com.chokealot.konyiguitar.testcontainer.MysqlContainerSetup
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

import static io.restassured.RestAssured.given

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserServiceIT extends Specification{

    @LocalServerPort
    public int localPort

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MysqlContainerSetup.mySQLContainer::getJdbcUrl)
        registry.add("spring.datasource.username", MysqlContainerSetup.mySQLContainer::getUsername)
        registry.add("spring.datasource.password", MysqlContainerSetup.mySQLContainer::getPassword)
    }

    def setup() throws Exception {
        RestAssured.port = localPort
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
        requestSpecBuilder.setBasePath("/api/v1/")
        RestAssured.requestSpecification = requestSpecBuilder.build()
    }

    def "Test 1 - create a user"() {
        given: "a user to create"

        User user = new User()
        user.setId(1L)
        user.setUsername("Chokealot")
        user.setPassword("test")
        user.setEmail("chokealot@test.com")

        when: "querying the database"

        def result = given()
                .contentType(ContentType.JSON)
                //.header("Authorization", "Bearer 2f472709-cc93-4c26-b332-00712ed9f8a8")
                .body(user)
                .when()
                .post("users")

        then:

        assert result.getStatusCode() == HttpStatus.SC_OK
    }

}
