DROP TABLE IF EXISTS user_info_his_tbl;
CREATE TABLE user_info_his_tbl
(
  user_name    VARCHAR(64),
  nick_name    VARCHAR(64),
  pass_word    VARCHAR(512),
  user_type    VARCHAR(1),
  tel_phone    VARCHAR(11),
  e_mail       VARCHAR(128),
  id_card      VARCHAR(18),
  created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

