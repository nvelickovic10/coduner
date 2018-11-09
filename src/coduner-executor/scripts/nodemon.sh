#!/bin/bash
cd "$(dirname "$0")"
. common.sh
cd ./build/

logDebug "Running nodemon"
../node_modules/.bin/nodemon  --delay 0.2 --ignore ./public/ -w ./ ./server/index.js
