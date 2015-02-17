package guessthepitchapp.guessthepitch;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.media.*;

public class AudioHandler extends Activity {
    // originally from http://marblemice.blogspot.com/2010/04/generate-and-play-tone-in-android.html
    // and modified by Steve Pomeroy <steve@staticfree.info>
    private static final int duration = 2; // seconds
    private static final int sampleRate = 8000;
    private static final int numSamples = duration * sampleRate;
    private static final double sample[] = new double[numSamples];

    private static byte generatedSnd[] = new byte[2 * numSamples];

    static Handler handler = new Handler();

    public static void playTone(final int freqOfTone) {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                genTone(freqOfTone);
                handler.post(new Runnable() {

                    public void run() {
                        playSound();
                    }
                });
            }
        });
        thread.start();
    }

    public static void genTone(int freqOfTone) {
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
        }

        int idx = 0;
        for (final double dVal : sample) {
            final short val = (short) ((dVal * 32767));
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }
    }

    public static void playSound() {
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();
    }
}