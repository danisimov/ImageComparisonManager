package service;

import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;

import java.io.File;

/**
 * Created by danisimov on 4/4/16.
 */
class ImageComparisonService {

    private IImageManager imageManager;

    ImageComparisonService(File scrImage, String suiteName) {
        initImageManager(scrImage, suiteName);
    }

    private void initImageManager(File scrImage, String suiteName) {
        imageManager = new LocalImageManager(scrImage, suiteName);
    }

    boolean compare() {
        CompareCmd compare = new CompareCmd();
        compare.setErrorConsumer(StandardStream.STDERR);
        IMOperation cmpOp = new IMOperation();

        // Set the compare metric
        cmpOp.metric("mae");

        cmpOp.addImage(imageManager.getImagePath("expected"));
        cmpOp.addImage(imageManager.getImagePath("actual"));
        cmpOp.addImage(imageManager.getImagePath("difference"));
        try {
            compare.run(cmpOp);
            return true;
        }
        catch (Exception ex) {
            System.out.println("Check images/$testSuiteName/difference.png file, to see the difference report and read the message above for details");
            return false;
        }
    }

    boolean purge() {
        boolean result = false;
        if(imageManager.verifyImageExists("actual")){
            result = imageManager.deleteImage("actual");
        }
        if(imageManager.verifyImageExists("difference")){
            result = result & imageManager.deleteImage("difference");
        }
        return result;
    }

    boolean totalPurge() {
        return imageManager.deleteDirectory();
    }
}
