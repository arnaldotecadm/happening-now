-- 1. Altera a coluna 'status' de booleano para o enum 'StatusEnum'
ALTER TABLE event
    ALTER COLUMN status TYPE VARCHAR(255)
    USING CASE
        WHEN status = true THEN 'ACTIVE'
        ELSE 'INACTIVE'
    END;

-- 2. Ajusta o nome da coluna para refletir o enum corretamente
ALTER TABLE event
    RENAME COLUMN status TO status_enum;

-- 3. Garante que a coluna seja não nula, se necessário
ALTER TABLE event
    ALTER COLUMN status_enum SET NOT NULL;
