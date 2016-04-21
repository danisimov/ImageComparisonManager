package service;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by danisimov on 4/4/16.
 */
class LocalImageManager implements IImageManager {

    private HashMap<String, File> filesCollection;

    LocalImageManager(File scrImage, String suiteName) {
        initFilesCollection(suiteName);

        if(!verifyImageExists("expected")) {
            copyImage(scrImage, getImage("expected"));
        }

        copyImage(scrImage, getImage("actual"));
    }

    private void initFilesCollection(final String suiteName) {
        filesCollection = new HashMap<String, File>(){
            {
                put("expected", new File("images/" + suiteName + "/expected.png"));
                put("actual", new File("images/" + suiteName + "/actual.png"));
                put("difference", new File("images/" + suiteName + "/difference.png"));
            }
        };
    }

    public void copyImage(File scrImage, File imageFile) {
        try {
            FileUtils.copyFile(scrImage, imageFile);
        } catch (IOException e) {
            System.out.println("Copying file exception:" + e);
        }
    }

    public File getImage(String key) {
        return filesCollection.get(key);
    }

    public String getImagePath(String key) {
        return filesCollection.get(key).getPath();
    }

    public boolean verifyImageExists(String key) {
        return filesCollection.get(key).exists();
    }

    public void deleteImage(String key) {
        filesCollection.get(key).delete();
    }
}
