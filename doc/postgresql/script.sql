create table suiot01.iottente (id_obj int8 not null, datainserimento timestamp, datamodifica timestamp, utenteinserimento varchar(100) not null, utentemodifica varchar(100), version int4, codicefiscale varchar(255) not null, email varchar(255) not null, email_pec varchar(255) not null, nome_dipartimento varchar(255) not null, nome_ente varchar(255) not null, token varchar(255), primary key (id_obj));
create table suiot01.iottmessage (id_obj int8 not null, datainserimento timestamp, datamodifica timestamp, utenteinserimento varchar(100) not null, utentemodifica varchar(100), version int4, codicefiscale varchar(255) not null, email varchar(255), idexternal varchar(255), oggetto varchar(120) not null, scadenza timestamp, testo varchar(10000) not null, timetolive int4 not null, tipomessaggio varchar(255) not null, primary key (id_obj));
create table suiot01.iottnotification (id_obj int8 not null, datainserimento timestamp, email_notification varchar(255), ultimotentativo timestamp, note varchar(10000), status varchar(255), webhood_notification varchar(255), idmessage int8, primary key (id_obj))alter table if exists suiot01.iottente drop constraint if exists UK_ol3l41767nqsqr0umlf7aoxpf;
alter table if exists suiot01.iottente add constraint UK_ol3l41767nqsqr0umlf7aoxpf unique (codicefiscale);
alter table if exists suiot01.iottente drop constraint if exists UK_abjui0pv0i2tj30dx6mc11crm;
alter table if exists suiot01.iottente add constraint UK_abjui0pv0i2tj30dx6mc11crm unique (nome_ente);
alter table if exists suiot01.iottmessage drop constraint if exists UKe9bout7ealn5kqr5v1qhrmpm1;
alter table if exists suiot01.iottmessage add constraint UKe9bout7ealn5kqr5v1qhrmpm1 unique (codicefiscale, id_obj);
create sequence suiot01.nxtnbr start 1 increment 1;
alter table if exists suiot01.iottnotification add constraint FKq02nchprl755gusa0ctt0u82d foreign key (idmessage) references suiot01.iottmessage;

