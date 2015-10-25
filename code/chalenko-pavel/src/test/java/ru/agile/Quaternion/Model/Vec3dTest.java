/**
 * 
 */
package test.java.ru.agile.Quaternion.Model;

import static org.junit.Assert.*;
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
	private double eps = 0.00001;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vec1 = new Vec3d(1.2, 2.3, 3.4);
		vec2 = new Vec3d(5.6, 6.7, 7.8);
	}

	@Test
	public void canCreateVec3dWithInitialValues() {
		assertNotNull("Can't create Vec3d with initial value", vec1);
		assertNotNull("Can't create Vec3d with initial value", vec2);
	}
	
	@Test
	public void areVec3dEquals(){
		Vec3d vec3 = new Vec3d(1.2, 2.3, 3.4);
		
		assertTrue("Two vectors are not equals", vec1.equals(vec3));
	}
	
	@Test
	public void Vec3dAreNotEquals(){
		Vec3d vec3 = new Vec3d(1.2, 2.3, 3.4);
		
		assertFalse("Two vectors are equals", vec2.equals(vec3));
	}
	
	@Test
	public void Vec3dAreNotEqualsInt(){
		int number = 7;
		
		assertFalse("Two vectors are equals", vec2.equals(number));
	}
	
	@Test
	public void Vec3dAreNotEqualsParticly1(){
		Vec3d vec3 = new Vec3d(1.2, 2.4, 3.5);
		
		assertFalse("Two vectors are equals", vec1.equals(vec3));
	}
	
	@Test
	public void Vec3dAreNotEqualsParticly2(){
		Vec3d vec3 = new Vec3d(1.2, 2.3, 3.5);
		
		assertFalse("Two vectors are equals", vec1.equals(vec3));
	}
	
	@Test
	public void canSetInitialValueX(){
		assertEquals("Can't set initial x value", 1.2, vec1.getX(), eps);
	}
	
	@Test
	public void canSetInitialValueY(){
		assertEquals("Can't set initial x value", 2.3, vec1.getY(), eps);
	}
	
	@Test
	public void canSetInitialValueZ(){
		assertEquals("Can't set initial x value", 3.4, vec1.getZ(), eps);
	}
	
	@Test
	public void canAddVec3d(){
		assertTrue("Can't added vectors", (vec1.add(vec2)).equals(new Vec3d(6.8, 9, 11.2)));
	}
}
