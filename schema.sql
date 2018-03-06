DROP SCHEMA IF EXISTS tasks;
CREATE SCHEMA tasks;
GRANT ALL PRIVILEGES ON tasks.* TO 'taskmaster'@'localhost' IDENTIFIED BY 'password';

USE tasks;

DROP TABLE IF EXISTS task;

CREATE TABLE `task` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(63) NOT NULL,
  `category` VARCHAR(63) NOT NULL,
  `assignee` VARCHAR(63) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `due_date` datetime NOT NULL,
  `completed_date` datetime DEFAULT NULL,
  `completed` tinyint(1) DEFAULT '0',
  PRIMARY KEY(`id`)
);

ALTER TABLE `task` ADD UNIQUE `unique_task`(`name`, `due_date`);

INSERT INTO `task`
  (name, category, assignee, description, due_date, completed_date, completed)
VALUES ('Create Application', 'dev','esaburruss','Create a task tracking application','2018-03-06 10:00:00',NULL,0);
