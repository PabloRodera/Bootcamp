CREATE TABLE skill (
 id BIGINT AUTO_INCREMENT,
 nombre VARCHAR(128),
 PRIMARY KEY (id)
);
CREATE TABLE idioma (
  id BIGINT AUTO_INCREMENT,
  nombre VARCHAR(128),
  PRIMARY KEY (id)
);
CREATE TABLE candidato (
  id BIGINT AUTO_INCREMENT,
  nombre VARCHAR(128),
  apellidos VARCHAR(256),
  avatar VARCHAR(512),
  PRIMARY KEY (id)
);
CREATE TABLE candidato_skill (
  id_candidato BIGINT,
  id_skill BIGINT,
  nivel VARCHAR(128),
  PRIMARY KEY (id_candidato, id_skill),
  FOREIGN KEY (id_candidato) REFERENCES candidato(id) ON DELETE CASCADE,
  FOREIGN KEY (id_skill) REFERENCES skill(id) ON DELETE CASCADE
);
CREATE TABLE candidato_idioma (
  id_candidato BIGINT,
  id_idioma BIGINT,
  nivel VARCHAR(128),
  PRIMARY KEY (id_candidato, id_idioma),
  FOREIGN KEY (id_candidato) REFERENCES candidato(id) ON DELETE CASCADE,
  FOREIGN KEY (id_idioma) REFERENCES idioma(id) ON DELETE CASCADE
);
CREATE TABLE educacion (
  id BIGINT AUTO_INCREMENT,
  id_candidato BIGINT,
  titulo VARCHAR(256),
  institucion VARCHAR(256),
  descripcion VARCHAR(512),
  fecha_inicio DATE,
  fecha_fin DATE,
  PRIMARY KEY (id),
  FOREIGN KEY (id_candidato) REFERENCES candidato(id) ON DELETE CASCADE
);
CREATE TABLE recomendacion (
  id BIGINT AUTO_INCREMENT,
  id_candidato BIGINT,
  nombre_recomendador VARCHAR(128),
  texto_recomendacion TEXT,
  PRIMARY KEY (id),
  FOREIGN KEY (id_candidato) REFERENCES candidato(id) ON DELETE CASCADE
);