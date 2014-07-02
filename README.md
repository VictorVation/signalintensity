# Signal Intensity
Minimalist Android app to show the strength of your wifi signal. Available on the Play Store: [Signal Intensity](https://play.google.com/store/apps/details?id=com.victorszeto.signalintensity)

<img src="/images/strongsignal.png?raw=true" width="50%" />
<img src="/images/weaksignal.png?raw=true" width="50%" />

## What is this
Displays the RSSI (relative signal strength indicator) detected by your phone, with a percentage of "maximum" (according to Android's `WifiManager`) possible RSSI. The background color changes depending on how strong your signal is.

## How to read
RSSI is a negative number between -100 and -55. The higher the number, the stronger the signal.
The background colour is an interpolated color between blue and red. Blue means weak signal, red means strong signal.

## Why do I need this
Useful for looking for WiFi "dead zones" in a room, or finding places where the signal is strongest when you're in a place with a flaky/weak wifi connection. I also hear it's cool for light painting and taking long-exposure photos with.
