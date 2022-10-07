\c host_usage;
DROP TABLE host_info, host_usage;

CREATE TABLE PUBLIC.host_info(
    id SERIAL NOT NULL PRIMARY KEY,
    hostname VARCHAR UNIQUE NOT NULL,
    cpu_number INTEGER NOT NULL,
    cpu_architecture VARCHAR NOT NULL,
    cpu_model VARCHAR NOT NULL,
    cpu_mhz NUMERIC NOT NULL,
    l2_cache INTEGER NOT NULL,
    total_mem INTEGER NOT NULL,
    timestamp TIMESTAMP
);

CREATE TABLE PUBLIC.host_usage(
    timestamp TIMESTAMP NOT NULL,
    host_id SERIAL NOT NULL,
    memory_free INTEGER NOT NULL,
    cpu_idle INTEGER NOT NULL,
    cpu_kernel INTEGER NOT NULL,
    disk_io INTEGER NOT NULL,
    disk_available INTEGER NOT NULL,
    CONSTRAINT fk_host_id
        FOREIGN KEY(host_id)
            REFERENCES host_info(id)
);