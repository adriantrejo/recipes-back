DROP TABLE IF EXISTS recipes;

CREATE TABLE IF NOT EXISTS recipes (
                                       id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                       title VARCHAR(100) NOT NULL,
                                       making_time VARCHAR(100) NOT NULL,
                                       serves VARCHAR(100) NOT NULL,
                                       ingredients VARCHAR(300) NOT NULL,
                                       cost INTEGER NOT NULL,
                                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);