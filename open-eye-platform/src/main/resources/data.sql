
-- Insert data into users_info table
INSERT INTO users_info (user_id, birthdate, email, first_name, last_name, password, user_name, user_role)
VALUES
    (1, '1990-01-01 00:00:00', 'john.doe@gmail.com', 'John', 'Doe', 'Password123!', 'johndoe', 'ADMIN_ROLE'),
    (2, '1992-05-15 00:00:00', 'jane.smith@example.com', 'Jane', 'Smith', 'SecurePass456@', 'janesmith', 'USER_ROLE'),
    (3, '1988-11-25 00:00:00', 'alex.brown@yahoo.com', 'Alex', 'Brown', 'MyPassword789!', 'alexbrown', 'USER_ROLE');

-- Insert data into post table
INSERT INTO post (post_id, user_id, description)
VALUES
    (1, 1, 'This is a post by John Doe.'),
    (2, 2, 'This is a post by Jane Smith.'),
    (3, 3, 'This is a post by Alex Brown.');
    (4, 4, 'This is a post by Alexa Brownie.');

