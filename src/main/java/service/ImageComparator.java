package service;

import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;

import java.io.File;

/**
 * Created by danisimov on 28.02.2016.
 */
public class ImageComparator {
    FileManager fileManager;

    public void initFileManager(File scrFile) {
        fileManager = new FileManager(scrFile);
    }

    public boolean compare() {
        CompareCmd compare = new CompareCmd();
        compare.setErrorConsumer(StandardStream.STDERR);
        IMOperation cmpOp = new IMOperation();

        // Set the compare metric
        cmpOp.metric("mae");

        cmpOp.addImage(fileManager.getPath("expected"));
        cmpOp.addImage(fileManager.getPath("actual"));
        cmpOp.addImage(fileManager.getPath("difference"));
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
        fileManager.flush();
    }
}
