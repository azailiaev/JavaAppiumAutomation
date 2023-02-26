import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetClassNumber(){
        if(getClassNumber() > 45) {
            System.out.println("Status: passed " +
                    "\n Expected: 'getClassNumber' > 45" +
                    "\n Actual: 'getClassNumber' = " + getClassNumber());
        } else {
            Assert.assertFalse("\n Status: failed " +
                            "\n Expected: 'getClassNumber' > 45" +
                            "\n Actual: 'getClassNumber' = " + getClassNumber(),
                    getClassNumber() <= 45);
        }
    }
}