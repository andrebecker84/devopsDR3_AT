
set client_min_messages = WARNING;
alter table if exists relatos drop constraint if exists FK2aakagaswrd5gychylgk0ps3r;

drop table if exists avistamentos cascade;
drop table if exists casos_ufo cascade;
drop table if exists relatos cascade;

create table avistamentos (
                              confiabilidade integer,
                              latitude numeric(9,6) not null,
                              longitude numeric(9,6) not null,
                              datahora timestamp(6) with time zone not null,
                              id uuid not null,
                              city varchar(20) not null,
                              descricao varchar(255),
                              primary key (id)
);

create table casos_ufo (
                           data_fim timestamp(6) with time zone,
                           data_inicio timestamp(6) with time zone not null,
                           id uuid not null,
                           status varchar(20) not null check (status in ('ABERTO','EM_ANALISE','ENCERRADO')),
                           cidade varchar(60) not null,
                           primary key (id)
);

create table relatos (
                         confiabilidade integer not null,
                         latitude numeric(9,6) not null,
                         longitude numeric(9,6) not null,
                         data_hora timestamp(6) with time zone not null,
                         caso_id uuid not null,
                         id uuid not null,
                         testemunha varchar(80) not null,
                         descricao text,
                         primary key (id)
);

create index idx_caso_data_inicio on casos_ufo (data_inicio);
create index idx_caso_cidade on casos_ufo (cidade);
create index idx_relato_datahora on relatos (data_hora);

alter table if exists relatos
    add constraint FK2aakagaswrd5gychylgk0ps3r
    foreign key (caso_id)
    references casos_ufo;