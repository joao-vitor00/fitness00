-- Procedure to add a new activity
CREATE PROCEDURE addActivity(
    IN user_id BIGINT,
    IN activity_type VARCHAR(255),
    IN duration INT,
    IN calories INT,
    IN activity_date DATE,
    IN completed BOOLEAN
)
BEGIN
    INSERT INTO activities (userid, type, duration, calories, date, completed)
    VALUES (user_id, activity_type, duration, calories, activity_date, completed);
END;

-- Procedure to delete an activity by ID
CREATE PROCEDURE deleteActivity(IN activity_id BIGINT)
BEGIN
    DELETE FROM activities WHERE id = activity_id;
END;

-- Procedure to find an activity by ID
CREATE PROCEDURE findActivity(IN activity_id BIGINT)
BEGIN
    SELECT * FROM activities WHERE id = activity_id;
END;

-- Procedure to list all activities
CREATE PROCEDURE listActivities()
BEGIN
    SELECT * FROM activities;
END;

-- Procedure to list activities by user ID
CREATE PROCEDURE listByUser(IN user_id BIGINT)
BEGIN
    SELECT * FROM activities WHERE userId = user_id AND completed = true;
END;

-- Procedure to update an activity
CREATE PROCEDURE updateActivity(
    IN activity_id BIGINT,
    IN activity_type VARCHAR(255),
    IN duration INT,
    IN calories INT,
    IN activity_date DATE,
    IN completed BOOLEAN
)
BEGIN
    UPDATE activities
    SET type = activity_type, duration = duration, calories = calories, date = activity_date, completed = completed
    WHERE id = activity_id;
END;
