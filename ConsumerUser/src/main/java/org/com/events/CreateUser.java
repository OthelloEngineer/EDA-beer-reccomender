package org.com.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateUser extends UserPayload {
    @JsonProperty
    public static final String TYPE = "CREATE_CUSTOMER";
    @JsonProperty
    public int age;
    @JsonProperty
    public String name;
    @JsonProperty
    public String email;
    @JsonProperty
    public String phone;
    @JsonProperty
    public String address;
    @JsonProperty
    public String city;
    @JsonProperty
    public String zip;
    @JsonProperty
    public String country;

    public CreateUser(@JsonProperty("id") UUID id,
                      @JsonProperty("age") int age,
                      @JsonProperty("name") String name,
                      @JsonProperty("email") String email,
                      @JsonProperty("phone") String phone,
                      @JsonProperty("address") String address,
                      @JsonProperty("city") String city,
                      @JsonProperty("zip") String zip,
                      @JsonProperty("country") String country) {
        super(id);
        this.age = age;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public static class Builder {
        private UUID id;
        private int age;
        private String name;
        private String email;
        private String phone;
        private String address;
        private String city;
        private String zip;
        private String country;

        public Builder(UUID id) {
            this.id = id;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public CreateUser build() {
            return new CreateUser(id, age, name, email, phone, address, city, zip, country);
        }
    }
}
