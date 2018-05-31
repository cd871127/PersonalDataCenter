DROP TABLE IF EXISTS user_info_tbl;
CREATE TABLE user_info_tbl
(
  user_name    VARCHAR(64)  NOT NULL PRIMARY KEY,
  nick_name    VARCHAR(64)  ,
  pass_word    VARCHAR(512) NOT NULL,
  user_type    VARCHAR(1)   NOT NULL       DEFAULT '1', #1普通用户 #0管理员用户
  tel_phone    VARCHAR(11),
  e_mail       VARCHAR(128),
  id_card      VARCHAR(18),
  created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

