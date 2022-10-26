package com.metaxiii.fr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class BuildingRestServicesWithSpringApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> BuildingRestServicesWithSpringApplication.main(new String[]{}));
    }

}
