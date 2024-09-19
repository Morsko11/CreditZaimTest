CREATE TABLE credits (
                         id SERIAL PRIMARY KEY,
                         credit_pmt_string VARCHAR(255) NOT NULL,
                         credit_pmt_start DATE NOT NULL
);