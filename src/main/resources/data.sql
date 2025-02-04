--
-- -- Insert data into users_info table
-- INSERT INTO users_info (user_id, birthdate, email, first_name, last_name, password, user_name, roles)
-- VALUES
--     (1, '1990-01-01 00:00:00', 'john.doe@gmail.com', 'John', 'Doe', 'Password123!', 'johndoe', 'ADMIN_ROLE'),
--     (2, '1992-05-15 00:00:00', 'jane.smith@example.com', 'Jane', 'Smith', 'SecurePass456@', 'janesmith', 'USER_ROLE'),
--     (3, '1988-11-25 00:00:00', 'alex.brown@yahoo.com', 'Alex', 'Brown', 'MyPassword789!', 'alexbrown', 'USER_ROLE');
-- Insert test users with BCrypt-hashed passwords
INSERT INTO users_info ( birthdate, email, first_name, last_name, password, user_name, roles)
VALUES
    ( '1990-01-01 00:00:00', 'admin@example.com', 'Admin', 'User',
     '$2a$10$TnCJlRq/JM7rTjc1FUpX9Ofyz/Hp5MOhQ38HOUZpgcR7jAOz6Ww2W', 'adminuser', 'ROLE_ADMIN'),

    ( '1992-05-15 00:00:00', 'user@example.com', 'Regular', 'User',
     '$2a$10$AftCm2wGfWo2boAz3zRbQOwGzXPVwvLgq1mXskXY9J2/d9pDPTqFG', 'regularuser', 'ROLE_USER');

-- adminuser → Password: admin123
-- regularuser → Password: user123
--
-- -- Insert data into post table
-- INSERT INTO post (post_id, user_id, description)
-- VALUES
--     (1, 1, 'This is a post by John Doe.'),
--     (2, 2, 'This is a post by Jane Smith.'),
--


