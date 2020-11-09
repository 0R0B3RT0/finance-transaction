-- CREATE SEQUENCES
CREATE SEQUENCE code_sequence;

-- CREATE TRIGGERS
CREATE OR REPLACE FUNCTION trigger_set_update()
	RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = now();
  RETURN NEW;
END;
	$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION trigger_set_code()
	RETURNS TRIGGER AS $$
BEGIN
  NEW.code = nextval('code_sequence');
  RETURN NEW;
END;
	$$ LANGUAGE 'plpgsql';

-- CREATE TABLES

CREATE TABLE account
(
  id              UUID   PRIMARY KEY NOT NULL,
  code            BIGINT             NOT NULL,
  document_number TEXT               NOT NULL,
  created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  updated_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  deleted_at      TIMESTAMP WITH TIME ZONE,
  enabled         BOOLEAN not null default true
);

CREATE UNIQUE INDEX account_id_idx
  ON account
  USING btree
  (id);

CREATE UNIQUE INDEX account_code_idx
  ON account
  USING btree
  (code);

CREATE INDEX account_document_number_idx
   ON account
   USING btree
   (document_number);

CREATE TRIGGER account_insert_tgr
 BEFORE INSERT ON account
 FOR EACH ROW
 EXECUTE PROCEDURE trigger_set_code();

CREATE TRIGGER account_update_tgr
 BEFORE insert or update ON account
 FOR EACH ROW
 EXECUTE PROCEDURE trigger_set_update();

CREATE TABLE transaction
(
  id              UUID   PRIMARY KEY NOT NULL,
  account_id      UUID             NOT NULL,
  operation_type  BIGINT             NOT NULL,
  amount          NUMERIC(12, 6)     NOT NULL,
  created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  updated_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
  deleted_at      TIMESTAMP WITH TIME ZONE,
  enabled         BOOLEAN not null default true
);

CREATE UNIQUE INDEX transaction_id_idx
  ON transaction
  USING btree
  (id);

alter table transaction add constraint account_id_fkey foreign key (account_id) references account (id) match simple on
update
	no action on
	delete
	no action;

CREATE INDEX transaction_account_idx
  ON transaction
  USING btree
  (account_id);

CREATE INDEX transaction_operation_type_idx
  ON transaction
  USING btree
  (operation_type);

CREATE TRIGGER transaction_update_tgr
 BEFORE insert or update ON transaction
 FOR EACH ROW
 EXECUTE PROCEDURE trigger_set_update();
