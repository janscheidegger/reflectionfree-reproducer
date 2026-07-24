package org.acme;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.UUID;

public class Request {

    @JsonAlias({"documentId", "id"})
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}