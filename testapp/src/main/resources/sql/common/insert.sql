INSERT INTO USERS (USER_ID, IISN) VALUES ('tch-user-mgmt', 'IOB');
INSERT INTO USERS (USER_ID, IISN) VALUES ('tch-configuration', 'KVB' );
INSERT INTO USERS (USER_ID, IISN) VALUES ('issuer-mgmt', 'CITI' );
INSERT INTO USERS (USER_ID, IISN) VALUES ('unauthorized-issuer', 'HSBC' );

INSERT INTO ROLES (ID, NAME) VALUES(1, 'ROLE_TCH_USER_MGMT');
INSERT INTO ROLES (ID, NAME) VALUES(2, 'ROLE_TCH_CONFIGURATION');
INSERT INTO ROLES (ID, NAME) VALUES(3, 'ROLE_ISSUER_MGMT');
INSERT INTO ROLES (ID, NAME) VALUES(4, 'UN_AUTHORIZED');

INSERT INTO USERS_ROLES (ID, USER_ID, ROLE_ID) VALUES (1, 1, 1);
INSERT INTO USERS_ROLES (ID, USER_ID, ROLE_ID) VALUES (2, 2, 2);
INSERT INTO USERS_ROLES (ID, USER_ID, ROLE_ID) VALUES (3, 3, 3);
INSERT INTO USERS_ROLES (ID, USER_ID, ROLE_ID) VALUES (4, 4, 4);
