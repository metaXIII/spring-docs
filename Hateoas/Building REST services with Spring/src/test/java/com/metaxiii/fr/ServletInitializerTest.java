package com.metaxiii.fr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.junit.jupiter.api.Assertions.*;

class ServletInitializerTest {

    @Test
    void configure() {
        assertDoesNotThrow(() -> {
            final ServletInitializer servletInitializer = new ServletInitializer();
            return servletInitializer.configure(new SpringApplicationBuilder());
        });
    }
}
