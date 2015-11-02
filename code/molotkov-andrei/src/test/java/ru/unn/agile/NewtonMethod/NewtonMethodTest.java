package ru.unn.agile.NewtonMethod;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Enclosed.class)
public class NewtonMethodTest {
    @RunWith(Parameterized.class)
    public static class WhenCanFindRoot {
        private final String baseFunction;
        private final String derivativeFunction;
        private final double leftPoint;
        private final double rightPoint;
        private final double expectedRoot;
        private final double delta = 0.001;

        public WhenCanFindRoot(final String baseFunction,
                               final String derivativeFunction,
                               final double expectedRoot,
                               final double leftPoint,
                               final double rightPoint) {
            this.baseFunction = baseFunction;
            this.derivativeFunction = derivativeFunction;
            this.expectedRoot = expectedRoot;
            this.leftPoint = leftPoint;
            this.rightPoint = rightPoint;
        }

        @Parameterized.Parameters
        public static List<Object[]> getFunctionsAndRangeWhenIsRoot() {
            return Arrays.asList(new Object[][]{
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -1.585, -2, 0},
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -4.414, -5, -4},
                    {"(x+3)*(x-5)+(x-2)=", "2*x-1=", 4.653, 4, 5},
                    {"(x+3)*(x-5)+(x-2)=", "2*x-1=", -3.653, -4, -3}
            });
        }

        @Test
        public void whenCanFindRoot() {
            NewtonMethod newtonMethod = new NewtonMethod(baseFunction, derivativeFunction);
            Double root = newtonMethod.searchRoot(leftPoint, rightPoint);
            assertEquals(expectedRoot, root, delta);
        }
    }
    @RunWith(Parameterized.class)
    public static class WhenCanNotFindRoot {
        private final String baseFunction;
        private final String derivativeFunction;
        private final double leftPoint;
        private final double rightPoint;

        public WhenCanNotFindRoot(final String baseFunction,
                                  final String derivativeFunction,
                                  final double leftPoint,
                                  final double rightPoint) {
            this.baseFunction = baseFunction;
            this.derivativeFunction = derivativeFunction;
            this.leftPoint = leftPoint;
            this.rightPoint = rightPoint;
        }

        @Parameterized.Parameters
        public static List<Object[]> getFunctionsAndRangeWhenNotRoot() {
            return Arrays.asList(new Object[][]{
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -2, -2},
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -2.9, -1.7},
                    {"(x+3)*(x+3)-2=", "2*(x+3)=", -4, -1}
            });
        }

        @Test
        public void whenCanNotFindRoot() {
            NewtonMethod newtonMethod = new NewtonMethod(baseFunction, derivativeFunction);
            Double root = newtonMethod.searchRoot(leftPoint, rightPoint);
            assertNull(root);
        }
    }
}
