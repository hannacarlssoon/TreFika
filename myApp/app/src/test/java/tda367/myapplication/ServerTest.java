package tda367.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;

import tda367.myapplication.service.Server;

/**
 * @author Sara Kitzing
 */

public class ServerTest {

    @Test
    public void testServerComp() {
        Server s = new Server("127.0.0.1");
        s.setUserCode("for(int i = 0; i < 5; i++){ print(i); }");
        s.startRunning();
        Boolean check = s.getCompiledCode().equals("0 1 2 3 4");
        assertEquals(check, true);

    }

    @Test
    public void testServerErr() {
        Server s = new Server("127.0.0.1");
        s.setUserCode("for(int i = 0; i < 5; i++{ print(i); }");
        s.startRunning();
        Boolean check = s.getCompiledCode().equals("Error");
        assertEquals(check, true);
    }

}
