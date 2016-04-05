package imageManagement;

import java.io.File;
import java.util.HashMap;

/**
 * Created by danisimov on 3/23/16.
 */
class LocalImageStorage {
    HashMap<String, File> filesCollection;

    LocalImageStorage(File scrImage) {
        filesCollection = new HashMap<String, File>(){
            {
                put("expected", new File("images/expected.png"));
                put("actual", new File("images/actual.png"));
                put("difference", new File("images/difference.png"));
            }
        };
    }
}
