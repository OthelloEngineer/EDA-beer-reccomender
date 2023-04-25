package org.com.customer.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DeleteCustomer extends CustomerPayload {
    @JsonProperty
    public static final String TYPE = "DELETE_CUSTOMER";

    public DeleteCustomer(UUID id) {
        super(id);
    }
}