CREATE TABLE users (
    id uuid PRIMARY KEY,
    name varchar(128) NOT NULL
);
CREATE TABLE products (
    id uuid PRIMARY KEY,
    title varchar(128) NOT NULL,
    price float NOT NULL
);
CREATE TABLE orders (
    id uuid PRIMARY KEY,
    user_id uuid NOT NULL,
    product_id uuid NOT NULL,
    quantity float NOT NULL,
    CONSTRAINT orders_user_id_fk
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT orders_role_product_id_fk
        FOREIGN KEY (product_id)
        REFERENCES products(id)
        ON DELETE CASCADE
);
CREATE INDEX user_id_orders_idx ON orders(user_id);
