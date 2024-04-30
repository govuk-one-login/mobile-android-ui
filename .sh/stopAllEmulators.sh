#!/usr/bin/env sh

# Obtains all devices that begin with 'emulator'
for DEVICE in $(adb devices | grep emulator | cut -f1)
do
  # Close the device; ignoring standard output
  adb -s "$DEVICE" emu kill > /dev/null 2>&1
done