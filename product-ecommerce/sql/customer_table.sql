-- Drop existing objects.
DROP SEQUENCE customer_seq;
DROP TABLE customer;

-- Create table.
CREATE TABLE customer (
			i_cust_id integer primary key USING INDEX (CREATE INDEX ix_i_cust_id ON customer(i_cust_id)),
			c_nick_name	varchar2(10),
			c_first_name varchar2(20),
			c_last_name	varchar2(20),
			c_addr_line1 varchar2(20),
			c_addr_line2 varchar2(20),
			c_addr_line3 varchar2(20),
			c_city 	varchar2(20),
			c_country 	varchar2(20),
			c_mobile 	varchar2(20),
			c_email 	varchar2(20),
			i_status	integer default 0,
			c_input_user	varchar2(20),
			t_input_date	date default sysdate,
			c_update_user	varchar2(20),
			t_update_date	date,
			CONSTRAINT c_nick_name_unique UNIQUE (c_nick_name)
			);

-- Create sequence.
CREATE SEQUENCE customer_seq START WITH 1 INCREMENT BY 1 NOMAXVALUE;

-- Create trigger.
CREATE OR REPLACE TRIGGER customer_trigger
BEFORE INSERT ON customer
FOR EACH ROW WHEN (NEW.i_cust_id IS NULL)
BEGIN
	SELECT	customer_seq.NEXTVAL
	INTO	:NEW.i_cust_id
	FROM	DUAL;
END;
/

-- Create index.
CREATE INDEX ix_c_nick_name ON customer(c_nick_name);

COMMIT;