#!/usr/bin/env bash

echo "Destroying transient MySQL instance"

docker stop store
docker rm store
echo "Done"
