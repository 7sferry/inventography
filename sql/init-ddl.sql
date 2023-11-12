/************************
 * Made by [MR Ferry™]  *
 * on November 2023     *
 ************************/

-- auto-generated definition
CREATE DATABASE inventography
;

-- auto-generated definition
CREATE SCHEMA inventory
;

-- auto-generated definition
CREATE TABLE inventory.products (
    product_code      VARCHAR(26) NOT NULL PRIMARY KEY,
    product_name      VARCHAR(50),
    expiring_date     DATE,
    is_active         BOOLEAN,
    stock             INTEGER,
    version           INTEGER,
    creation_time     TIMESTAMP(6) WITH TIME ZONE,
    last_updated_time TIMESTAMP(6) WITH TIME ZONE
)
;

-- auto-generated definition
CREATE SCHEMA ordering
;

-- auto-generated definition
CREATE TABLE ordering.orders (
    order_no          VARCHAR(26) NOT NULL PRIMARY KEY,
    active            BOOLEAN,
    version           INTEGER,
    creation_time     TIMESTAMP(6) WITH TIME ZONE,
    last_updated_time TIMESTAMP(6) WITH TIME ZONE,
    customer_name     VARCHAR(25),
    seller_name       VARCHAR(25)
)
;

CREATE TABLE ordering.order_items (
    price             NUMERIC(38, 2),
    quantity          INTEGER,
    creation_time     TIMESTAMP(6) WITH TIME ZONE,
    id                BIGINT NOT NULL PRIMARY KEY,
    last_updated_time TIMESTAMP(6) WITH TIME ZONE,
    order_order_no    VARCHAR(26)
        CONSTRAINT fkawjxpm6oqi4ljrvefau82g7he REFERENCES ordering.orders,
    product_code      VARCHAR(26)
)
;
