package com.metaxiii.fr.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class Greeting extends RepresentationModel<Greeting> {
    private final String content;

    @JsonCreator //Signals how Jackson can create an instance of this POJO
    public Greeting(@JsonProperty("content") String content) { //@JsonProperty => Marks the field into which Jackson should put this constructor argument
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}
