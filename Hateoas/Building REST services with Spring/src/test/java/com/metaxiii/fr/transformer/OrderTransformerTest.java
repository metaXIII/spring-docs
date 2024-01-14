package com.metaxiii.fr.transformer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.metaxiii.fr.dto.OrderDTO;
import com.metaxiii.fr.enums.Status;
import com.metaxiii.fr.input.OrderInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTransformerTest {

  private OrderTransformer transformer;

  @AfterEach
  public void afterEach() {
    transformer = null;
  }

  @BeforeEach
  public void beforeEach() {
    transformer = new OrderTransformer();
  }

  @Test
  void itShouldToDTO() {
    final var input = OrderInput.builder().status(Status.COMPLETED).description("some description").build();
    assertDoesNotThrow(() -> {
      final var result = transformer.toDTO(input);
      assertEquals(Status.COMPLETED, result.getStatus());
    });
  }

  @Test
  void itShouldToDomain() {
    final var dto = OrderDTO.builder().status(Status.CANCELLED).build();
    assertDoesNotThrow(() -> {
      final var result = transformer.toDomain(dto);
      assertEquals(Status.CANCELLED, result.getStatus());
      assertNull(result.getDescription());
    });
  }
}
