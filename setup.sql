DROP TABLE PERSON;
CREATE TABLE Person (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL DEFAULT '',
  country varchar(20) DEFAULT NULL,
  createdDate Date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
commit;