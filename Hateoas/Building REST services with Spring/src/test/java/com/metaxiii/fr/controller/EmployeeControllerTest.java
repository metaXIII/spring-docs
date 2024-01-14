package com.metaxiii.fr.controller;

import com.metaxiii.fr.dto.EmployeeDTO;
import com.metaxiii.fr.enums.Role;
import com.metaxiii.fr.input.EmployeeInput;
import com.metaxiii.fr.model.EmployeeModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

  @Autowired
  private WebTestClient client;

  @Test
  void deleteEmployee() {
    client.delete().uri("employees/329fc021-4cdd-4f72-882c-423f5d86bd80").exchange().expectStatus().isNoContent();
  }

  @Test
  void getAllEmployees() {
    client.get().uri("employees").exchange().expectStatus().isOk().expectBodyList(EmployeeModel.class);
  }

  @Test
  void getEmployee() {
    client
      .get()
      .uri("employees/329fc021-4cdd-4f72-882c-423f5d86bd80")
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody(EmployeeModel.class);
  }

  @Test
  void getEmployeeIsNotFound() {
    client
      .get()
      .uri("employees/329fc021-4cdd-4f72-882c-423f5d86bd81")
      .exchange()
      .expectStatus()
      .isNotFound()
      .expectBody()
      .consumeWith(System.out::println);
  }

  @Test
  void newEmployee() {
    client
      .post()
      .uri("employees")
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(EmployeeInput.builder().firstName("f").lastName("l").role(Role.DEV).build())
      .exchange()
      .expectStatus()
      .isCreated()
      .expectBody(EmployeeModel.class);
  }

  @Test
  void replaceEmployee() {
    client
      .patch()
      .uri("employees/329fc021-4cdd-4f72-882c-423f5d86bd80")
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(EmployeeDTO.builder().firstName("f211").lastName("l211").build())
      .exchange()
      .expectStatus()
      .isAccepted()
      .expectBody()
      .consumeWith(System.out::println);
  }

  @Test
  void replaceEmployeeIsBadRequest() {
    client
      .patch()
      .uri("employees/329fc021-4cdd-4f72-882c-423f5d86bd80")
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(EmployeeDTO.builder().firstName("f2").lastName("l2").build())
      .exchange()
      .expectStatus()
      .isBadRequest()
      .expectBody()
      .consumeWith(System.out::println);
  }

  @Test
  void replaceEmployeeIsnotFound() {
    client
      .patch()
      .uri("employees/329fc021-4cdd-4f72-882c-423f5d86bd81")
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(EmployeeDTO.builder().firstName("f211").lastName("l211").build())
      .exchange()
      .expectStatus()
      .isNotFound()
      .expectBody()
      .consumeWith(System.out::println);
  }
}
