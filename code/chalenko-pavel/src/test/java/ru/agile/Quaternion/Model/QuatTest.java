/**
 * 
 */
package test.java.ru.agile.Quaternion.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import main.java.ru.agile.Quaternion.Model.Quat;
import main.java.ru.agile.Quaternion.Model.Vec3d;

import org.junit.Before;
import org.junit.Test;

/**
 * @author User
 *
 */
public class QuatTest {

	Quat q1;
	Quat q2;
	double eps = 0.001;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		q1 = new Quat(1, 2., 3.0, 4);
		q2 = new Quat(2, new Vec3d(1., .5, 0.25));
	}

	@Test
	public void canCreateQuatWithInitialValues() {
		assertNotNull("Can't create Vec3d with initial value", q1);
		assertNotNull("Can't create Vec3d with initial value", q2);
	}
	
	@Test
	public void areQuatEquals(){
		Quat q3 = new Quat(1.0, 2, 3.0, 4.);
		
		assertTrue("Two vectors are not equals", q1.equals(q3));
	}
	
	@Test
	public void isQuatEqualsComutative(){
		Quat q = new Quat(1, 2.0, 3.0, 4);
		
		boolean e1 = q1.equals(q);
		boolean e2 = q.equals(q1);
		
		assertEquals("Equals isn't comutative", e1, e2);
	}
	
	@Test
	public void QuatAreNotEquals(){
		Quat q3 = new Quat(1.0, 2, 3.0, 4.);
		
		assertFalse("Two quaternions are equals", q2.equals(q3));
	}
	
	@Test
	public void QuatAreNotEqualsInt(){
		int number = 12;
		
		assertFalse("Two quaternions are equals", q2.equals(number));
	}

	@Test
	public void canSetInitialValueScalar(){
		double s = q1.getScalar();
		
		assertEquals("Can't set initial scalar value", 1, s, eps);
	}
	
	@Test
	public void canSetInitialValueJ(){
		double j = q1.getJ();
		
		assertEquals("Can't set initial j value", 3.0, j, eps);
	}
	
	@Test
	public void canSetInitialValueVector(){
		Vec3d expectedVector = new Vec3d(2, 3, 4);
		
		Vec3d actualVector = q1.getVec();
		
		assertTrue("Can't set initial vector value", expectedVector.equals(actualVector));
	}
	
	@Test
	public void canAdd(){
		Quat expectedSumQuat = new Quat(3, 3, 3.5, 4.25);
		
		Quat actualSumQuat = q1.add(q2);
		
		assertTrue("Can't added vectors", actualSumQuat.equals(expectedSumQuat));
	}
	
	@Test
	public void isAddComutative(){
		Quat q12 = q1.add(q2);
		Quat q21 = q2.add(q1);
		boolean actual = q12.equals(q21);
		
		assertTrue("Quaternions add isn't comutative", actual);
	}
	
	@Test
	public void canLength(){
		double expectedLen = 5.477;
		
		double actualLen = q1.length();
		
		assertEquals("Can't calculate length", expectedLen, actualLen, eps);
	}
	
	@Test
	public void canConj(){
		Quat expectedConjQuat = new Quat(1, -2, -3, -4);
		
		Quat actualConjQuat = q1.conj();
		
		assertTrue("Can't calculate conjugate quaternion", actualConjQuat.equals(expectedConjQuat));
	}
	
	@Test
	public void isConjConjIdentical(){
		Quat actualConjConjQuat = q1.conj().conj();
		
		assertTrue("Can't calculate conjugate quaternion", actualConjConjQuat.equals(q1));
	}
}
