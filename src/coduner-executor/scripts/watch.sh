#!/bin/bash
cd "$(dirname "$0")"
. common.sh

logDebug "Running watch commands"
node_modules/.bin/watch ./scripts/buildServer.sh ./server/ --wait=1 --interval=1 &

logInfo "Waiting 2 seconds before nodemon"
sleep 2

logDebug "Running nodemon"
./scripts/nodemon.sh

logInfo "Exit 0"
exit 0
