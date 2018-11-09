#!/bin/bash
cd "$(dirname "$0")"
. common.sh

logDebug "Building server"
mkdir -p ./build/server/
babel ./server/ --out-dir ./build/server/ --presets=@babel/preset-env
