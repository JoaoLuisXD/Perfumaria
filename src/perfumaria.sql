CREATE TABLE marca (
    cnpj VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    origem VARCHAR(60),
    anoCriacao INT
);

CREATE TABLE cliente (
    cpf VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    endereco VARCHAR(200),
    telefone VARCHAR(20)
);

CREATE TABLE revendedor (
    cpf VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    salario DECIMAL(10,2),
    telefone ARCHAR(20)
);

CREATE TABLE perfume (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10,2),
    cnpj_marca  VARCHAR(20),
    FOREIGN KEY (cnpj_marca) REFERENCES marca(cnpj)
);

CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    valor DECIMAL(10,2) NOT NULL,
    comissao DECIMAL(10,2),
    cpfCliente VARCHAR(20),
    cpfRevendedor VARCHAR(20),
    FOREIGN KEY (cpfCliente) REFERENCES cliente(cpf),
    FOREIGN KEY (cpfRevendedor) REFERENCES revendedor(cpf)
);

CREATE TABLE inclui (
    idPedido  INT,
    idPerfume INT,
    qtd_itens INT NOT NULL,
    data DATE,
    PRIMARY KEY (idPedido, idPerfume),
    FOREIGN KEY (idPedido) REFERENCES pedido(id),
    FOREIGN KEY (idPerfume) REFERENCES perfume(id)
);

CREATE TABLE entrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    endereco VARCHAR(200) NOT NULL,
    valorEntrega DECIMAL(10,2) NOT NULL,
    data DATE,
    status VARCHAR(20),
    tipo VARCHAR(20) NOT NULL
    idPedido  INT
    FOREIGN KEY (idPerfume) REFERENCES perfume(id)
);

