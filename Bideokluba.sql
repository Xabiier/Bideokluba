CREATE TABLE BAZKIDE(
  Kodea VARCHAR(15) NOT NULL,
  Pasahitza VARCHAR(15) NOT NULL,
  Izena VARCHAR(64) DEFAULT '',
  Abizena VARCHAR(64) DEFAULT '',
  Helbidea VARCHAR(15) DEFAULT '',
  Kreditua INT DEFAULT 0 CHECK(Kreditua BETWEEN 0 and 9999),
  Egoera VARCHAR(15) DEFAULT 'alta',
  Noiztik DATETIME,
  PRIMARY KEY(Kodea)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE PELIKULA(
  Kodea VARCHAR(15) NOT NULL,
  Izena VARCHAR(64) DEFAULT '',
  Prezioa TINYINT(1) DEFAULT 0,
  Egoera VARCHAR(15) DEFAULT 'libre',
  Sartze_data DATETIME,
  PRIMARY KEY(Kodea)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE ALOKAIRUAK(
  Bazkide_kodea VARCHAR(15) NOT NULL,
  Pelikula_kodea VARCHAR(15) NOT NULL,
  Hartze_data DATETIME,
  Itzultze_data DATETIME,
  PRIMARY KEY(Bazkide_kodea, Pelikula_kodea, Hartze_data),
  FOREIGN KEY(Bazkide_kodea) REFERENCES BAZKIDE(Kodea) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(Pelikula_kodea) REFERENCES PELIKULA(Kodea) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
