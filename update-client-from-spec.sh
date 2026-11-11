#!/usr/bin/env bash

set -e

curl -o 'spec/openapi.json' 'http://localhost:8080/rest/openapi.json' \
	&& mvn -f openapi-client-generator \
		initialize -DskipTests=true

git checkout -- component-registry-client/pom.xml
mvn -f component-registry-client clean install
