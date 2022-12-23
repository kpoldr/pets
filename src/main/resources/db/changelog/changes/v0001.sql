-- liquibase formatted sql

-- changeset Kiwi:1671726011108-1
CREATE TABLE Role
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

-- changeset Kiwi:1671726011108-2
CREATE TABLE pet_color
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_pet_color PRIMARY KEY (id)
);

-- changeset Kiwi:1671726011108-3
CREATE TABLE pet_country
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_pet_country PRIMARY KEY (id)
);

-- changeset Kiwi:1671726011108-4
CREATE TABLE pet_types
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_pet_types PRIMARY KEY (id)
);

-- changeset Kiwi:1671726011108-5
CREATE TABLE pets
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    name           VARCHAR(255) NOT NULL,
    idCode         VARCHAR(255) NOT NULL,
    user_id        BIGINT,
    pet_color_id   BIGINT,
    pet_country_id BIGINT,
    pet_type_id    BIGINT,
    CONSTRAINT pk_pets PRIMARY KEY (id)
);

-- changeset Kiwi:1671726011108-6
CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset Kiwi:1671726011108-7
CREATE TABLE users_roles
(
    AppUser_id BIGINT NOT NULL,
    roles_id   BIGINT NOT NULL
);

-- changeset Kiwi:1671726011108-8
ALTER TABLE pets
    ADD CONSTRAINT uc_pets_idcode UNIQUE (idCode);

-- changeset Kiwi:1671726011108-9
ALTER TABLE pets
    ADD CONSTRAINT uc_pets_name UNIQUE (name);

-- changeset Kiwi:1671726011108-10
ALTER TABLE Role
    ADD CONSTRAINT uc_role_name UNIQUE (name);

-- changeset Kiwi:1671726011108-11
ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

-- changeset Kiwi:1671726011108-12
ALTER TABLE pets
    ADD CONSTRAINT FK_PETS_ON_PET_COLOR FOREIGN KEY (pet_color_id) REFERENCES pet_color (id);

-- changeset Kiwi:1671726011108-13
ALTER TABLE pets
    ADD CONSTRAINT FK_PETS_ON_PET_COUNTRY FOREIGN KEY (pet_country_id) REFERENCES pet_country (id);

-- changeset Kiwi:1671726011108-14
ALTER TABLE pets
    ADD CONSTRAINT FK_PETS_ON_PET_TYPE FOREIGN KEY (pet_type_id) REFERENCES pet_types (id);

-- changeset Kiwi:1671726011108-15
ALTER TABLE pets
    ADD CONSTRAINT FK_PETS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset Kiwi:1671726011108-16
ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_app_user FOREIGN KEY (AppUser_id) REFERENCES users (id);

-- changeset Kiwi:1671726011108-17
ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES Role (id);

