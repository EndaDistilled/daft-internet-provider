CREATE SEQUENCE IF NOT EXISTS broadband_packages_seq START WITH 1;

CREATE TABLE IF NOT EXISTS broadband_packages
(
    id                 integer PRIMARY KEY DEFAULT nextval('broadband_packages_seq' :: REGCLASS) not null,
    isp_name           text,
    package_name       text,
    package_speed      text,
    region             text,
    place              text
);

INSERT INTO broadband_packages (isp_name, package_name, package_speed, region, place)
VALUES ('virgin', '250Mb Broadband', '245Mb', 'dublin-1', 'amiens-street');
INSERT INTO broadband_packages (isp_name, package_name, package_speed, region, place)
VALUES ('eir', 'Unlimited broadband solo + eir sport', '100Mb', 'dublin-1', 'amiens-street');
INSERT INTO broadband_packages (isp_name, package_name, package_speed, region, place)
VALUES ('sky', 'Broadband Superfast', '75Mb', 'dublin-1', 'amiens-street');