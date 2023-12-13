-- public.artist definition

-- Drop table

-- DROP TABLE public.artist;

CREATE TABLE public.artist (
	artist_id uuid NOT NULL,
	description varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT artist_pkey PRIMARY KEY (artist_id)
);

-- public.category definition

-- Drop table

-- DROP TABLE public.category;

CREATE TABLE public.category (
	category_id uuid NOT NULL,
	description varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (category_id)
);

-- public."comments" definition

-- Drop table

-- DROP TABLE public."comments";

CREATE TABLE public."comments" (
	comment_id uuid NOT NULL,
	"comment" varchar(255) NULL,
	deslikes int4 NOT NULL,
	likes int4 NOT NULL,
	title varchar(255) NULL,
	user_creation uuid NULL,
	CONSTRAINT comments_pkey PRIMARY KEY (comment_id)
);

-- public."event" definition

-- Drop table

-- DROP TABLE public."event";

CREATE TABLE public."event" (
	event_id uuid NOT NULL,
	created_at varchar(255) NULL,
	description varchar(255) NULL,
	end_date date NULL,
	"name" varchar(255) NULL,
	payed bool NOT NULL,
	start_date date NULL,
	status bool NOT NULL,
	thumbnail bytea NULL,
	updated_at varchar(255) NULL,
	web_page varchar(255) NULL,
	location_location_id uuid NULL,
	CONSTRAINT event_pkey PRIMARY KEY (event_id)
);


-- public."event" foreign keys

ALTER TABLE public."event" ADD CONSTRAINT fkmlr4pflnagr98wii63vrcjkxv FOREIGN KEY (location_location_id) REFERENCES public."location"(location_id);

-- public.event_artist definition

-- Drop table

-- DROP TABLE public.event_artist;

CREATE TABLE public.event_artist (
	event_event_id uuid NOT NULL,
	artist_artist_id uuid NOT NULL
);


-- public.event_artist foreign keys

ALTER TABLE public.event_artist ADD CONSTRAINT fklig6e2u79tjqg884p1oly8yus FOREIGN KEY (event_event_id) REFERENCES public."event"(event_id);
ALTER TABLE public.event_artist ADD CONSTRAINT fkodas8tb03k7250lo5wti9udkp FOREIGN KEY (artist_artist_id) REFERENCES public.artist(artist_id);

-- public.event_category definition

-- Drop table

-- DROP TABLE public.event_category;

CREATE TABLE public.event_category (
	event_event_id uuid NOT NULL,
	category_category_id uuid NOT NULL
);


-- public.event_category foreign keys

ALTER TABLE public.event_category ADD CONSTRAINT fk1vd1f0j6w2kjmt1ho7vwapky0 FOREIGN KEY (category_category_id) REFERENCES public.category(category_id);
ALTER TABLE public.event_category ADD CONSTRAINT fkeikbhi8tq4c5m9wyijt0m94y5 FOREIGN KEY (event_event_id) REFERENCES public."event"(event_id);

-- public.event_comments definition

-- Drop table

-- DROP TABLE public.event_comments;

CREATE TABLE public.event_comments (
	event_event_id uuid NOT NULL,
	comments_comment_id uuid NOT NULL,
	CONSTRAINT uk_et614qxaxhxa32pt9v6w2ycr0 UNIQUE (comments_comment_id)
);


-- public.event_comments foreign keys

ALTER TABLE public.event_comments ADD CONSTRAINT fkgypklnnpy1jtrfevab25of21v FOREIGN KEY (event_event_id) REFERENCES public."event"(event_id);
ALTER TABLE public.event_comments ADD CONSTRAINT fko6antu1k2syl4k9eknhkr5mca FOREIGN KEY (comments_comment_id) REFERENCES public."comments"(comment_id);

-- public.event_tags definition

-- Drop table

-- DROP TABLE public.event_tags;

CREATE TABLE public.event_tags (
	event_event_id uuid NOT NULL,
	tags varchar(255) NULL
);


-- public.event_tags foreign keys

ALTER TABLE public.event_tags ADD CONSTRAINT fk2y2uwntkt4l2fx9xyoeb8becf FOREIGN KEY (event_event_id) REFERENCES public."event"(event_id);

-- public."location" definition

-- Drop table

-- DROP TABLE public."location";

CREATE TABLE public."location" (
	location_id uuid NOT NULL,
	adrres varchar(255) NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	other_fields_for_add varchar(255) NULL,
	CONSTRAINT location_pkey PRIMARY KEY (location_id)
);

-- public.location_event definition

-- Drop table

-- DROP TABLE public.location_event;

CREATE TABLE public.location_event (
	location_location_id uuid NOT NULL,
	event_event_id uuid NOT NULL,
	CONSTRAINT uk_6d3uslsm7i8iymwtm1ynjn48n UNIQUE (event_event_id)
);


-- public.location_event foreign keys

ALTER TABLE public.location_event ADD CONSTRAINT fk7o39rryb12mxd91qddfprw2yq FOREIGN KEY (event_event_id) REFERENCES public."event"(event_id);
ALTER TABLE public.location_event ADD CONSTRAINT fkiakc3wfwg2r641t4uq4ltxcvq FOREIGN KEY (location_location_id) REFERENCES public."location"(location_id);

-- public.organizer definition

-- Drop table

-- DROP TABLE public.organizer;

CREATE TABLE public.organizer (
	organizer_id uuid NOT NULL,
	addres varchar(255) NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	other_fields_for_add varchar(255) NULL,
	other_fields_for_people varchar(255) NULL,
	CONSTRAINT organizer_pkey PRIMARY KEY (organizer_id)
);