package anthony.cd.app.pdc;

import org.junit.Test;

import java.io.File;

public class TestDir {
    @Test
    public void testDir(){
        File f=new File("D:/tmpfile/2017-12-05/");
        System.out.println(f.exists());
    }
}
