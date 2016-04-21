import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.ImageComparisonManager;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by danisimov on 4/20/16.
 */
public class TestImageComparator {

    private static ImageComparisonManager imageComparisonManager;
    private String suiteName;

    @BeforeClass
    public static void start() {
        imageComparisonManager = ImageComparisonManager.getInstance();
    }

    @BeforeMethod
    public void startTest(Method method) {
        suiteName = method.getName();
    }

    @Test
    public void positiveComparisonTest() {
        Assert.assertTrue(imageComparisonManager.generateComparison(new File("src/main/java/service/testImg/firstSample.png"), suiteName));
    }

    @Test
    public void negativeComparisonTest() {
        Assert.assertTrue(imageComparisonManager.generateComparison(new File("src/main/java/service/testImg/firstSample.png"), suiteName));
        Assert.assertFalse(imageComparisonManager.generateComparison(new File("src/main/java/service/testImg/secondSample.png"), suiteName));
    }

    @AfterClass
    public static void end() {
        imageComparisonManager.purgeDirectories();
    }
}
