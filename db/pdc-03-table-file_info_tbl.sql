DROP TABLE IF EXISTS file_info_tbl;
CREATE TABLE file_info_tbl
(
  file_id      VARCHAR(32),
  file_name    VARCHAR(32),
  file_size    VARCHAR(64),
  postfix      VARCHAR(32),
  user_name    VARCHAR(32) REFERENCES user_info_tbl (user_name),
  server_path  VARCHAR(32),
  created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (file_id, user_name)
);

