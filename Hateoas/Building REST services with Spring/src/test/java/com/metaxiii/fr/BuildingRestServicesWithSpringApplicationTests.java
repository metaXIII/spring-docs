package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuildingRestServicesWithSpringApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> BuildingRestServicesWithSpringApplication.main(new String[] {}));
  }
}
