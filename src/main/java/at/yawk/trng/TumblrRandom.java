package at.yawk.trng;

import java.util.Random;

/**
 * @author yawkat
 */
public class TumblrRandom extends Random {
    public TumblrRandom(EntropySource entropySource) {
        super(hc(entropySource.generateEntropy()));
    }

    public TumblrRandom() {
        this(TumblrEntropySource.getInstance());
    }

    private static long hc(byte[] arr) {
        long hc = 1;
        for (byte b : arr) {
            hc = hc * 7L + b;
        }
        return hc;
    }
}
