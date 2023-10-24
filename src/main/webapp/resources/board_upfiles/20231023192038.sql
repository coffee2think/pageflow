-- run -> system // id : c##pageflow // pwd : pageflow
create user c##pageflow IDENTIFIED by pageflow;

grant connect, resource to c##pageflow;
grant create view to c##pageflow;
alter user c##pageflow
quota 1024m on users;