DROP TRIGGER IF EXISTS t_before_insert_on_user_info_tbl;
CREATE TRIGGER t_before_insert_on_user_info_tbl
BEFORE INSERT ON user_info_tbl
FOR EACH ROW
  BEGIN
    IF NEW.created_date IS NULL
    THEN
      SET NEW.created_date = sysdate() AND NEW.updated_date = sysdate();
    END IF;
  END;

DROP TRIGGER IF EXISTS t_before_update_on_user_info_tbl;
CREATE TRIGGER t_before_update_on_user_info_tbl
BEFORE UPDATE ON user_info_tbl
FOR EACH ROW
  BEGIN
    IF NEW.updated_date IS NULL
    THEN
      SET NEW.updated_date = sysdate();
    END IF;
  END;