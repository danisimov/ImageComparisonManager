package service;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by danisimov on 3/23/16.
 */
public class FileManager {
    HashMap<String, File> filesCollection;

    public FileManager(File scrFile) {
        filesCollection = new HashMap<String, File>(){
            {
                put("expected", new File("images/expected.png"));
                put("actual", new File("images/actual.png"));
                put("difference", new File("images/difference.png"));
            }
        };

        if(!checkFile("expected")) {
            writeFile(scrFile, getFile("expected"));
        }

        writeFile(scrFile, getFile("actual"));
    }

    public void flush() {
        if(checkFile("actual")){
            deleteFile("actual");
        }
        if(checkFile("difference")){
            deleteFile("difference");
        }
    }

    private void writeFile(File scrFile, File imageFile) {
        try {
            FileUtils.copyFile(scrFile, imageFile);
        } catch (IOException e) {
            System.out.println("Image comparator creating file exception:" + e);
        }
    }

    private boolean checkFile(String key) {
        return filesCollection.get(key).exists();
    }

    private File getFile(String key) {
        return filesCollection.get(key);
    }

    public String getPath(String key) {
        return filesCollection.get(key).getPath();
    }

    private void deleteFile(String key) {
        filesCollection.get(key).delete();
    }
}
