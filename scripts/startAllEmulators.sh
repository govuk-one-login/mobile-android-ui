#!/usr/bin/env sh
HOME_SDK_ROOT=$HOME/.android/sdk
LIBRARY_SDK_ROOT=$HOME/Library/Android/sdk

if [ -z "$1" ]
then
  echo "You must provide an AVD pattern!" 1>&2
  exit 1
fi

if [ -z "$ANDROID_SDK_ROOT" ]
then
  echo "ANDROID_SDK_ROOT not set, checking other locations"
  if [ -d "$HOME_SDK_ROOT" ]; then
    echo "ANDROID_SDK_ROOT set to $HOME_SDK_ROOT"
    ANDROID_SDK_ROOT=$HOME_SDK_ROOT
  elif [ -d "$LIBRARY_SDK_ROOT" ]; then
    echo "ANDROID_SDK_ROOT set to $LIBRARY_SDK_ROOT"
    ANDROID_SDK_ROOT=$LIBRARY_SDK_ROOT
  else
    echo "SDK not found"
    exit 1
  fi
fi
COUNT=0
for AVD in $("$ANDROID_SDK_ROOT"/emulator/emulator -list-avds | grep -i "$1")
do
  echo "Booting $AVD"
  "$ANDROID_SDK_ROOT"/emulator/emulator -avd "$AVD" > /dev/null 2>&1 &
  COUNT=$((COUNT+1))
  SLEEPS=0
  while [ "$(adb devices | grep -v "List" | grep -v "offline" | sed '/^[[:space:]]*$/d' | wc -l)" -ne $COUNT ]
  do
    echo "Device $COUNT is not yet online"
    sleep 1
    SLEEPS=$((SLEEPS+1))
    if [ $SLEEPS -gt 600 ]
    then
      echo "Device $COUNT failed to come online after 600 seconds"
      exit 1
    fi
  done
done

for EMU in $(adb devices | grep -v "List" | grep -v "offline" | sed '/^[[:space:]]*$/d' | awk '{print $1}')
do
  echo "Checking $EMU has booted"
  SLEEPS=0
  while ! adb -s "$EMU" shell getprop sys.boot_completed > /dev/null 2>&1
  do
    echo "Emulator $EMU has not finished booting"
    sleep 1
    SLEEPS=$((SLEEPS+1))
    if [ $SLEEPS -gt 600 ]
    then
      echo "Emulator $EMU failed to boot after 600 seconds"
      exit 1
    fi
  done
done