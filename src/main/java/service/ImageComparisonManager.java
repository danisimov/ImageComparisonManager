package service;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by danisimov on 28.02.2016.
 */
public class ImageComparisonManager {
    private static ImageComparisonManager instance;
    private ImageComparisonService imgCompService;

    public static synchronized ImageComparisonManager getInstance() {
        if (instance == null) {
            instance = new ImageComparisonManager();
        }
        return instance;
    }

    public synchronized boolean generateComparison(File scrImage, String suiteName) {
        initComparisonManager(scrImage, suiteName);
        boolean result = compare();
        purge();
        return result;
    }

    private void initComparisonManager(File scrImage, String suiteName) {
        imgCompService = new ImageComparisonService(scrImage, suiteName);
    }

    private boolean compare() {
        return imgCompService.compare();
    }

    private void purge() {
        imgCompService.purge();
    }

    public void totalPurge() {
        File imgDir = new File("images");
        if (imgDir.exists()) {
            try {
                FileUtils.deleteDirectory(imgDir);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
