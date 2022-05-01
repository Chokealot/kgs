package com.chokealot.konyiguitar.testcontainer;

import org.junit.ClassRule;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;

@ContextConfiguration
@ActiveProfiles("test")
public class MysqlContainerSetup {

    @ClassRule
    public static final MySQLContainer mySQLContainer = new MySQLContainer("mysql")
            .withDatabaseName("kgs");

    static {
        MySQLContainer mySQLContainer = MysqlContainerSetup.mySQLContainer;
        mySQLContainer.start();
    }

}
