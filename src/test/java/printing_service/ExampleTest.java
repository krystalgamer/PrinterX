package printing_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ExampleTest {


    @Test
    public void testExample(){

        Example e = new Example();
        Assertions.assertEquals(0, e.getInner());
        e.incrementInner();
        Assertions.assertEquals(1, e.getInner());
        int value = e.getAndIncrementInner();
        Assertions.assertEquals(1, value);
    }

    @Test
    public void testUntestedSideEffect(){
        //TODO
    }


    @Test
    public void testExample2(){
        Assertions.assertEquals("HOT", Example.horOrCold(40));
        Assertions.assertEquals("COLD", Example.horOrCold(3));
    }

    @Test
    public void testMissingBoundary(){
        //TODO
    }

    @Test
    public void testExample3(){

        Example mock = Mockito.mock(Example.class);
        Example.otherObject(mock, false);
        verify(mock, never()).getInner();

        Example mock2 = Mockito.mock(Example.class);
        Example.otherObject(mock, true);
        verify(mock, times(1)).getInner();
    }


    @Test
    public void testMyopicMockist(){
        //TODO
    }
}
