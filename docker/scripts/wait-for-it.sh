#!/bin/bash
set -e
host="$1"
shift
cmd="$@"
until dockerize -wait tcp://$host:3306 -timeout 30s; do
  >&2 echo "MySQL is unavailable - sleeping"
  sleep 1
done
>&2 echo "MySQL is up - executing command"
exec "$@"