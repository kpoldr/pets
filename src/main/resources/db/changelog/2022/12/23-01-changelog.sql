-- liquibase formatted sql

-- changeset Kiwi:1671798683141-1
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

-- changeset Kiwi:1671798683141-2
CREATE TABLE AppUser (id BIGINT NOT NULL, username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, CONSTRAINT pk_appuser PRIMARY KEY (id));

-- changeset Kiwi:1671798683141-3
CREATE TABLE AppUser_roles (AppUser_id BIGINT NOT NULL, roles_id BIGINT NOT NULL);

-- changeset Kiwi:1671798683141-4
CREATE TABLE Role (id BIGINT NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT pk_role PRIMARY KEY (id));

-- changeset Kiwi:1671798683141-5
CREATE TABLE pet_color (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT pk_pet_color PRIMARY KEY (id));

-- changeset Kiwi:1671798683141-6
CREATE TABLE pet_country (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT pk_pet_country PRIMARY KEY (id));

-- changeset Kiwi:1671798683141-7
CREATE TABLE pet_types (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT pk_pet_types PRIMARY KEY (id));

-- changeset Kiwi:1671798683141-8
CREATE TABLE pets (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NOT NULL, idCode VARCHAR(255) NOT NULL, user_id BIGINT, pet_color_id BIGINT, pet_country_id BIGINT, pet_type_id BIGINT, CONSTRAINT pk_pets PRIMARY KEY (id));

-- changeset Kiwi:1671798683141-9
ALTER TABLE AppUser ADD CONSTRAINT uc_appuser_username UNIQUE (username);

-- changeset Kiwi:1671798683141-10
ALTER TABLE pets ADD CONSTRAINT uc_pets_idcode UNIQUE (idCode);

-- changeset Kiwi:1671798683141-11
ALTER TABLE pets ADD CONSTRAINT uc_pets_name UNIQUE (name);

-- changeset Kiwi:1671798683141-12
ALTER TABLE Role ADD CONSTRAINT uc_role_name UNIQUE (name);

-- changeset Kiwi:1671798683141-13
ALTER TABLE pets ADD CONSTRAINT FK_PETS_ON_PET_COLOR FOREIGN KEY (pet_color_id) REFERENCES pet_color (id);

-- changeset Kiwi:1671798683141-14
ALTER TABLE pets ADD CONSTRAINT FK_PETS_ON_PET_COUNTRY FOREIGN KEY (pet_country_id) REFERENCES pet_country (id);

-- changeset Kiwi:1671798683141-15
ALTER TABLE pets ADD CONSTRAINT FK_PETS_ON_PET_TYPE FOREIGN KEY (pet_type_id) REFERENCES pet_types (id);

-- changeset Kiwi:1671798683141-16
ALTER TABLE pets ADD CONSTRAINT FK_PETS_ON_USER FOREIGN KEY (user_id) REFERENCES AppUser (id);

-- changeset Kiwi:1671798683141-17
ALTER TABLE AppUser_roles ADD CONSTRAINT fk_approl_on_app_user FOREIGN KEY (AppUser_id) REFERENCES AppUser (id);

-- changeset Kiwi:1671798683141-18
ALTER TABLE AppUser_roles ADD CONSTRAINT fk_approl_on_role FOREIGN KEY (roles_id) REFERENCES Role (id);

