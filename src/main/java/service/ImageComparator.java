package service;

import java.io.File;

/**
 * Created by danisimov on 28.02.2016.
 */
public class ImageComparator {
    private static ImageComparator instance;
    private ComparisonManager comparisonManager;

    public static synchronized ImageComparator getInstance() {
        if (instance == null) {
            instance = new ImageComparator();
        }
        return instance;
    }

    public synchronized boolean generateComparison(File scrImage, String suiteName) {
        initComparisonManager(scrImage, suiteName);
        boolean result = compare();
        flush();
        return result;
    }

    private void initComparisonManager(File scrImage, String suiteName) {
        comparisonManager = new ComparisonManager(scrImage, suiteName);
    }

    private boolean compare() {
        return comparisonManager.compare();
    }

    private void flush() {
        comparisonManager.flush();
    }
}
