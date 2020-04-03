-- Drop existing objects.
DROP SEQUENCE prod_admin_seq;
DROP TABLE prod_admin;

-- Create table.
CREATE TABLE prod_admin (
			i_prod_admin_id integer primary key USING INDEX (CREATE INDEX ix_i_prod_admin_id ON prod_admin(i_prod_admin_id)),
			c_admin_name	varchar2(10),
			c_country 	varchar2(20),
			c_mobile 	varchar2(20),
			c_email 	varchar2(20),
			i_status	integer default 0,
			c_input_user	varchar2(20),
			t_input_date	date default sysdate,
			c_update_user	varchar2(20),
			t_update_date	date,
			CONSTRAINT c_admin_name_unique UNIQUE (c_admin_name)
			);

-- Create sequence.
CREATE SEQUENCE prod_admin_seq START WITH 1 INCREMENT BY 1 NOMAXVALUE;

-- Create trigger.
CREATE OR REPLACE TRIGGER prod_admin_trigger
BEFORE INSERT ON prod_admin
FOR EACH ROW WHEN (NEW.i_prod_admin_id IS NULL)
BEGIN
	SELECT	prod_admin_seq.NEXTVAL
	INTO	:NEW.i_prod_admin_id
	FROM	DUAL;
END;
/

-- Create index.
CREATE INDEX ix_c_admin_name ON prod_admin(c_admin_name);

COMMIT;