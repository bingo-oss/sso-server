-- oauth2_authz_code
CREATE TABLE IF NOT EXISTS oauth2_authz_code
(
  code VARCHAR(38) PRIMARY KEY,
  client_id VARCHAR(38),
  user_Id VARCHAR(38),
  session_id VARCHAR(38),
  created DATETIME,
  expiration DATETIME,
  ex_data VARCHAR(1000)
);
-- oauth2_access_token
CREATE TABLE IF NOT EXISTS oauth2_access_token
(
  token VARCHAR(38) PRIMARY KEY,
  token_type VARCHAR(20),
  client_id VARCHAR(38),
  user_id VARCHAR(38),
  refresh_token VARCHAR(38),
  scope VARCHAR(1000),
  authenticated BIT,
  created DATETIME,
  expiration DATETIME,
  ex_data VARCHAR(1000)
);
-- oauth2_refresh_token
CREATE TABLE IF NOT EXISTS oauth2_refresh_token
(
  token VARCHAR(38) PRIMARY KEY,
  client_id VARCHAR(38),
  user_id VARCHAR(38),
  scope VARCHAR(1000),
  created DATETIME,
  expiration DATETIME
);
-- oauth2_client
CREATE TABLE IF NOT EXISTS oauth2_client
(
  id VARCHAR(38) PRIMARY KEY,
  secret VARCHAR(38),
  redirect_uri VARCHAR(200),
  redirect_uri_pattern VARCHAR(200),
  logout_uri VARCHAR(200),
  logout_uri_pattern VARCHAR(200),
  at_expires INT DEFAULT 3600,
  rt_expires INT DEFAULT 3600000,
  allow_authz_code BIT DEFAULT TRUE ,
  allow_refresh_token BIT DEFAULT TRUE ,
  allow_login_token BIT DEFAULT TRUE ,
  enabled BIT DEFAULT TRUE ,
  granted_scope VARCHAR(500)
);
-- oauth2_sso_login
CREATE TABLE IF NOT EXISTS oauth2_sso_login
(
  id VARCHAR(38) PRIMARY KEY,
  session_id VARCHAR(38),
  logout_uri VARCHAR(1000),
  client_id VARCHAR(38),
  initial BIT,
  login_time DATETIME,
  expiration DATETIME
);
-- oauth2_sso_session
CREATE TABLE IF NOT EXISTS oauth2_sso_session
(
  id VARCHAR(38) PRIMARY KEY,
  user_id VARCHAR(38),
  user_name VARCHAR(50),
  token VARCHAR(1000),
  created DATETIME,
  expiration DATETIME
);
-- sec_user
CREATE TABLE IF NOT EXISTS sec_user
(
  id VARCHAR(38) PRIMARY KEY,
  name VARCHAR(50),
  login_name VARCHAR(50),
  password VARCHAR(200),
  first_name VARCHAR(10),
  last_name VARCHAR(50)
);
DELETE FROM sec_user WHERE id='1' OR id='2';
INSERT INTO SEC_USER(ID,NAME,PASSWORD,LOGIN_NAME,FIRST_NAME,LAST_NAME) VALUES ('1','管理员','$2a$10$NV0il8rEkWlMbWhgib98a.HL1wU6AfVTYVM0UBp5rTzJeFaqi3qOq','admin','管理','员');
INSERT INTO SEC_USER(ID,NAME,PASSWORD,LOGIN_NAME,FIRST_NAME,LAST_NAME) VALUES ('2','张三','$2a$10$upfY1gHdjUzTmbMGZgMHXOxFPPZihuEGd.bZFPyHmV6qrXpzxvjke','zhangsan','张','三');