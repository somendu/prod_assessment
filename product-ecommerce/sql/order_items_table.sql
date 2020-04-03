-- Drop existing objects.
DROP SEQUENCE order_items_seq;
DROP TABLE order_items;

-- Create table.
CREATE TABLE order_items (
			i_order_items_id integer primary key USING INDEX (CREATE INDEX ix_i_order_items_id ON order_items(i_order_items_id)),
			i_prod_order_id integer,
			i_product_id integer,
			i_prod_count integer,
			c_approve_status varchar2(1),
			i_status	integer default 0,
			c_input_user	varchar2(20),
			t_input_date	date default sysdate,
			c_update_user	varchar2(20),
			t_update_date	date
			);

-- Create sequence.
CREATE SEQUENCE order_items_seq START WITH 1 INCREMENT BY 1 NOMAXVALUE;

-- Create trigger.
CREATE OR REPLACE TRIGGER order_items_trigger
BEFORE INSERT ON order_items
FOR EACH ROW WHEN (NEW.i_order_items_id IS NULL)
BEGIN
	SELECT	order_items_seq.NEXTVAL
	INTO	:NEW.i_order_items_id
	FROM	DUAL;
END;
/

COMMIT;