#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Determine the location of the script
APP_HOME=$(cd "$(dirname "$0")"; pwd)

# Execute the Gradle wrapper
exec java -jar "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" "$@"
