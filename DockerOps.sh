#!/bin/bash
echo "====================Loading Variables=========="
# Replace these variables with your server details
SERVER_IP="134.209.178.235"
SSH_KEY_PATH="/path/to/your/private/key"
SSH_USER="root"
echo "====================Setting Env To Java 17=========="
export JAVA_HOME=`/usr/libexec/java_home -v 17.0.2.`
echo "====================Building Java App==============="
mvn clean install
echo "====================Remove Image Docker Image==========="
docker rmi savoirms/com.wati.tea.board.core:latest:latest
echo "====================Building Docker Image==========="
docker build -f Dockerfile-dev  -t savoirms/com.wati.tea.board.core:latest .
echo "====================Pushing Docker Image============"
docker push savoirms/com.wati.tea.board.core:latest
#ssh -i "$SSH_KEY_PATH" "$SSH_USER"@"$SERVER_IP" << EOF
echo "====================Updating the deployment============"
# SSH into the server and run commands
ssh "$SSH_USER"@"$SERVER_IP" << EOF
  cd /opt/tbk/tbk-core || exit
  pwd
  ls
  cat docker-compose.yaml
  docker compose pull
  docker compose down
  docker compose up -d
  docker-compose ps  # Check the status of Docker containers after starting them
EOF


/www/wwwroot/tbk.com