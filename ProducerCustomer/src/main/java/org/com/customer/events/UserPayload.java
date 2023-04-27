package org.com.customer.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreateUser.class, name = CreateUser.TYPE),
    @JsonSubTypes.Type(value = DeleteUser.class, name = "DELETE_CUSTOMER"),
    @JsonSubTypes.Type(value = UpdateUser.class, name = "UPDATE_CUSTOMER")
})

public abstract class UserPayload {
    @JsonProperty
    public UUID id;
    public UserPayload(UUID id) {
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
