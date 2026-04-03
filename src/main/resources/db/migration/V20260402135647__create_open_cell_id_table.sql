CREATE TABLE IF NOT EXISTS "open_cell_id"
(
    id                           integer GENERATED ALWAYS AS IDENTITY,
    radio                        VARCHAR(32) NOT NULL,
    mcc                          INTEGER NOT NULL,
    mnc                          INTEGER NOT NULL,
    lac                          INTEGER NOT NULL,
    cid                          BIGINT NOT NULL,
    unit                         INTEGER,
    lon                          DOUBLE NOT NULL,
    lat                          DOUBLE NOT NULL,
    cell_range                   INTEGER,
    samples                      INTEGER,
    changeable                   INTEGER,
    created                      INTEGER,
    updated                      INTEGER,
    average_signal               INTEGER,
    UNIQUE (mcc, mnc, lac, cid),
    PRIMARY KEY (id)
    );
