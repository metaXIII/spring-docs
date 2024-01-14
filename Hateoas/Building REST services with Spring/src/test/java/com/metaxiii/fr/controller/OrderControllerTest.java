package com.metaxiii.fr.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.metaxiii.fr.dto.OrderDTO;
import com.metaxiii.fr.enums.Status;
import com.metaxiii.fr.input.OrderInput;
import com.metaxiii.fr.model.OrderModel;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerTest {

  @Autowired
  private WebTestClient client;

  @Test
  void itShouldDeleteOrder() {
    client.delete().uri("orders/8a6bc99f-d4cb-43ba-a490-fc54efed12b8").exchange().expectStatus().isNoContent();
  }

  @Test
  void itShouldGetAllOrders() {
    client.get().uri("orders").exchange().expectStatus().isOk().expectBodyList(OrderModel.class);
  }

  @Test
  void itShouldGetOrder() {
    client
      .get()
      .uri("orders/8a6bc99f-d4cb-43ba-a490-fc54efed12b8")
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody(OrderModel.class)
      .value(orderModel -> {
        assertEquals("MacBook Pro", orderModel.getProperties().getDescription());
        assertEquals(Status.COMPLETED, orderModel.getProperties().getStatus());
      });
  }

  @Test
  void itShouldInsertNewOrder() {
    client
      .post()
      .uri("orders")
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(OrderInput.builder().description("new description").status(Status.IN_PROGRESS).build())
      .exchange()
      .expectStatus()
      .isCreated();
  }

  @Test
  void itShouldPatchOrder() {
    client
      .patch()
      .uri("orders/8a6bc99f-d4cb-43ba-a490-fc54efed12b8")
      .bodyValue(OrderDTO.builder().status(Status.CANCELLED).build())
      .exchange()
      .expectStatus()
      .isAccepted()
      .expectBody(OrderModel.class)
      .value(orderModel -> {
        assertEquals(UUID.fromString("8a6bc99f-d4cb-43ba-a490-fc54efed12b8"), orderModel.getId());
        assertEquals("MacBook Pro", orderModel.getProperties().getDescription());
        assertEquals(Status.CANCELLED, orderModel.getProperties().getStatus());
      });
  }

  @Test
  void itShouldReturn400ResponseEntityWhenDtoIsNotValidPatchOrder() {
    client
      .patch()
      .uri("orders/8a6bc99f-d4cb-43ba-a490-fc54efed12b8")
      .bodyValue(OrderDTO.builder().status(null).build())
      .exchange()
      .expectStatus()
      .isBadRequest()
      .expectBody()
      .consumeWith(System.out::println);
  }

  @Test
  void itShouldReturn404ResponseEntityWhenNotFoundPatchOrder() {
    client
      .patch()
      .uri("orders/8a6bc99f-d4cb-43ba-a490-fc54efed12b1")
      .bodyValue(OrderDTO.builder().status(Status.CANCELLED).build())
      .exchange()
      .expectStatus()
      .isNotFound()
      .expectBody()
      .consumeWith(System.out::println);
  }
}
