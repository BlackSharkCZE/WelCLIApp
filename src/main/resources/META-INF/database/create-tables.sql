CREATE TABLE user_entity (
	id           SERIAL PRIMARY KEY,
	date_created TIMESTAMP   NOT NULL DEFAULT now(),
	nick         VARCHAR(64) NOT NULL
);
