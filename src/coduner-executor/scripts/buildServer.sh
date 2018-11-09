#!/bin/bash
cd "$(dirname "$0")"
. common.sh

logDebug "Building server"
mkdir -p ./build/
babel ./server/ --out-dir ./build/ --presets=@babel/preset-env
