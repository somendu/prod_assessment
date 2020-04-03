-- Drop existing objects.
DROP SEQUENCE product_seq;
DROP TABLE product;

-- Create table.
CREATE TABLE product (
			i_product_id integer primary key USING INDEX (CREATE INDEX ix_i_product_id ON product(i_product_id)),
			c_prod_name	varchar2(20),
			c_prod_type varchar2(20),
			d_price number(10,2),
			i_status	integer default 0,
			c_input_user	varchar2(20),
			t_input_date	date default sysdate,
			c_update_user	varchar2(20),
			t_update_date	date,
			CONSTRAINT c_prod_name_unique UNIQUE (c_prod_name)
			);

-- Create sequence.
CREATE SEQUENCE product_seq START WITH 1 INCREMENT BY 1 NOMAXVALUE;

-- Create trigger.
CREATE OR REPLACE TRIGGER product_trgger
BEFORE INSERT ON product
FOR EACH ROW WHEN (NEW.i_product_id IS NULL)
BEGIN
	SELECT	product_seq.NEXTVAL
	INTO	:NEW.i_product_id
	FROM	DUAL;
END;
/

-- Create index.
CREATE INDEX ix_c_prod_name ON product(c_prod_name);

COMMIT;