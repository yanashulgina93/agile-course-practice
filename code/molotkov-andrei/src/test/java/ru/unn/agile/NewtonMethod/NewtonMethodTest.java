package ru.unn.agile.NewtonMethod;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NewtonMethodTest {
    private final double delta = 0.001;

    @Test
    public void canInitialStartValuesNetonMethod() {
        NewtonMethod newtonMethod = new NewtonMethod("(x+3)*(x+3)-2=", "2*(x+3)=");
        assertNotNull(newtonMethod);
    }

    @Test
    public void canFindRootInIntervalConteiningThisRoot() {
        NewtonMethod newtonMethod = new NewtonMethod("(x+3)*(x+3)-2=", "2*(x+3)=");
        Double root = newtonMethod.searchRoot(-2, 0);
        assertEquals(-1.585, root, delta);
    }

    @Test
    public void canFindRootInEmptyInterval() {
        NewtonMethod newtonMethod = new NewtonMethod("(x+3)*(x+3)-2=", "2*(x+3)=");
        Double root = newtonMethod.searchRoot(-2, -2);
        assertNull(root);
    }

    @Test
    public void canFindRootInIntervalWithoutRoot() {
        NewtonMethod newtonMethod = new NewtonMethod("(x+3)*(x+3)-2=", "2*(x+3)=");
        Double root = newtonMethod.searchRoot(-2.9, -1.7);
        assertNull(root);
    }

    @Test
    public void canFindRootNotMonotonicFunction() {
        NewtonMethod newtonMethod = new NewtonMethod("(x+3)*(x+3)-2=", "2*(x+3)=");
        Double root = newtonMethod.searchRoot(-4, -1);
        assertNull(root);
    }
}
