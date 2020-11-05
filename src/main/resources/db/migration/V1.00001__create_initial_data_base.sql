CREATE TABLE account
(
  id              BIGINT PRIMARY KEY NOT NULL,
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

CREATE INDEX account_document_number_idx
   ON account
   USING btree
   (document_number);

CREATE TABLE transaction
(
  id              BIGINT PRIMARY KEY NOT NULL,
  account_id      BIGINT             NOT NULL,
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
