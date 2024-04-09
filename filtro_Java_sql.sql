Create database empresas;
use empresas;

CREATE TABLE coder(
	idCoder INT primary key auto_increment,
    nombre VARCHAR(30),
    apellido VARCHAR(30),
    documento VARCHAR(30),
    cohorte INT, 
    cv text
);

CREATE TABLE empresa(
	idEmpresa INT primary key auto_increment,
    nombre VARCHAR(30),
    sector VARCHAR(40),
    ubicacion VARCHAR(40),
    contacto VARCHAR(40)
);

CREATE TABLE vacante(
	idVacante INT primary key auto_increment,
    titulo VARCHAR(40),
    descripcion TEXT,
    duracion VARCHAR(40),
    estado VARCHAR(40),
    idEmpresa INT NOT NULL, 
    constraint fk_vacante_empresa foreign key(idEmpresa) references empresa(idEmpresa) ON DELETE CASCADE
);

CREATE TABLE contratacion(
	idContratacion INT primary key auto_increment,
    fecha_aplicacion DATE,
    estado VARCHAR(40),
    salario decimal(10),
    idVacante INT NOT NULL,
    constraint fk_contratacion_vacante foreign key(idVacante) references vacante(idVacante) ON DELETE CASCADE,
    idCoder INT NOT NULL,
    constraint fk_cantatracion_coder foreign key(idCoder) references coder(idCoder) ON DELETE CASCADE
);

