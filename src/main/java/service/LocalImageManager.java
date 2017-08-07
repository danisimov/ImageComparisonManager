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
        directory = new File(OSProperties.getHomeFolder() + "/ICS");
        if (directory.mkdirs()) { System.out.println(directory.getPath() + " directory created");}
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

    public boolean deleteImage(String key) {
        return filesCollection.get(key).delete();
    }

    public boolean deleteDirectory() {
        if (directory.exists()) {
            try {
                FileUtils.deleteDirectory(directory);
                return true;
            }
            catch (IOException e) {
                System.out.println("purgeDirectories exception\n" + e);
            }
        }
        return false;
    }
}
