drop table IF EXISTS locations;
drop table IF EXISTS latitude;
drop table IF EXISTS longitude;

create TABLE latitude (
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    marker_latitude VARCHAR(1) NOT NULL,
    degree INT NOT NULL,
    arcminute INT NOT NULL
);

create TABLE longitude (
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    marker_longitude VARCHAR(1) NOT NULL,
    degree INT NOT NULL,
    arcminute INT NOT NULL

);

create TABLE locations (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  location_name VARCHAR(250) NOT NULL,
  latitude_id int(11) NOT NULL,
  longitude_id int(11) NOT NULL,
  KEY FK_LATITUDE_idx (latitude_id),
  CONSTRAINT FK_LATITUDE FOREIGN KEY (latitude_id) REFERENCES latitude (id),
  KEY FK_LONGITUDE_idx (longitude_id),
  CONSTRAINT FK_LONGITUDE FOREIGN KEY (longitude_id) REFERENCES longitude (id)
);

