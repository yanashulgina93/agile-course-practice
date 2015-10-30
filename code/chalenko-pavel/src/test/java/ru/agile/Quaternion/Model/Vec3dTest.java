/**
 * 
 */
package test.java.ru.agile.Quaternion.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import main.java.ru.agile.Quaternion.Model.Vec3d;

import org.junit.Before;
import org.junit.Test;

/**
 * @author User
 *
 */
public class Vec3dTest {

	private Vec3d vec1;
	private Vec3d vec2;
	private double eps = 0.001;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vec1 = new Vec3d(1.0, 2.0, 3.0);
		vec2 = new Vec3d(0.5, 0.25, 0.33333);
	}

	@Test
	public void canCreateVec3dWithInitialValues() {
		assertNotNull("Can't create Vec3d with initial value", vec1);
		assertNotNull("Can't create Vec3d with initial value", vec2);
	}
	
	@Test
	public void areVec3dEquals(){
		Vec3d vec3 = new Vec3d(1, 2.0, 3.0);
		
		assertTrue("Two vectors are not equals", vec1.equals(vec3));
	}
	
	@Test
	public void isVec3dEqualsComutative(){
		Vec3d vec = new Vec3d(1, 2.0, 3.0);
		
		boolean e1 = vec1.equals(vec);
		boolean e2 = vec.equals(vec1);
		
		assertEquals("Equals isn't comutative", e1, e2);
	}
	
	@Test
	public void Vec3dAreNotEquals(){
		Vec3d vec3 = new Vec3d(1, 2.0, 3.0);
		
		assertFalse("Two vectors are equals", vec2.equals(vec3));
	}
	
	@Test
	public void Vec3dAreNotEqualsInt(){
		int number = 7;
		
		assertFalse("Two vectors are equals", vec2.equals(number));
	}
	
	@Test
	public void Vec3dAreNotEqualsParticly1(){
		Vec3d vec3 = new Vec3d(1, 2.0, 3.5);
		
		assertFalse("Two vectors are equals", vec1.equals(vec3));
	}
	
	@Test
	public void Vec3dAreNotEqualsParticly2(){
		Vec3d vec3 = new Vec3d(1, 2.5, 3.5);
		
		assertFalse("Two vectors are equals", vec1.equals(vec3));
	}
	
	@Test
	public void canSetInitialValueX(){
		double x = vec1.getX();
		
		assertEquals("Can't set initial x value", 1, x, eps);
	}
	
	@Test
	public void canSetInitialValueY(){
		double y = vec1.getY();
		
		assertEquals("Can't set initial y value", 2.0, y, eps);
	}
	
	@Test
	public void canSetInitialValueZ(){
		double z = vec1.getZ();
		
		assertEquals("Can't set initial z value", 3.0, z, eps);
	}
	
	@Test
	public void canAdd(){
		Vec3d expectedSumVec = new Vec3d(1.5, 2.25, 3.33333);
		
		Vec3d actualSumVec = vec1.add(vec2);
		
		assertTrue("Can't added vectors", actualSumVec.equals(expectedSumVec));
	}
	
	@Test
	public void isAddComutative(){
		Vec3d add12 = vec1.add(vec2);
		Vec3d add21 = vec2.add(vec1);
		boolean actual = add12.equals(add21);
		
		assertTrue("Vector add isn't comutative", actual);
	}
	
	@Test
	public void canDot(){
		double expectedDot = 2;
		
		double actualDot = vec1.dot(vec2);
		
		assertEquals("Can't scalar multiply", expectedDot, actualDot, eps);
	}
	
	@Test
	public void canDotZeroVector(){
		double expectedDot = 0;
		vec2 = new Vec3d(0, 0, 0);
		
		double actualDot = vec1.dot(vec2);
		
		assertEquals("Can't scalar multiply", expectedDot, actualDot, eps);
	}
	
	@Test
	public void isDotComutative(){
		double actualDot12 = vec1.dot(vec2);
		double actualDot21 = vec2.dot(vec1);
		
		assertEquals("Can't scalar multiply", actualDot12, actualDot21, eps);
	}
	
	@Test
	public void canLength(){
		double expectedLen = 3.74166;
		
		double actualLen = vec1.length();
		
		assertEquals("Can't calculate length", expectedLen, actualLen, eps);
	}
	
	@Test
	public void canLengthZeroVector(){
		double expectedLen = 0;
		vec1 = new Vec3d(0, 0, 0);
		
		double actualLen = vec1.length();
		
		assertEquals("Can't calculate length", expectedLen, actualLen, eps);
	}
	
	@Test
	public void canVecMulOrt(){
		Vec3d ortX = new Vec3d(1, 0, 0);
		Vec3d ortY = new Vec3d(0, 1, 0);
		Vec3d ortZ = new Vec3d(0, 0, 1);
		
		Vec3d actualVec = ortX.mul(ortY);
		
		assertTrue("Can't vector multiply", actualVec.equals(ortZ));
	}
	
	@Test
	public void canVecMul(){
		Vec3d expectedVec = new Vec3d(-0.08334, 1.16667, -0.75);
		
		Vec3d actualVec = vec1.mul(vec2);
		
		//assertTrue("Can't calculate length", actualVec.equals(expectedVec));
		assertEquals("Can't vector multiply: wrong i component", expectedVec.getX(), actualVec.getX(), eps);
		assertEquals("Can't vector multiply: wrong j component", expectedVec.getY(), actualVec.getY(), eps);
		assertEquals("Can't vector multiply: wrong k component", expectedVec.getZ(), actualVec.getZ(), eps);
	}
	
	@Test
	public void isVecMulNonComutative(){
		Vec3d mul12 = vec1.mul(vec2);
		Vec3d mul21 = vec2.mul(vec1);
		
		boolean actual = mul12.equals(mul21);
		
		assertFalse("Vector multiply isn't comutative", actual);
	}
	
	@Test
	public void canMul(){
		Vec3d expectedVec = new Vec3d(2.0, 4, 6);
		
		Vec3d actualVec = vec1.mul(2);
		
		assertTrue("Can't multiply vectors to scalar", actualVec.equals(expectedVec));
	}
	
	@Test
	public void canMulZero(){
		Vec3d expectedVec = new Vec3d(0, 0, 0);
		
		Vec3d actualVec = vec1.mul(0);
		
		assertTrue("Can't multiply vectors to scalar", actualVec.equals(expectedVec));
	}
	
	@Test
	public void canCopy(){
		Vec3d actualVec = new Vec3d(vec1);
		
		assertTrue("Can't copy vector", actualVec.equals(vec1));
		assertFalse("Can't copy vector", actualVec.equals(vec2));
	}
	
	@Test (expected = NullPointerException.class)
	public void canThrowExceptionWhenCopyNull(){
		Vec3d actualVec = new Vec3d(null);
		
		assertTrue("Can't copy vector", actualVec.equals(vec1));
		assertFalse("Can't copy vector", actualVec.equals(vec2));
	}
	
	@Test
	public void canNorm(){
		Vec3d expectedVec = new Vec3d(0.577, .577, .577);
		Vec3d actualVec = new Vec3d(1, 1, 1);
		
		actualVec.norm();
		
		assertEquals("Can't normalize vector", actualVec.getX(), expectedVec.getX(), eps);
		assertEquals("Can't normalize vector", actualVec.getY(), expectedVec.getY(), eps);
		assertEquals("Can't normalize vector", actualVec.getZ(), expectedVec.getZ(), eps);
	}
	
	@Test
	public void canNormOrt(){
		Vec3d expectedVec = new Vec3d(1, 0, 0);
		Vec3d actualVec = new Vec3d(1, 0, 0);
		
		actualVec.norm();
		
		assertEquals("Can't normalize vector", actualVec.getX(), expectedVec.getX(), eps);
		assertEquals("Can't normalize vector", actualVec.getY(), expectedVec.getY(), eps);
		assertEquals("Can't normalize vector", actualVec.getZ(), expectedVec.getZ(), eps);
	}
	
	@Test
	public void canNormVectorWithLength2(){
		Vec3d expectedVec = new Vec3d(0, 1, 0);
		Vec3d actualVec = new Vec3d(0, 2, 0);
		
		actualVec.norm();
		
		assertEquals("Can't normalize vector", actualVec.getX(), expectedVec.getX(), eps);
		assertEquals("Can't normalize vector", actualVec.getY(), expectedVec.getY(), eps);
		assertEquals("Can't normalize vector", actualVec.getZ(), expectedVec.getZ(), eps);
	}
	
	@Test (expected = ArithmeticException.class)
	public void canNotNorm(){
		Vec3d actualVec = new Vec3d(0., .0, .0);
		
		actualVec.norm();
		
		assertTrue(false);
	}
}
