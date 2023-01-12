#!/bin/sh
ENV_FILE=environment.env
APPLICATION_CONTAINER=database_double_batch_app

echo
echo "Building application container image..."
sh ./script/build_docker_image.sh

echo "Changing working directory to docker/"
cd ./docker

echo "Stopping previous executions (if any)..."
sudo docker-compose down

echo "Starting using the environment file $ENV_FILE..."
sudo docker-compose --env-file $ENV_FILE up -d

echo "Cleaning untagged docker images."
sudo docker images -q -f "dangling=true" | sudo xargs --no-run-if-empty docker rmi -f

echo "Watching logs for $APPLICATION_CONTAINER container..."
sudo docker logs -f --tail 500 $APPLICATION_CONTAINER

echo
echo "Finished successfully."