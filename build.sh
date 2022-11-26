#!/bin/sh

./gradlew clean
./gradlew app-compose-ui-devices:build
cd app-compose-ui-devices/build/outputs/aar
unzip app-compose-ui-devices-release.aar
~/Library/Android/sdk/build-tools/30.0.3/d8 classes.jar
cp classes.dex ../../../../app/src/main/assets/compose_ui_devices.dex
./gradlew installDebug
