-- public.artist definition

-- Drop table

-- DROP TABLE public.artist;

CREATE TABLE public.artist (
	id uuid NOT NULL,
	description varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT artist_pkey PRIMARY KEY (id)
);

-- public.category definition

-- Drop table

-- DROP TABLE public.category;

CREATE TABLE public.category (
	id uuid NOT NULL,
	description varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);

-- public."comment" definition

-- Drop table

-- DROP TABLE public."comment";

CREATE TABLE public."comment" (
	id uuid NOT NULL,
	"comment" varchar(255) NULL,
	deslikes int4 NOT NULL,
	likes int4 NOT NULL,
	title varchar(255) NULL,
	user_creation uuid NULL,
	CONSTRAINT comment_pkey PRIMARY KEY (id)
);

-- public."event" definition

-- Drop table

-- DROP TABLE public."event";

CREATE TABLE public."event" (
	id uuid NOT NULL,
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
	location_id uuid NULL,
	CONSTRAINT event_pkey PRIMARY KEY (id)
);


-- public."event" foreign keys

ALTER TABLE public."event" ADD CONSTRAINT fkbb6c0h5nhs5og47iem617ehrl FOREIGN KEY (location_id) REFERENCES public."location"(id);

-- public.event_artist definition

-- Drop table

-- DROP TABLE public.event_artist;

CREATE TABLE public.event_artist (
	event_list_id uuid NOT NULL,
	artist_id uuid NOT NULL
);


-- public.event_artist foreign keys

ALTER TABLE public.event_artist ADD CONSTRAINT fk66oxag9xck7chmygh4ju0t7hq FOREIGN KEY (artist_id) REFERENCES public.artist(id);
ALTER TABLE public.event_artist ADD CONSTRAINT fkmkblcljrs3678b4jd3w5vfnk2 FOREIGN KEY (event_list_id) REFERENCES public."event"(id);

-- public.event_category definition

-- Drop table

-- DROP TABLE public.event_category;

CREATE TABLE public.event_category (
	event_id uuid NOT NULL,
	category_id uuid NOT NULL
);


-- public.event_category foreign keys

ALTER TABLE public.event_category ADD CONSTRAINT fk24ud2uucu4h8ga1ois1mnalo8 FOREIGN KEY (event_id) REFERENCES public."event"(id);
ALTER TABLE public.event_category ADD CONSTRAINT fkpwl2b1ylc09urqr0c4n18io FOREIGN KEY (category_id) REFERENCES public.category(id);

-- public.event_comments definition

-- Drop table

-- DROP TABLE public.event_comments;

CREATE TABLE public.event_comments (
	event_id uuid NOT NULL,
	comments_id uuid NOT NULL,
	CONSTRAINT uk_603hnqqdvry597pare5ogc6di UNIQUE (comments_id)
);


-- public.event_comments foreign keys

ALTER TABLE public.event_comments ADD CONSTRAINT fkbg5m1v1ibc2tlum0pk3p1agfu FOREIGN KEY (event_id) REFERENCES public."event"(id);
ALTER TABLE public.event_comments ADD CONSTRAINT fko7ub33ug0ltfuhwn89qx98edj FOREIGN KEY (comments_id) REFERENCES public."comment"(id);

-- public.event_tags definition

-- Drop table

-- DROP TABLE public.event_tags;

CREATE TABLE public.event_tags (
	event_id uuid NOT NULL,
	tags varchar(255) NULL
);


-- public.event_tags foreign keys

ALTER TABLE public.event_tags ADD CONSTRAINT fkhjeq0v70jnlh0glmfoatbqy0b FOREIGN KEY (event_id) REFERENCES public."event"(id);

-- public."location" definition

-- Drop table

-- DROP TABLE public."location";

CREATE TABLE public."location" (
	id uuid NOT NULL,
	adrres varchar(255) NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT location_pkey PRIMARY KEY (id)
);

-- public.location_event_list definition

-- Drop table

-- DROP TABLE public.location_event_list;

CREATE TABLE public.location_event_list (
	location_id uuid NOT NULL,
	event_list_id uuid NOT NULL,
	CONSTRAINT uk_3c6gbhqxptrm4hp600y7j1pmv UNIQUE (event_list_id)
);


-- public.location_event_list foreign keys

ALTER TABLE public.location_event_list ADD CONSTRAINT fk9vcy1y4yax86pjliyqb3xln1s FOREIGN KEY (event_list_id) REFERENCES public."event"(id);
ALTER TABLE public.location_event_list ADD CONSTRAINT fkak83pq2e5c0a8ecw9805yc0j8 FOREIGN KEY (location_id) REFERENCES public."location"(id);

-- public.organizer definition

-- Drop table

-- DROP TABLE public.organizer;

CREATE TABLE public.organizer (
	id uuid NOT NULL,
	address varchar(255) NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT organizer_pkey PRIMARY KEY (id)
);