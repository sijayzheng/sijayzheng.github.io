#!/bin/bash

mvn clean package -DskipTests=true -Dmaven.javadoc.skip=true -Dcheckstyle.skip=true -P prod -U