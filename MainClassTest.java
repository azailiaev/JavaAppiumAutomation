import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber(){
        if(getLocalNumber() == 14) {
            System.out.println("Status: passed " +
                    "\n Expected: 'getLocalNumber' = 14" +
                    "\n Actual: 'getLocalNumber' = 14");
        } else {
            Assert.assertFalse("\n Status: failed " +
                    "\n Expected: 'getLocalNumber' = 14" +
                    "\n Actual: 'getLocalNumber' = " + getLocalNumber(),
                    getLocalNumber() != 14);
        }
    }
}
