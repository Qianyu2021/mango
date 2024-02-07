# Use the official PostgreSQL image as the base image
FROM postgres:15.5

# Set environment variables for PostgreSQL
ENV POSTGRES_DB mango
ENV POSTGRES_USER mango
ENV POSTGRES_PASSWORD orangesaredelicious

# Expose the PostgreSQL default port (5432)
EXPOSE 5432

# Create a custom entrypoint script to initialize the database and user
COPY scripts/init_postgres.sh /docker-entrypoint-initdb.d/