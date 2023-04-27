package org.com.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreateCustomer.class, name = CreateCustomer.TYPE),
    @JsonSubTypes.Type(value = DeleteCustomer.class, name = "DELETE_CUSTOMER"),
    @JsonSubTypes.Type(value = UpdateCustomer.class, name = "UPDATE_CUSTOMER")
})

public abstract class CustomerPayload {
    @JsonProperty
    public UUID id;
    public CustomerPayload(UUID id) {
        this.id = id;
    }
    public abstract String getType();
    public final UUID getId() {
        return id;
    }

    protected final String baseToString() {
        return "id=" + id;
    }
}
