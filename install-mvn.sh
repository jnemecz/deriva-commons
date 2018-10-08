#!/bin/bash

mvn clean
mvn package
mvn install:install-file -Dfile=target/deriva-commons-1.1.jar -DgroupId=cz.deriva.commons \
-DartifactId=deriva-commons -Dversion=1.1 -Dpackaging=jar -DgenerationPom=true