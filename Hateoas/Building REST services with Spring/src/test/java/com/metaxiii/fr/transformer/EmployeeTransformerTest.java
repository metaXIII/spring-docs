package com.metaxiii.fr.transformer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.metaxiii.fr.dto.EmployeeDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTransformerTest {

  private EmployeeTransformer transformer;

  @AfterEach
  public void afterEach() {
    transformer = null;
  }

  @BeforeEach
  public void beforeEach() {
    transformer = new EmployeeTransformer();
  }

  @Test
  void itShouldToDomain() {
    final var dto = EmployeeDTO.builder().firstName("aze").lastName("AZE").build();
    assertDoesNotThrow(() -> {
      final var result = transformer.toDomain(dto);
      assertEquals("aze", result.getFirstName());
      assertEquals("AZE", result.getLastName());
      assertNull(result.getId());
      assertNull(result.getRole());
    });
  }
}
