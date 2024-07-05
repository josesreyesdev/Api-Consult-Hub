ALTER TABLE physicians ADD active tinyint;

UPDATE physicians set active = 1;