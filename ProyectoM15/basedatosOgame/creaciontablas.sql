alter session set "_ORACLE_SCRIPT"=true;
create user ogame identified by ogame;
GRANT RESOURCE TO ogame;
grant create session to ogame;
grant unlimited tablespace to ogame;
grant dba to ogame;
grant create view to ogame;