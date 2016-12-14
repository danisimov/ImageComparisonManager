package service;

import java.io.File;

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

    public synchronized boolean doComparison(File scrImage, String suiteName) {
        initComparisonManager(scrImage, suiteName);
        return compare();
    }

    private void initComparisonManager(File scrImage, String suiteName) {
        imgCompService = new ImageComparisonService(scrImage, suiteName);
    }

    private boolean compare() {
        return imgCompService.compare();
    }

    public boolean purgeFiles() {
        return imgCompService.purge();
    }

    public boolean purgeDirectories() {
        return imgCompService.totalPurge();
    }
}
