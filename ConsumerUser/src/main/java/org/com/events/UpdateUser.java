package org.com.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UpdateUser extends UserPayload {
    @JsonProperty
    public static final String TYPE = "UPDATE_CUSTOMER";
    public UpdateUser(UUID id) {
        super(id);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
