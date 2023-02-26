import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetClassString(){
        String substr1 = "hello";
        String substr2 = "Hello";
        if(getClassString().contains(substr1)) {
            System.out.println("String contains 'hello'");
        } else if (getClassString().contains(substr2)){
            System.out.println("String contains 'Hello'");
        } else {
            Assert.fail("\nThere is neither 'hello' nor 'Hello' in the string");
        }
    }
}