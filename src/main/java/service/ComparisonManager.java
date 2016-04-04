package service;

import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;

import java.io.File;

/**
 * Created by danisimov on 4/4/16.
 */
class ComparisonManager {
    IImageManager imageManager;

    public void initImageManager(File scrImage) {
        imageManager = new LocalImageManager(scrImage);
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

    public void flush() {
        imageManager.flush();
    }
}
