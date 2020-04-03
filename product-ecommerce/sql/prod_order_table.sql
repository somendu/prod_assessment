-- Drop existing objects.
DROP SEQUENCE prod_order_seq;
DROP TABLE prod_order;

-- Create table.
CREATE TABLE prod_order (
			i_prod_order_id integer primary key USING INDEX (CREATE INDEX ix_i_prod_order_id ON prod_order(i_prod_order_id)),
			i_cust_id integer,
			i_admin_id integer,
			i_status	integer default 0,
			c_input_user	varchar2(20),
			t_input_date	date default sysdate,
			c_update_user	varchar2(20),
			t_update_date	date
			);

-- Create sequence.
CREATE SEQUENCE prod_order_seq START WITH 1 INCREMENT BY 1 NOMAXVALUE;

-- Create trigger.
CREATE OR REPLACE TRIGGER prod_order_trigger
BEFORE INSERT ON prod_order
FOR EACH ROW WHEN (NEW.i_prod_order_id IS NULL)
BEGIN
	SELECT	prod_order_seq.NEXTVAL
	INTO	:NEW.i_prod_order_id
	FROM	DUAL;
END;
/

COMMIT;