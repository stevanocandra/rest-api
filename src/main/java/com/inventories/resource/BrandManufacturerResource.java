package com.inventories.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class BrandManufacturerResource extends ResourceSupport {
    private final String content;

    @JsonCreator
    public BrandManufacturerResource(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}