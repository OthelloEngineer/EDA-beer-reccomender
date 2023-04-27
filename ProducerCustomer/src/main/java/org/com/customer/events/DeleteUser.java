package org.com.customer.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DeleteUser extends UserPayload {
    @JsonProperty
    public static final String TYPE = "DELETE_CUSTOMER";

    public DeleteUser(UUID id) {
        super(id);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
