package service;

import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;

import java.io.File;

/**
 * Created by danisimov on 4/4/16.
 */
public class ImageComparisonService {
    private IImageManager imageManager;

    ImageComparisonService(File scrImage, String suiteName) {
        initImageManager(scrImage, suiteName);
    }

    public void initImageManager(File scrImage, String suiteName) {
        imageManager = new LocalImageManager(scrImage, suiteName);
    }

    public boolean compare() {
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
            System.out.println("Check images/difference.png file, to see the difference report");
            return false;
        }
    }

    public void purge() {
        if(imageManager.verifyImageExists("actual")){
            imageManager.deleteImage("actual");
        }
        if(imageManager.verifyImageExists("difference")){
            imageManager.deleteImage("difference");
        }
    }

    public void totalPurge() {
        purge();
        if(imageManager.verifyImageExists("expected")){
            imageManager.deleteImage("expected");
        }
    }
}
