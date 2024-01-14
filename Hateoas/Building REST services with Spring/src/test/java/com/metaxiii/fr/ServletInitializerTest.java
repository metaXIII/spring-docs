package com.metaxiii.fr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

class ServletInitializerTest {

  @Test
  void configure() {
    assertDoesNotThrow(() -> {
      final ServletInitializer servletInitializer = new ServletInitializer();
      return servletInitializer.configure(new SpringApplicationBuilder());
    });
  }
}
