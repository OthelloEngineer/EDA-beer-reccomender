package org.com.customer.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreateCustomer.class, name = CreateCustomer.TYPE),
    @JsonSubTypes.Type(value = DeleteCustomer.class, name = "DELETE_CUSTOMER"),
    @JsonSubTypes.Type(value = UpdateCustomer.class, name = "UPDATE_CUSTOMER")
})

public class CustomerPayload {
    public UUID id;
    public CustomerPayload(UUID id) {
        this.id = id;
    }
}
