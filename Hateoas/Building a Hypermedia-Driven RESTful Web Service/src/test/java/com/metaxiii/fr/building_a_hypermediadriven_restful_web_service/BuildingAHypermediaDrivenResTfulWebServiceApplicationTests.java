package com.metaxiii.fr.building_a_hypermediadriven_restful_web_service;

import com.metaxiii.fr.BuildingAHypermediaDrivenResTfulWebServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuildingAHypermediaDrivenResTfulWebServiceApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> {
            BuildingAHypermediaDrivenResTfulWebServiceApplication.main(new String[]{});
        });
    }
}
