CREATE TABLE public.artist (
	id uuid NOT NULL,
	description varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT artist_pkey PRIMARY KEY (id)
);

CREATE TABLE public.category (
	id uuid NOT NULL,
	description varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);

CREATE TABLE public."comment" (
	id uuid NOT NULL,
	"comment" varchar(255) NOT NULL,
	dislikes int4 NOT NULL,
	likes int4 NOT NULL,
	title varchar(255) NULL,
	user_creation uuid NULL,
	CONSTRAINT comment_pkey PRIMARY KEY (id)
);

CREATE TABLE public."event" (
	id uuid NOT NULL,
	created_at varchar(255) NULL,
	description varchar(255) NULL,
	end_date date NULL,
	"name" varchar(255) NOT NULL,
	payed bool NOT NULL,
	start_date date NULL,
	status bool NOT NULL,
	thumbnail bytea NULL,
	updated_at varchar(255) NULL,
	web_page varchar(255) NULL,
	CONSTRAINT event_pkey PRIMARY KEY (id)
);

CREATE TABLE public.event_artist_list (
	event_list_id uuid NOT NULL,
	artist_list_id uuid NOT NULL
);

ALTER TABLE public.event_artist_list ADD CONSTRAINT fk3fh42yrjv0u2am0vkhpq1g0ys FOREIGN KEY (event_list_id) REFERENCES public."event"(id);
ALTER TABLE public.event_artist_list ADD CONSTRAINT fk4q7gxuvs4egw23ub4kol2747x FOREIGN KEY (artist_list_id) REFERENCES public.artist(id);

CREATE TABLE public.event_category_list (
	event_list_id uuid NOT NULL,
	category_list_id uuid NOT NULL
);

ALTER TABLE public.event_category_list ADD CONSTRAINT fk4bl4de1ox65nnkgc9cf3bgxa9 FOREIGN KEY (category_list_id) REFERENCES public.category(id);
ALTER TABLE public.event_category_list ADD CONSTRAINT fkcf63yu0t8ln4no1ww68h42we7 FOREIGN KEY (event_list_id) REFERENCES public."event"(id);

CREATE TABLE public.event_comment_list (
	event_id uuid NOT NULL,
	comment_list_id uuid NOT NULL,
	CONSTRAINT uk_8pms1kg1k9uaeabqmafqe6l7a UNIQUE (comment_list_id)
);

ALTER TABLE public.event_comment_list ADD CONSTRAINT fkf2pm3b7pg5o5qxyvgpffrfymy FOREIGN KEY (comment_list_id) REFERENCES public."comment"(id);
ALTER TABLE public.event_comment_list ADD CONSTRAINT fknfnvgig45g0tsc7bt3f7jv1xx FOREIGN KEY (event_id) REFERENCES public."event"(id);

CREATE TABLE public."location" (
	id uuid NOT NULL,
	address varchar(255) NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT location_pkey PRIMARY KEY (id)
);