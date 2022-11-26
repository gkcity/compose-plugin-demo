#!/bin/sh

./gradlew clean
./gradlew app-compose-ui-devices:build
cd app-compose-ui-devices/build/outputs/aar
unzip app-compose-ui-devices-release.aar
~/Library/Android/sdk/build-tools/30.0.3/dx --dex --min-sdk-version=26 --output=compose_ui_devices.dex classes.jar
cp compose_ui_devices.dex ../../../../app/src/main/assets
./gradlew assembleDebug