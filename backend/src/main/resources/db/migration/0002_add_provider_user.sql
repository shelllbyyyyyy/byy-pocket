CREATE TYPE provider_enum AS ENUM ('google', 'facebook', 'github', 'local');

ALTER public.users
ADD COLUMN isVerified boolean NOT NULL SET DEFAULT false;
ADD COLUMN provider provider_enum DEFAULT 'local';