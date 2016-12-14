import org.testng.Assert;
import org.testng.annotations.*;
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
        Assert.assertTrue(imageComparisonManager.doComparison(new File("src/main/java/service/testImg/firstSample.png"), suiteName));
    }

    @Test
    public void negativeComparisonTest() {
        Assert.assertTrue(imageComparisonManager.doComparison(new File("src/main/java/service/testImg/firstSample.png"), suiteName));
        Assert.assertFalse(imageComparisonManager.doComparison(new File("src/main/java/service/testImg/secondSample.png"), suiteName));
    }

    @Test
    public void purgeFilesTest() {
        Assert.assertTrue(imageComparisonManager.doComparison(new File("src/main/java/service/testImg/firstSample.png"), suiteName));
        Assert.assertTrue(imageComparisonManager.purgeFiles());
    }

    @AfterMethod
    public static void endTest() {
        imageComparisonManager.purgeFiles();
    }

    @AfterClass
    public static void endBundle() {
        imageComparisonManager.purgeDirectories();
    }
}
