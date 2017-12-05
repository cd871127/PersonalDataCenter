DROP TABLE IF EXISTS account_info_tbl;
CREATE TABLE account_info_tbl
(
  user_name     VARCHAR(32) REFERENCES user_info_tbl (user_name),
  account_name  VARCHAR(32) NOT NULL,
  account_type  VARCHAR(2)  NOT NULL,
  pass_word     VARCHAR(32),
  description   VARCHAR(32),
  web_site      VARCHAR(32),
  security_mail VARCHAR(32),
  phone         VARCHAR(32),
  created_date  DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_date  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (user_name, account_name)
);

