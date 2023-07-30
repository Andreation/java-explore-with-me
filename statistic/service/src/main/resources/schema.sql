CREATE TABLE IF NOT EXISTS hits
(
    hit_id  BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    app     VARCHAR(200)  NOT NULL,
    uri     VARCHAR(2000) NOT NULL,
    ip      VARCHAR(20)   NOT NULL,
    created TIMESTAMP
);