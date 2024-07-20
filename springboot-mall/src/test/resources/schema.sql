
CREATE TABLE IF NOT EXISTS product
(
    product_id         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_name       VARCHAR(128)  NOT NULL,
    category           VARCHAR(32)  NOT NULL,
    image_url          VARCHAR(256) NOT NULL,
    price              INT          NOT NULL,
    stock              INT          NOT NULL,
    description        VARCHAR(1024),
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL
);

----------------- H2 資料庫用
CREATE TABLE IF NOT EXISTS user
(
    user_id            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email              VARCHAR(256) NOT NULL UNIQUE,
    password           VARCHAR(256) NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS AngularToDoUser
(
    user_id            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username           VARCHAR(30),
    email              VARCHAR(256)  UNIQUE ,
    password           VARCHAR(256) ,
    created_date       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP ,
    last_modified_date TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ,
    roles              VARCHAR(30) ,
    accessToken        VARCHAR(256)

);
--
--
CREATE TABLE IF NOT EXISTS AngularToDo
(
    note_id            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title              VARCHAR(256) ,
    description        VARCHAR(256)  ,
    created_date       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);



--
--DROP TABLE AngularToDo;
--DROP TABLE AngularToDoUser;





