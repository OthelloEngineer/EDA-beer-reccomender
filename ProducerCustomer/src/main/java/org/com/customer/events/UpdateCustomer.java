package org.com.customer.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UpdateCustomer extends CustomerPayload{
    @JsonProperty
    public static final String TYPE = "UPDATE_CUSTOMER";
    public UpdateCustomer(UUID id) {
        super(id);
    }
}
