#!/bin/bash
# set -e

SCRIPT_NAME="${0##*/}"

function logInfo {
    echo ">>> INFO: [${SCRIPT_NAME}] $1"
}

function logDebug {
    # echo ">>> DEBUG: [$(date)] [${SCRIPT_NAME}] $1"
    return 0
}

logInfo "Running ${SCRIPT_NAME}"

cd ../
