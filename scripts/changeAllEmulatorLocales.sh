#!/usr/bin/env sh

if [ -z "$1" ]
then
  echo "You must provide an ISO 639-1 language code! (eg cy-GB)" 1>&2
  exit 1
fi

LANGUAGE_CODE=$1

for DEVICE in $(adb devices | grep -v "List" | awk '{print $1}')
do
  adb root
  adb -s "$DEVICE" shell "setprop persist.sys.locale $LANGUAGE_CODE; setprop ctl.restart zygote"
  echo "Checking $DEVICE has restarted"
  SLEEPS=0
  while ! adb -s "$DEVICE" shell getprop sys.boot_completed > /dev/null 2>&1
  do
    echo "Emulator $DEVICE has not finished restarting"
    sleep 1
    SLEEPS=$((SLEEPS+1))
    if [ $SLEEPS -gt 600 ]
    then
      echo "Emulator $DEVICE failed to restart after 600 seconds"
      exit 1
    fi
  done
  adb unroot
done