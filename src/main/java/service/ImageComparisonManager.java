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
        imgCompService.totalPurge();
    }
}
