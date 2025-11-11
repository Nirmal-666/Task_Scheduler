CREATE DATABASE IF NOT EXISTS task_scheduler;
USE task_scheduler;

CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    due_date DATE,
    status VARCHAR(20) DEFAULT 'Pending'
);

INSERT INTO tasks (title, description, due_date, status)
VALUES 
('Study', 'Exam SEMESTER', '2025-11-11', 'Pending'),
('Drink Water', 'Hydration', '2025-11-12', 'Complete'),
('Walking', 'Fitness', '2025-11-13', 'Pending');
