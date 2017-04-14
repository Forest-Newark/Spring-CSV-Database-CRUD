--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: csvdemo; Type: DATABASE; Schema: -; Owner: forestnewark
--

CREATE DATABASE csvdemo WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE csvdemo OWNER TO forestnewark;

\connect csvdemo

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: composition; Type: TABLE; Schema: public; Owner: forestnewark
--

CREATE TABLE composition (
    compositionid integer NOT NULL,
    libnum character varying(20) NOT NULL,
    title character varying(50) NOT NULL,
    composer character varying(50),
    arranger character varying(50),
    copyright character varying(20),
    ensemble character varying(50),
    notes text,
    catagory character varying(20) NOT NULL
);


ALTER TABLE composition OWNER TO forestnewark;

--
-- Name: composition_compositionid_seq; Type: SEQUENCE; Schema: public; Owner: forestnewark
--

CREATE SEQUENCE composition_compositionid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE composition_compositionid_seq OWNER TO forestnewark;

--
-- Name: composition_compositionid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: forestnewark
--

ALTER SEQUENCE composition_compositionid_seq OWNED BY composition.compositionid;


--
-- Name: composition compositionid; Type: DEFAULT; Schema: public; Owner: forestnewark
--

ALTER TABLE ONLY composition ALTER COLUMN compositionid SET DEFAULT nextval('composition_compositionid_seq'::regclass);


--
-- Data for Name: composition; Type: TABLE DATA; Schema: public; Owner: forestnewark
--

COPY composition (compositionid, libnum, title, composer, arranger, copyright, ensemble, notes, catagory) FROM stdin;
\.


--
-- Name: composition_compositionid_seq; Type: SEQUENCE SET; Schema: public; Owner: forestnewark
--

SELECT pg_catalog.setval('composition_compositionid_seq', 23, true);


--
-- Name: composition composition_pkey; Type: CONSTRAINT; Schema: public; Owner: forestnewark
--

ALTER TABLE ONLY composition
    ADD CONSTRAINT composition_pkey PRIMARY KEY (compositionid);


--
-- Name: composition_compositionID_uindex; Type: INDEX; Schema: public; Owner: forestnewark
--

CREATE UNIQUE INDEX "composition_compositionID_uindex" ON composition USING btree (compositionid);


--
-- PostgreSQL database dump complete
--

