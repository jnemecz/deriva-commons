#!/bin/bash

# navysit aktualni verzi zde a to same v pom.xml

mvn clean
mvn package
mvn install:install-file -Dfile=target/deriva-commons-1.0.5.jar -DgroupId=cz.deriva.commons \
-DartifactId=deriva-commons -Dversion=1.0.5 -Dpackaging=jar -DgenerationPom=true