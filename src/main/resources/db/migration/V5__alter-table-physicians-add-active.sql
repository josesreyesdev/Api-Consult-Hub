ALTER TABLE physicians ADD COLUMN active TINYINT;

UPDATE physicians SET active = 1;

ALTER TABLE physicians MODIFY active TINYINT NOT NULL ;