
/**
 * Author:  Flávio Santos
 * Created: 2 de jun. de 2023
 */

CREATE DATABASE gestaocontratos;

CREATE TABLE cliente(
    id bigint NOT NULL AUTO_INCREMENT,
    cpf bigint NOT NULL,
    nome varchar(45) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE contrato(
    id bigint NOT NULL AUTO_INCREMENT,
    redacao mediumtext NOT NULL,
    ultimaAtualizacao date,
    idCliente bigint NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(idCliente) REFERENCES cliente(id)
);