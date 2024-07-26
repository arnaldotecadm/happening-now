-- TABLE artist;
CREATE TABLE artist (
	id uuid NOT NULL,
	description varchar(255) NOT NULL,
	name varchar(255) NULL,
	CONSTRAINT artist_pkey PRIMARY KEY (id)
);

-- TABLE category;
CREATE TABLE category (
	id uuid NOT NULL,
	description varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);

-- TABLE comment;
CREATE TABLE comment (
	id uuid NOT NULL,
	comment_text varchar(255) NOT NULL,
	dislikes int4 NOT NULL,
	likes int4 NOT NULL,
	title varchar(255) NULL,
	user_creation uuid NULL,
	CONSTRAINT comment_pkey PRIMARY KEY (id)
);

-- TABLE event;
CREATE TABLE event (
	id uuid NOT NULL,
	created_at timestamp NULL,
	description varchar(255) NULL,
	end_date date NULL,
	name varchar(255) NOT NULL,
	payed bool NOT NULL,
	start_date date NULL,
	status bool NOT NULL,
	thumbnail bytea NULL,
	updated_at timestamp NULL,
	web_page varchar(255) NULL,
	CONSTRAINT event_pkey PRIMARY KEY (id)
);

-- TABLE location;
CREATE TABLE location (
	id uuid NOT NULL,
	address varchar(255) NOT NULL,
	description varchar(255) NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT location_pkey PRIMARY KEY (id)
);

-- TABLE organizer;
CREATE TABLE organizer (
	id uuid NOT NULL,
	address varchar(255) NOT NULL,
	description varchar(255) NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT organizer_pkey PRIMARY KEY (id)
);

--  TABLE event_artist_list;
CREATE TABLE event_artist_list (
	event_list_id uuid NOT NULL,
	artist_list_id uuid NOT NULL,
	CONSTRAINT fk_event_artist_artist FOREIGN KEY (artist_list_id) REFERENCES artist(id),
	CONSTRAINT fk_event_artist_event FOREIGN KEY (event_list_id) REFERENCES event(id)
);

-- TABLE event_category_list;
CREATE TABLE event_category_list (
	event_list_id uuid NOT NULL,
	category_list_id uuid NOT NULL,
	CONSTRAINT fk_event_category_category FOREIGN KEY (category_list_id) REFERENCES category(id),
	CONSTRAINT fk_event_category_event FOREIGN KEY (event_list_id) REFERENCES event(id)
);

-- TABLE event_comment_list;
CREATE TABLE event_comment_list (
	event_id uuid NOT NULL,
	comment_list_id uuid NOT NULL,
	CONSTRAINT uk_event_comment UNIQUE (comment_list_id),
	CONSTRAINT fk_event_comment_comment FOREIGN KEY (comment_list_id) REFERENCES comment(id),
	CONSTRAINT fk_event_comment_event FOREIGN KEY (event_id) REFERENCES event(id)
);

-- TABLE event_organizer_list;
CREATE TABLE event_organizer_list (
	event_list_id uuid NOT NULL,
	organizer_list_id uuid NOT NULL,
	CONSTRAINT fk_event_organizer_event FOREIGN KEY (event_list_id) REFERENCES event(id),
	CONSTRAINT fk_event_organizer_organizer FOREIGN KEY (organizer_list_id) REFERENCES organizer(id)
);

-- TABLE event_tag_list;
CREATE TABLE event_tag_list (
	event_id uuid NOT NULL,
	tag_list varchar(255) NULL,
	CONSTRAINT fk_event_tag_event FOREIGN KEY (event_id) REFERENCES event(id)
);

-- TABLE location_event_list;
CREATE TABLE location_event_list (
	location_id uuid NOT NULL,
	event_list_id uuid NOT NULL,
	CONSTRAINT uk_location_event UNIQUE (event_list_id),
	CONSTRAINT fk_location_event_event FOREIGN KEY (event_list_id) REFERENCES event(id),
	CONSTRAINT fk_location_event_location FOREIGN KEY (location_id) REFERENCES location(id)
);