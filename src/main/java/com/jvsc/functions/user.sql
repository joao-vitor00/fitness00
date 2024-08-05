-- Procedure to add a user
CREATE OR REPLACE PROCEDURE add_user(
    p_name VARCHAR,
    p_email VARCHAR,
    p_password VARCHAR,
    p_birthdate DATE
) AS
BEGIN
    INSERT INTO users (name, email, password, birthdate) VALUES (p_name, p_email, p_password, p_birthdate);
END;
/

-- Function to find a user by id
CREATE OR REPLACE FUNCTION find_user(p_id NUMBER) RETURN SYS_REFCURSOR AS
    user_cursor SYS_REFCURSOR;
BEGIN
    OPEN user_cursor FOR SELECT * FROM users WHERE id = p_id;
    RETURN user_cursor;
END;
/

-- Function to find a user by name
CREATE OR REPLACE FUNCTION find_user_by_name(p_name VARCHAR) RETURN SYS_REFCURSOR AS
    user_cursor SYS_REFCURSOR;
BEGIN
    OPEN user_cursor FOR SELECT * FROM users WHERE name = p_name;
    RETURN user_cursor;
END;
/

-- Function to find a user by email
CREATE OR REPLACE FUNCTION find_user_by_email(p_email VARCHAR) RETURN SYS_REFCURSOR AS
    user_cursor SYS_REFCURSOR;
BEGIN
    OPEN user_cursor FOR SELECT * FROM users WHERE email = p_email;
    RETURN user_cursor;
END;
/

-- Procedure to delete a user
CREATE OR REPLACE PROCEDURE delete_user(p_id NUMBER) AS
BEGIN
    DELETE FROM users WHERE id = p_id;
END;
/

-- Function to list all users
CREATE OR REPLACE FUNCTION list_users RETURN SYS_REFCURSOR AS
    users_cursor SYS_REFCURSOR;
BEGIN
    OPEN users_cursor FOR SELECT * FROM users;
    RETURN users_cursor;
END;
/

-- Procedure to update a user
CREATE OR REPLACE PROCEDURE update_user(
    p_id NUMBER,
    p_name VARCHAR,
    p_email VARCHAR,
    p_password VARCHAR,
    p_birthdate DATE
) AS
BEGIN
    UPDATE users SET name = p_name, email = p_email, password = p_password, birthdate = p_birthdate WHERE id = p_id;
END;
/
