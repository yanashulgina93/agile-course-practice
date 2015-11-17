package ru.unn.agile.IntersectionFinder.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector3DTestParse {
    private String vectorStr;
    private Vector3D vector;

    @Before
    public void setUp() {
        vector = new Vector3D(5.3, 7.0, -6.0);
    }

    @Test
    public void canParseCorrectString() {
        vectorStr = " 1  ;-2 ; 3.2";

        vector.parse(vectorStr);

        assertEquals(vector, new Vector3D(1.0, -2.0, 3.2));
    }

    @Test
    public void canCreateVectorFromString() {
        vectorStr = " 1  ;-2 ; 3.2";

        vector = new Vector3D(vectorStr);

        assertEquals(vector, new Vector3D(1.0, -2.0, 3.2));
    }

    @Test(expected = Exception.class)
    public void cantParseEmptyString() {
        vectorStr = "";

        vector.parse(vectorStr);
    }

    @Test(expected = Exception.class)
    public void cantParseWrongStringWithLetter() {
        vectorStr = " 1a  ; 2; 3";

        vector.parse(vectorStr);
    }

    @Test(expected = Exception.class)
    public void cantParseWrongStringWithSpaceAtTheMiddle() {
        vectorStr = "1; 2 2; 3";

        vector.parse(vectorStr);
    }

    @Test(expected = Exception.class)
    public void cantParseWrongStringWithWrongParametersNumber() {
        vectorStr = "1;2;";

        vector.parse(vectorStr);
    }

    @Test
    public void canConvertToString() {
        assertEquals(vector.toString(), "5.3; 7.0; -6.0");
    }
}
