#!/bin/bash

mvn clean compile assembly:single

cp ./target/deriva-commons-full.jar ~/IdeaProjects/_LIBS/
cp ./target/deriva-commons-full.jar ~/IdeaProjects/alfa-restapi/src/main/resources
cp ./target/deriva-commons-full.jar ~/IdeaProjects/togglemobjednavka/src/main/resources

