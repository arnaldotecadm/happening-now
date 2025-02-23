DROP TRIGGER IF EXISTS trigger_update_short_description ON event;
DROP FUNCTION IF EXISTS update_short_description;

UPDATE event
SET short_description = LEFT(long_description, 147) || '...'
WHERE long_description IS NOT NULL;
