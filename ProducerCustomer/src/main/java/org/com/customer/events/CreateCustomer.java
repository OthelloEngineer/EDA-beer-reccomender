package org.com.customer.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateCustomer extends CustomerPayload {
    @JsonProperty
    public static final String TYPE = "CREATE_CUSTOMER";
    public int age;
    public String name;
    public String email;
    public String phone;
    public String address;
    public String city;
    public String zip;
    public String country;

    public CreateCustomer(UUID id, int age, String name, String email, String phone, String address, String city, String zip, String country) {
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

    //have the constructor be dissected into a builder
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

        public CreateCustomer build() {
            return new CreateCustomer(id, age, name, email, phone, address, city, zip, country);
        }
    }
}
