drop table  if exists filme;
create table filme(
    id integer primary key,
    nome varchar(100),
    anoLancamento varchar(4),
    dataHoraInclusao Timestamp,
    dataHoraAtualizacao Timestamp
);

drop table if exists casting;
create table casting(
    id serial primary key,
    ator varchar(100),
    personagem varchar(100),
    dataHoraInclusao Timestamp,
    dataHoraAtualizacao Timestamp,
    idfilme int,
    FOREIGN KEY (idfilme) REFERENCES filme (id)
);


