package com.metaxiii.fr.batchprocessing;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchProcessingApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> BatchProcessingApplication.main(new String[] {}));
  }
}
