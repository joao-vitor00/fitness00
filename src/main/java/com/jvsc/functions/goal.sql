-- Procedure to find a goal by ID
CREATE PROCEDURE findGoal(IN goal_id BIGINT)
BEGIN
    SELECT * FROM goals WHERE id = goal_id;
END;

-- Procedure to add a new goal
CREATE PROCEDURE addGoal(
    IN user_id BIGINT,
    IN goal_type VARCHAR(255),
    IN goal_value INT,
    IN goal_period DATE,
    IN start_date DATE
)
BEGIN
    INSERT INTO goals (userid, goaltype, goalvalue, goalperiod, start, completed)
    VALUES (user_id, goal_type, goal_value, goal_period, start_date, false);
END;

-- Procedure to list all goals
CREATE PROCEDURE listGoals()
BEGIN
    SELECT * FROM goals;
END;

-- Procedure to refresh a goal
CREATE PROCEDURE refreshGoal(IN goal_id BIGINT)
BEGIN
    UPDATE goals SET completed = true WHERE id = goal_id;
END;

-- Procedure to delete a goal
CREATE PROCEDURE deleteGoal(IN goal_id BIGINT)
BEGIN
    DELETE FROM goals WHERE id = goal_id;
END;

-- Procedure to update a goal
CREATE PROCEDURE updateGoal(
    IN goal_id BIGINT,
    IN goal_type VARCHAR(255),
    IN goal_value INT,
    IN goal_period DATE
)
BEGIN
    UPDATE goals SET goaltype = goal_type, goalvalue = goal_value, goalperiod = goal_period WHERE id = goal_id;
END;
