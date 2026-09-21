CREATE TABLE correntista (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    documento VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(255)
);

CREATE TABLE conta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(255) NOT NULL,
    saldo DECIMAL(19, 2) NOT NULL DEFAULT 0,
    correntista_id BIGINT,
    CONSTRAINT fk_conta_correntista
        FOREIGN KEY (correntista_id)
        REFERENCES correntista(id)
);

CREATE TABLE conta_corrente (
    id BIGINT PRIMARY KEY,
    limite DECIMAL(19, 2) NOT NULL DEFAULT 0,
    CONSTRAINT fk_conta_corrente_conta
        FOREIGN KEY (id)
        REFERENCES conta(id)
);

CREATE TABLE conta_poupanca (
    id BIGINT PRIMARY KEY,
    CONSTRAINT fk_conta_poupanca_conta
        FOREIGN KEY (id)
        REFERENCES conta(id)
);

CREATE TABLE transacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    valor DECIMAL(19, 2) NOT NULL,
    data TIMESTAMP NOT NULL,
    conta_id BIGINT,
    CONSTRAINT fk_transacao_conta
        FOREIGN KEY (conta_id)
        REFERENCES conta(id)
);