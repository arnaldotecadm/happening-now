ALTER TABLE event
    RENAME COLUMN description TO long_description;

ALTER TABLE event
    ALTER COLUMN long_description TYPE varchar(5000);

ALTER TABLE event
    ADD short_description varchar(150);

UPDATE event
SET short_description = LEFT(long_description, 147) || '...'
WHERE short_description IS NULL;