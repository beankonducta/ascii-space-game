package com.patrick.game.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioUtils {

    public static int[][] rawWavData(String filePath) {
        File file = new File(filePath);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int frameLength = (int) audioInputStream.getFrameLength();
        int frameSize = (int) audioInputStream.getFormat().getFrameSize();
        byte[] bytes = new byte[frameLength * frameSize];
        int numChannels = audioInputStream.getFormat().getChannels();
        int[][] toReturn = new int[numChannels][frameLength];
        int result = 0;
        try {
            result = audioInputStream.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int sampleIndex = 0;

        for (int t = 0; t < bytes.length;) {
            for (int channel = 0; channel < numChannels; channel++) {
                int low = (int) bytes[t];
                t++;
                int high = (int) bytes[t];
                t++;
                int sample = getSixteenBitSample(high, low);
                toReturn[channel][sampleIndex] = sample;
            }
            sampleIndex++;
        }
        return toReturn;
    }

    private static int getSixteenBitSample(int high, int low) {
        return (high << 8) + (low & 0x00ff);
    }
}
