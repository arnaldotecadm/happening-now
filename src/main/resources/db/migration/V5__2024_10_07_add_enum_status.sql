ALTER TABLE event
    RENAME COLUMN status TO statusEnum;

ALTER TABLE event
    ALTER COLUMN statusEnum TYPE ENUM(PENDING("Pending"),
    ACTIVE("Active"), INACTIVE("Inactive"),
    DELETED("Deleted"), PENDING_ANALYSIS("Pending analysis");