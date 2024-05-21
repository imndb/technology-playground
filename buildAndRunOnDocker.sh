
# Clean and build the project using Maven
mvn clean install

# Build the Docker image
echo "Building the Docker image..."
docker build -t demo:latest .

#  Run the Docker Compose file
docker-compose -f compose.yaml up --build -d

echo "Done."
