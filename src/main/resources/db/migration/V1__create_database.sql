CREATE TABLE `users` (
                         id CHAR(36) PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         token VARCHAR(255) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `phones` (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id CHAR(36) NOT NULL,
                          number VARCHAR(20) NOT NULL,
                          citycode VARCHAR(20) NOT NULL,
                          contrycode VARCHAR(20) NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES `users`(id) ON DELETE CASCADE
);