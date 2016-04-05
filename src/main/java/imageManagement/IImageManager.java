package imageManagement;

import java.io.File;

/**
 * Created by danisimov on 4/4/16.
 */
public interface IImageManager {
    void copyImage(File scrImage, File imageFile);
    File getImage(String key);
    String getImagePath(String key);
    boolean verifyImageExists(String key);
    void deleteImage(String key);
}