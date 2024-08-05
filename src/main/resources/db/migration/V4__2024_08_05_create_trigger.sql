CREATE OR REPLACE FUNCTION update_short_description()
RETURNS TRIGGER AS $$
BEGIN
    NEW.short_description := LEFT(NEW.long_description, 147) || '...';
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_short_description
BEFORE INSERT OR UPDATE ON event
FOR EACH ROW
EXECUTE FUNCTION update_short_description();
