package com.patrick.game.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioUtils {

    /**
     * Reads a wav file into a 2d byte array, split by channel and representing the audio
     * waveform at (n) position of the audio file.
     *
     * @param filePath
     * @return
     */
    public static int[][] rawWavData(String filePath) {
        File file = new File(filePath);
        AudioInputStream audioInputStream = null;
        // reads the audio file in
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // set up arrays for data storage
        int frameLength = (int) audioInputStream.getFrameLength();
        int frameSize = audioInputStream.getFormat().getFrameSize();
        byte[] bytes = new byte[frameLength * frameSize];
        int numChannels = audioInputStream.getFormat().getChannels();
        int[][] toReturn = new int[numChannels][frameLength];
        // read audio file into byte array
        try {
            audioInputStream.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // splits channels and reads sixteen bit data into 2d byte array
        int sampleIndex = 0;
        for (int t = 0; t < bytes.length;) {
            for (int channel = 0; channel < numChannels; channel++) {
                int low = bytes[t];
                t++;
                int high = bytes[t];
                t++;
                int sample = getSixteenBitSample(high, low);
                toReturn[channel][sampleIndex] = sample;
            }
            sampleIndex++;
        }
        return toReturn;
    }

    /**
     * Returns a sixteen bit sample from a high and low point of the audio waveform.
     *
     * @param high
     * @param low
     * @return
     */
    private static int getSixteenBitSample(int high, int low) {
        return (high << 8) + (low & 0x00ff);
    }
}
