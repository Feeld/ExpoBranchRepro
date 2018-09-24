#!/bin/bash

./gradlew ${1:-installDevMinSdkDevKernelDebug} --stacktrace && adb shell am start -n co.feeld.client.beta/host.exp.exponent.MainActivity
