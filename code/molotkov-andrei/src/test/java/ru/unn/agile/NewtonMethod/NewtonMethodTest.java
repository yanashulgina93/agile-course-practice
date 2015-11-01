package ru.unn.agile.NewtonMethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
public class NewtonMethodTest {
    private final double leftPoint;
    private final double rightPoint;
    private final double delta = 0.001;

    public NewtonMethodTest(final double leftPoint, final double rightPoint) {
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
    }

    @Parameterized.Parameters
    public static List<Object[]> isNotRoot() {
        return Arrays.asList(new Object[][]{
                {-2, -2},
                {-2.9, -1.7},
                {-4, -1}
        });
    }

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
    public void whenCanNotFindRoot() {
        NewtonMethod newtonMethod = new NewtonMethod("(x+3)*(x+3)-2=", "2*(x+3)=");
        Double root = newtonMethod.searchRoot(leftPoint, rightPoint);
        assertNull(root);
    }
}
