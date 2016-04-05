package imageManagement;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by danisimov on 4/4/16.
 */
public class LocalImageManager implements IImageManager {
    LocalImageStorage localImageStorage;

    public LocalImageManager(File scrImage) {
        localImageStorage = new LocalImageStorage(scrImage);

        if(!verifyImageExists("expected")) {
            copyImage(scrImage, getImage("expected"));
        }

        copyImage(scrImage, getImage("actual"));
    }

    public void copyImage(File scrImage, File imageFile) {
        try {
            FileUtils.copyFile(scrImage, imageFile);
        } catch (IOException e) {
            System.out.println("Copying file exception:" + e);
        }
    }

    public File getImage(String key) {
        return localImageStorage.filesCollection.get(key);
    }

    public String getImagePath(String key) {
        return localImageStorage.filesCollection.get(key).getPath();
    }

    public boolean verifyImageExists(String key) {
        return localImageStorage.filesCollection.get(key).exists();
    }

    public void deleteImage(String key) {
        localImageStorage.filesCollection.get(key).delete();
    }
}
