CREATE TABLE BAZKIDE(
  Kodea VARCHAR(15) NOT NULL,
  Pasahitza VARCHAR(15),
  Izena VARCHAR(15),
  Abizena VARCHAR(15),
  Helbidea VARCHAR(15),
  Kreditua SMALLINT,
  Egoera VARCHAR(15),
  Noiztik DATE,
  PRIMARY KEY(Kodea)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE PELIKULA(
  Kodea VARCHAR(15) NOT NULL,
  Izena VARCHAR(15),
  Prezioa TINYINT,
  Egoera VARCHAR(15),
  Sartze_data DATE,
  Hartze_data DATE,
  Itzultze_data DATE,
  Bazkide_kodea VARCHAR(15),
  PRIMARY KEY(Kodea, Hartze_data, Itzultze_data, Bazkide_kodea),
  FOREIGN KEY(Bazkide_kodea) REFERENCES BAZKIDE(Kodea)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
