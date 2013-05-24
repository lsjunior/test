CREATE TABLE contact
(
  id integer NOT NULL,
  name character varying(100) NOT NULL,
  email character varying(100) NOT NULL,
  CONSTRAINT pk_contact PRIMARY KEY (id ),
  CONSTRAINT uk_contact_email UNIQUE (email )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contact
  OWNER TO postgres;