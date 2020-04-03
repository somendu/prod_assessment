-- Drop existing objects.
DROP SEQUENCE order_stock_seq;
DROP TABLE order_stock;

-- Create table.
CREATE TABLE order_stock (
			i_order_stock_id integer primary key USING INDEX (CREATE INDEX ix_i_order_stock_id ON order_stock(i_order_stock_id)),
			i_product_id integer,
			i_admin_id integer,
			i_count integer,
			i_status	integer default 0,
			c_input_user	varchar2(20),
			t_input_date	date default sysdate,
			c_update_user	varchar2(20),
			t_update_date	date,
			);

-- Create sequence.
CREATE SEQUENCE order_stock_seq START WITH 1 INCREMENT BY 1 NOMAXVALUE;

-- Create trigger.
CREATE OR REPLACE TRIGGER order_stock_trigger
BEFORE INSERT ON order_stock
FOR EACH ROW WHEN (NEW.i_order_stock_id IS NULL)
BEGIN
	SELECT	order_stock_seq.NEXTVAL
	INTO	:NEW.i_order_stock_id
	FROM	DUAL;
END;
/

COMMIT;