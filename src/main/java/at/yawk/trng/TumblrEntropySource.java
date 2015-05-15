package at.yawk.trng;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yawkat
 */
public class TumblrEntropySource implements EntropySource {
    private static final TumblrEntropySource instance = new TumblrEntropySource();

    private TumblrEntropySource() {}

    public static TumblrEntropySource getInstance() {
        return instance;
    }

    @Override
    public byte[] generateEntropy() {
        try {
            URL url = new URL("https://www.tumblr.com/explore/text");
            MessageDigest digest = MessageDigest.getInstance("SHA");
            try (InputStream inputStream = url.openStream()) {
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) != -1) {
                    digest.update(buf, 0, len);
                }
            }
            return digest.digest();
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
