package service;

import adapter.ComparisonManager;

import java.io.File;

/**
 * Created by danisimov on 28.02.2016.
 */
public class ImageComparator {
    ComparisonManager comparisonManager;

    public ImageComparator() {
        comparisonManager = new ComparisonManager();
    }

    public void initComparisonManager(File scrImage) {
        comparisonManager.initImageManager(scrImage);
    }

    public boolean compare() {
        return comparisonManager.compare();
    }

    public void flush() {
        comparisonManager.flush();
    }
}
