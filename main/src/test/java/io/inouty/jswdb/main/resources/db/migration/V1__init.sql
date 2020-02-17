-- SCHEMAS
CREATE SCHEMA IF NOT EXISTS public;
CREATE SCHEMA IF NOT EXISTS works;
CREATE SCHEMA IF NOT EXISTS BATCH;

-- SEQUENCES
CREATE SEQUENCE IF NOT EXISTS works.characters_id_seq
    INCREMENT 1 MINVALUE 1
    MAXVALUE 9223372036854775807 START 1
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS works.genres_id_seq
    INCREMENT 1 MINVALUE 1
    MAXVALUE 9223372036854775807 START 1
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS works.release_info_id_seq
    INCREMENT 1 MINVALUE 1
    MAXVALUE 9223372036854775807 START 1
    CACHE 1;


-- TABLES
CREATE TABLE IF NOT EXISTS works.actors
(
    id        VARCHAR(20) NOT NULL,
    full_name VARCHAR(60) NOT NULL,
    CONSTRAINT actors_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS works.writers
(
    id        VARCHAR(20) NOT NULL,
    full_name VARCHAR(60) NOT NULL,
    CONSTRAINT writers_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.countries
(
    code VARCHAR(4)  NOT NULL,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT countries_name_key UNIQUE (name),
    CONSTRAINT countries_pkey PRIMARY KEY (code)
);

/* Data for the 'works.countries' table  (Records 1 - 136) */
DELETE FROM public.countries;
INSERT INTO public.countries (code, name)
VALUES (E'kr', E'South Korea'),
       (E'kz', E'Kazakhstan'),
       (E'hr', E'Croatia'),
       (E'lu', E'Luxembourg'),
       (E'pl', E'Poland'),
       (E'co', E'Colombia'),
       (E'at', E'Austria'),
       (E'kh', E'Cambodia'),
       (E'tn', E'Tunisia'),
       (E'ro', E'Romania'),
       (E'tg', E'Togo'),
       (E'it', E'Italy'),
       (E'do', E'Dominican Republic'),
       (E'rs', E'Serbia'),
       (E'pk', E'Pakistan'),
       (E'bd', E'Bangladesh'),
       (E'nl', E'Netherlands'),
       (E'th', E'Thailand'),
       (E'bn', E'Brunei'),
       (E'us', E'USA'),
       (E'cl', E'Chile'),
       (E'lt', E'Lithuania'),
       (E'sn', E'Senegal'),
       (E'ph', E'Philippines'),
       (E'ge', E'Georgia'),
       (E'ao', E'Angola'),
       (E'bs', E'Bahamas'),
       (E'om', E'Oman'),
       (E'is', E'Iceland'),
       (E'mx', E'Mexico'),
       (E'am', E'Armenia'),
       (E'iq', E'Iraq'),
       (E'dz', E'Algeria'),
       (E'il', E'Israel'),
       (E'jp', E'Japan'),
       (E'pa', E'Panama'),
       (E'al', E'Albania'),
       (E'de', E'Germany'),
       (E'bw', E'Botswana'),
       (E'cr', E'Costa Rica'),
       (E'ma', E'Morocco'),
       (E'id', E'Indonesia'),
       (E'ad', E'Andorra'),
       (E'br', E'Brazil'),
       (E'lb', E'Lebanon'),
       (E'ir', E'Iran'),
       (E'ci', E'Côte d''Ivoire'),
       (E'vn', E'Vietnam'),
       (E'ng', E'Nigeria'),
       (E'ke', E'Kenya'),
       (E'pg', E'Papua New Guinea'),
       (E'bo', E'Bolivia'),
       (E'rw', E'Rwanda'),
       (E'fr', E'France'),
       (E'hu', E'Hungary'),
       (E'my', E'Malaysia'),
       (E'by', E'Belarus'),
       (E'kw', E'Kuwait'),
       (E'bg', E'Bulgaria'),
       (E'ca', E'Canada'),
       (E'nz', E'New Zealand'),
       (E'au', E'Australia'),
       (E'es', E'Spain'),
       (E'fj', E'Fiji'),
       (E'af', E'Afghanistan'),
       (E'ru', E'Russia'),
       (E'sk', E'Slovakia'),
       (E'uy', E'Uruguay'),
       (E'tr', E'Turkey'),
       (E'cm', E'Cameroon'),
       (E'be', E'Belgium'),
       (E'ni', E'Nicaragua'),
       (E'ug', E'Uganda'),
       (E'sa', E'Saudi Arabia'),
       (E'py', E'Paraguay'),
       (E'ua', E'Ukraine'),
       (E'eg', E'Egypt'),
       (E'sv', E'El Salvador'),
       (E'hk', E'Hong Kong'),
       (E'mg', E'Madagascar'),
       (E'ch', E'Switzerland'),
       (E'si', E'Slovenia'),
       (E'xkv', E'Kosovo'),
       (E'hn', E'Honduras'),
       (E'dk', E'Denmark'),
       (E'ee', E'Estonia'),
       (E'az', E'Azerbaijan'),
       (E'ba', E'Bosnia and Herzegovina'),
       (E'fi', E'Finland'),
       (E'pt', E'Portugal'),
       (E'gt', E'Guatemala'),
       (E'np', E'Nepal'),
       (E'gb', E'UK'),
       (E'pe', E'Peru'),
       (E'ar', E'Argentina'),
       (E'ae', E'United Arab Emirates'),
       (E'sg', E'Singapore'),
       (E'lk', E'Sri Lanka'),
       (E'gr', E'Greece'),
       (E'lv', E'Latvia'),
       (E'no', E'Norway'),
       (E'mk', E'Republic of Macedonia'),
       (E'cn', E'China'),
       (E'ec', E'Ecuador'),
       (E'bt', E'Bhutan'),
       (E'bh', E'Bahrain'),
       (E've', E'Venezuela'),
       (E'gy', E'Guyana'),
       (E'cz', E'Czech Republic'),
       (E'qa', E'Qatar'),
       (E'tt', E'Trinidad and Tobago'),
       (E'tz', E'Tanzania'),
       (E'et', E'Ethiopia'),
       (E'se', E'Sweden'),
       (E'za', E'South Africa'),
       (E'tw', E'Taiwan'),
       (E'in', E'India'),
       (E'ie', E'Ireland'),
       (E'jo', E'Jordan'),
       (E'aw', E'Aruba'),
       (E'pr', E'Puerto Rico'),
       (E'cy', E'Cyprus'),
       (E'me', E'Montenegro'),
       (E'mv', E'Maldives'),
       (E'sr', E'Suriname'),
       (E'mo', E'Macao'),
       (E'mm', E'Myanmar'),
       (E'jm', E'Jamaica'),
       (E'sy', E'Syria'),
       (E'vg', E'British Virgin Islands'),
       (E'mt', E'Malta'),
       (E'mn', E'Mongolia'),
       (E'tl', E'Timor-Leste'),
       (E'vi', E'U.S. Virgin Islands'),
       (E'gd', E'Grenada'),
       (E'la', E'Laos');

CREATE TABLE IF NOT EXISTS works.genreEntities
(
    id   BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT genres_pkey PRIMARY KEY (id),
    CONSTRAINT genres_uk UNIQUE (name)
);

DELETE FROM works.genreEntities;
INSERT INTO works.genreEntities (id, name)
VALUES (1, E'Action'),
       (2, E'Adventure'),
       (3, E'Animation'),
       (4, E'Biography'),
       (5, E'Comedy'),
       (6, E'Crime'),
       (7, E'Documentary'),
       (8, E'Drama'),
       (9, E'Family'),
       (10, E'Fantasy'),
       (11, E'Film-Noir'),
       (12, E'Game-Show'),
       (13, E'History'),
       (14, E'Horror'),
       (15, E'Music'),
       (16, E'Musical'),
       (17, E'Mystery'),
       (18, E'News'),
       (19, E'Reality-TV'),
       (20, E'Romance'),
       (21, E'Sci-Fi'),
       (22, E'Sport'),
       (23, E'Talk-Show'),
       (24, E'Thriller'),
       (25, E'War'),
       (26, E'Western');

CREATE TABLE IF NOT EXISTS works.characters
(
    id      BIGSERIAL,
    imdb_id VARCHAR(20),
    name    VARCHAR(120) NOT NULL,
    CONSTRAINT characters_pkey PRIMARY KEY (id),
    CONSTRAINT characters_uk UNIQUE (imdb_id, name)
);

CREATE TABLE IF NOT EXISTS works.countries
(
    code VARCHAR(4)  NOT NULL,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT countries_name_key UNIQUE (name),
    CONSTRAINT countries_pkey PRIMARY KEY (code)
);

CREATE TABLE IF NOT EXISTS works.directors
(
    id        VARCHAR(20) NOT NULL,
    full_name VARCHAR(60) NOT NULL,
    CONSTRAINT directors_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS works.genreEntities
(
    id   BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT genres_pkey PRIMARY KEY (id),
    CONSTRAINT uk_pe1a9woik1k97l87cieguyhh4 UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS works.movies
(
    id           VARCHAR(20)  NOT NULL,
    certificate  VARCHAR(10),
    duration     TIME WITHOUT TIME ZONE,
    metascore    INTEGER,
    profile_img  VARCHAR(255),
    rating_avg   DOUBLE PRECISION,
    rating_count BIGINT,
    summary      VARCHAR(3000),
    title        VARCHAR(255) NOT NULL,
    trailer_url  VARCHAR(255),
    year         INTEGER,
    CONSTRAINT movies_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS works.movies_actors_characters
(
    actor_id     VARCHAR(20) NOT NULL,
    character_id BIGINT      NOT NULL,
    movie_id     VARCHAR(20) NOT NULL,
    CONSTRAINT movies_actors_characters_pkey PRIMARY KEY (actor_id, character_id, movie_id),
    CONSTRAINT fknewyde9eudg611ir36rtvl1en FOREIGN KEY (actor_id)
        REFERENCES works.actors (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE,
    CONSTRAINT fkt2oj4jrontci8y7s909u7e3op FOREIGN KEY (character_id)
        REFERENCES works.characters (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE,
    CONSTRAINT fktoab8qdehgmyfej8jvthpwuyv FOREIGN KEY (movie_id)
        REFERENCES works.movies (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE
);

CREATE TABLE IF NOT EXISTS works.movies_directors
(
    director_id VARCHAR(20) NOT NULL,
    movie_id    VARCHAR(20) NOT NULL,
    CONSTRAINT movies_directors_pkey PRIMARY KEY (director_id, movie_id),
    CONSTRAINT fkbtmuusnf9w4db2p998xls2ci8 FOREIGN KEY (director_id)
        REFERENCES works.directors (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE,
    CONSTRAINT fkco6yamau93db0bwxv6uneg1d7 FOREIGN KEY (movie_id)
        REFERENCES works.movies (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE
);

CREATE TABLE IF NOT EXISTS works.movies_genres
(
    genre_id BIGINT      NOT NULL,
    movie_id VARCHAR(20) NOT NULL,
    CONSTRAINT movies_genres_pkey PRIMARY KEY (genre_id, movie_id),
    CONSTRAINT fkk0w7fx0a7flrtafppanyup87d FOREIGN KEY (movie_id)
        REFERENCES works.movies (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE,
    CONSTRAINT fkrs5u5iygsuht2f0cag9b9h1ob FOREIGN KEY (genre_id)
        REFERENCES works.genreEntities (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE
);


CREATE TABLE IF NOT EXISTS works.movies_writers
(
    credits   VARCHAR(120),
    movie_id  VARCHAR(20) NOT NULL,
    writer_id VARCHAR(20) NOT NULL,
    CONSTRAINT movies_writers_pkey PRIMARY KEY (movie_id, writer_id),
    CONSTRAINT fk1ag22okf8yj0onkl96jmwi9dw FOREIGN KEY (writer_id)
        REFERENCES works.writers (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE,
    CONSTRAINT fkjoqjymreytm8b4ivqifbge0ka FOREIGN KEY (movie_id)
        REFERENCES works.movies (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE
);

CREATE TABLE IF NOT EXISTS works.release_info
(
    id           BIGSERIAL,
    country_code VARCHAR(20) NOT NULL,
    country_name VARCHAR(40) NOT NULL,
    note         VARCHAR(255),
    release_date DATE        NOT NULL,
    movie_id     VARCHAR(20) NOT NULL,
    CONSTRAINT release_info_pkey PRIMARY KEY (id),
    CONSTRAINT release_info_uk UNIQUE (movie_id, country_code, note, release_date),
    CONSTRAINT fkhjmqdk2b5yu76d3jy6dlcqukw FOREIGN KEY (movie_id)
        REFERENCES works.movies (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE
);

-- INDICES

/*ALTER TABLE works.actors
    ADD CONSTRAINT actors_pkey
        PRIMARY KEY (id) NOT DEFERRABLE;
ALTER TABLE works.characters
    ADD CONSTRAINT characters_pkey
        PRIMARY KEY (id) NOT DEFERRABLE;
ALTER TABLE works.characters
    ADD CONSTRAINT characters_uk
        UNIQUE (imdb_id, name) NOT DEFERRABLE;
ALTER TABLE works.countries
    ADD CONSTRAINT countries_name_key
        UNIQUE (name) NOT DEFERRABLE;
ALTER TABLE works.countries
    ADD CONSTRAINT countries_pkey
        PRIMARY KEY (code) NOT DEFERRABLE;
ALTER TABLE works.directors
    ADD CONSTRAINT directors_pkey
        PRIMARY KEY (id) NOT DEFERRABLE;
ALTER TABLE works.genreEntities
    ADD CONSTRAINT genres_pkey
        PRIMARY KEY (id) NOT DEFERRABLE;
ALTER TABLE works.movies_actors_characters
    ADD CONSTRAINT movies_actors_characters_pkey
        PRIMARY KEY (actor_id, character_id, movie_id) NOT DEFERRABLE;
ALTER TABLE works.movies_directors
    ADD CONSTRAINT movies_directors_pkey
        PRIMARY KEY (director_id, movie_id) NOT DEFERRABLE;
ALTER TABLE works.movies_genres
    ADD CONSTRAINT movies_genres_pkey
        PRIMARY KEY (genre_id, movie_id) NOT DEFERRABLE;
ALTER TABLE works.movies
    ADD CONSTRAINT movies_pkey
        PRIMARY KEY (id) NOT DEFERRABLE;
ALTER TABLE works.movies_writers
    ADD CONSTRAINT movies_writers_pkey
        PRIMARY KEY (movie_id, writer_id) NOT DEFERRABLE;
ALTER TABLE works.release_info
    ADD CONSTRAINT release_info_pkey
        PRIMARY KEY (id) NOT DEFERRABLE;
ALTER TABLE works.release_info
    ADD CONSTRAINT release_info_uk
        UNIQUE (movie_id, country_code, note, release_date) NOT DEFERRABLE;
ALTER TABLE works.genreEntities
    ADD CONSTRAINT genres_uk
        UNIQUE (name) NOT DEFERRABLE;
ALTER TABLE works.writers
    ADD CONSTRAINT writers_pkey
        PRIMARY KEY (id) NOT DEFERRABLE;*/


--BATCH

-- Autogenerated: do not edit this file

CREATE TABLE IF NOT EXISTS BATCH.JOB_INSTANCE
(
    JOB_INSTANCE_ID BIGINT       NOT NULL PRIMARY KEY,
    VERSION         BIGINT,
    JOB_NAME        VARCHAR(100) NOT NULL,
    JOB_KEY         VARCHAR(32)  NOT NULL,
    constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
);

CREATE TABLE IF NOT EXISTS BATCH.JOB_EXECUTION
(
    JOB_EXECUTION_ID           BIGINT        NOT NULL PRIMARY KEY,
    VERSION                    BIGINT,
    JOB_INSTANCE_ID            BIGINT        NOT NULL,
    CREATE_TIME                TIMESTAMP     NOT NULL,
    START_TIME                 TIMESTAMP DEFAULT NULL,
    END_TIME                   TIMESTAMP DEFAULT NULL,
    STATUS                     VARCHAR(10),
    EXIT_CODE                  VARCHAR(2500),
    EXIT_MESSAGE               VARCHAR(2500),
    LAST_UPDATED               TIMESTAMP,
    JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
    constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
        references BATCH.JOB_INSTANCE (JOB_INSTANCE_ID)
);

CREATE TABLE IF NOT EXISTS BATCH.JOB_EXECUTION_PARAMS
(
    JOB_EXECUTION_ID BIGINT       NOT NULL,
    TYPE_CD          VARCHAR(6)   NOT NULL,
    KEY_NAME         VARCHAR(100) NOT NULL,
    STRING_VAL       VARCHAR(250),
    DATE_VAL         TIMESTAMP DEFAULT NULL,
    LONG_VAL         BIGINT,
    DOUBLE_VAL       DOUBLE PRECISION,
    IDENTIFYING      CHAR(1)      NOT NULL,
    constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
        references BATCH.JOB_EXECUTION (JOB_EXECUTION_ID)
);

CREATE TABLE IF NOT EXISTS BATCH.STEP_EXECUTION
(
    STEP_EXECUTION_ID  BIGINT       NOT NULL PRIMARY KEY,
    VERSION            BIGINT       NOT NULL,
    STEP_NAME          VARCHAR(100) NOT NULL,
    JOB_EXECUTION_ID   BIGINT       NOT NULL,
    START_TIME         TIMESTAMP    NOT NULL,
    END_TIME           TIMESTAMP DEFAULT NULL,
    STATUS             VARCHAR(10),
    COMMIT_COUNT       BIGINT,
    READ_COUNT         BIGINT,
    FILTER_COUNT       BIGINT,
    WRITE_COUNT        BIGINT,
    READ_SKIP_COUNT    BIGINT,
    WRITE_SKIP_COUNT   BIGINT,
    PROCESS_SKIP_COUNT BIGINT,
    ROLLBACK_COUNT     BIGINT,
    EXIT_CODE          VARCHAR(2500),
    EXIT_MESSAGE       VARCHAR(2500),
    LAST_UPDATED       TIMESTAMP,
    constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
        references BATCH.JOB_EXECUTION (JOB_EXECUTION_ID)
);

CREATE TABLE IF NOT EXISTS BATCH.STEP_EXECUTION_CONTEXT
(
    STEP_EXECUTION_ID  BIGINT        NOT NULL PRIMARY KEY,
    SHORT_CONTEXT      VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT,
    constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
        references BATCH.STEP_EXECUTION (STEP_EXECUTION_ID)
);

CREATE TABLE IF NOT EXISTS BATCH.JOB_EXECUTION_CONTEXT
(
    JOB_EXECUTION_ID   BIGINT        NOT NULL PRIMARY KEY,
    SHORT_CONTEXT      VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT,
    constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
        references BATCH.JOB_EXECUTION (JOB_EXECUTION_ID)
);

CREATE SEQUENCE IF NOT EXISTS BATCH.STEP_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS BATCH.JOB_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE IF NOT EXISTS BATCH.JOB_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
