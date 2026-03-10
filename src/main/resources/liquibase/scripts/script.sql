-- liquibase formatted sql

-- changeset neo:1
CREATE TABLE IF NOT EXISTS notifications (
                                             id BIGSERIAL PRIMARY KEY,
                                             chat_id BIGINT NOT NULL,
                                             text TEXT NOT NULL,
                                             date TIMESTAMP WITHOUT TIME ZONE NOT NULL  -- для LocalDateTime
);

-- changeset neo:2
CREATE INDEX IF NOT EXISTS idx_chat_date ON notifications(chat_id, date);
