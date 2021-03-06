package se.ju.students.malu1798.ledcontrollerproject;

/*Written by Lucas M and Carl M for capability with embedded system */
/*
Brightness: W000: [0]-[3]
RED: R000: [4]-[7]
GREEN: G000: [8]-[11]
BLUE: B000: [12]-[15]
STROBE ON/OFF: S0: [16]-[17]
STROBE WAVEFORM: X0: [18]-[19]
STROBE HEIGHT: H000: [20]-[23]
STROBE FREQUENCY: F000: [24]-[27]
STROBE AMPLITUDE: A000: [28]-[31]
STROBE OFFSET: O000: [32]-[35]
AUDIO SYNC: U0: [36]- [37]
LED CONTROL: C0: [38]-[39]
LEDs: L0000000000: [50]-[60]
*/

public enum ControlLedEnum {
    BRIGHTNESS,
    RED,
    GREEN,
    BLUE,
    STROBE,
    STROBE_WAVEFORM,
    STROBE_HEIGHT,
    STROBE_FREQUENCY,
    STROBE_AMPLITUDE,
    STROBE_OFFSET,
    AUDIO_SYNC,
    LED_CONTROL,
    LEDs
}
