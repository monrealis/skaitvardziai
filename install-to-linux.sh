#!/bin/bash
set -e

cd skaitvardziai-parent
mvn clean install
cd ../skaitvardziai-app
./install-gcj-exec.sh
