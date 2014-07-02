# Signal Intensity
Minimalist Android app to show the strength of your wifi signal. Available on the Play Store: [Signal Intensity](https://play.google.com/store/apps/details?id=com.victorszeto.signalintensity)

![strong-signal](/images/strongsignal.png?raw=true)
![weak-signal](/images/weaksignal.png?raw=true)

## What is this
Displays the RSSI (relative signal strength indicator) detected by your phone, with a percentage of "maximum" (according to Android's `WifiManager`) possible RSSI. The background color changes depending on how strong your signal is.

## How to read
RSSI is a negative number between -100 and -55. The higher the number, the stronger the signal.
The background colour is an interpolated color between blue and red. Blue means weak signal, red means strong signal.
