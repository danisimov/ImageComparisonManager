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
    private File directory;

    LocalImageManager(File scrImage, String suiteName) {
        initDirectory();
        initFilesCollection(suiteName);

        if(!verifyImageExists("expected")) {
            copyImage(scrImage, getImage("expected"));
        }

        copyImage(scrImage, getImage("actual"));
    }

    private void initDirectory() {
        if (OSProperties.getOSName().contains("Windows")) {
            directory = new File(OSProperties.getHomeFolder() + "/images");
        }
        else {
            directory = new File("/home/" + OSProperties.getAccountName() + "/images");
        }
        if (directory.mkdirs()) { System.out.println("/images directory created");}
    }

    private void initFilesCollection(final String suiteName) {
        filesCollection = new HashMap<String, File>(){
            {
                put("expected", new File(directory.getPath() + "/" + suiteName + "/expected.png"));
                put("actual", new File(directory.getPath() + "/" + suiteName + "/actual.png"));
                put("difference", new File(directory.getPath() + "/" + suiteName + "/difference.png"));
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
        assert filesCollection.get(key).delete();
    }

    public void deleteDirectory() {
        if (directory.exists()) {
            try {
                FileUtils.deleteDirectory(directory);
            }
            catch (IOException e) {
                System.out.println("purgeDirectories exception\n" + e);
            }
        }
    }
}
