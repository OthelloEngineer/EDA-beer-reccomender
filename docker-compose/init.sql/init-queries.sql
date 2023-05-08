create TABLE  country(
     "id" SERIAL PRIMARY KEY,
     "name" VARCHAR(255) NOT NULL UNIQUE
);

create TABLE city(
     "id" SERIAL PRIMARY KEY,
     "name" VARCHAR(255) NOT NULL,
     "address" VARCHAR(255) NOT NULL,
     "zip_code" VARCHAR(255) NOT NULL UNIQUE,
     "country_id" INTEGER NOT NULL,
     FOREIGN KEY("country_id") REFERENCES country("id")
);

create TABLE "user"(
       "id" SERIAL PRIMARY KEY,
       "name" VARCHAR(255) NOT NULL,
       "age" SMALLINT NOT NULL,
       "email" VARCHAR(255) NOT NULL,
       "phone" VARCHAR(255) NOT NULL,
       "city_id" INTEGER NOT NULL,
       FOREIGN KEY("city_id") REFERENCES city("id")
);

CREATE OR REPLACE PROCEDURE insert_user(
   user_name VARCHAR(255),
   age INTEGER,
   email VARCHAR(255),
   phone VARCHAR(255),
   city_name VARCHAR(255),
   city_address VARCHAR(255),
   city_zip_code VARCHAR(255),
   country_name VARCHAR(255)
)
language plpgsql
as $$
    BEGIN
        INSERT INTO country(name) VALUES(country_name) ON CONFLICT DO NOTHING;
        INSERT INTO city(name, address, zip_code, country_id) VALUES(city_name, city_address, city_zip_code, (SELECT id FROM country WHERE name = country_name)) ON CONFLICT DO NOTHING;
        INSERT INTO "user"(name, age, email, phone, city_id) VALUES(user_name, age, email, phone, (SELECT id FROM city WHERE name = city_name));
    commit;
END;$$