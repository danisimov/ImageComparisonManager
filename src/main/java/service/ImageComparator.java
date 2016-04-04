package service;

import java.io.File;

/**
 * Created by danisimov on 28.02.2016.
 */
public class ImageComparator {
    ComparisonManager comparisonManager;

    public void initComparisonManager(File scrImage) {
        comparisonManager = new ComparisonManager();
        comparisonManager.initImageManager(scrImage);
    }

    public boolean compare() {
        return comparisonManager.compare();
    }

    public void flush() {
        comparisonManager.flush();
    }
}
