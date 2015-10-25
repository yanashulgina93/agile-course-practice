/**
 * 
 */
package main.java.ru.agile.Quaternion.Model;

/**
 * @author Chalenko Pavel
 *
 */
public class Vec3d {

	private double x = 0;
	private double y = 0;
	private double z = 0;
	
	/**
	 * 
	 */
	public Vec3d(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vec3d){
			Vec3d vec = (Vec3d)obj;
			return ((this.x == vec.x) && (this.y == vec.y) && (this.z == vec.z));
		}
		return false;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return this.x;
	}
	
	public double getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	
	public double getZ() {
		// TODO Auto-generated method stub
		return this.z;
	}

	public Vec3d add(Vec3d other) {
		// TODO Auto-generated method stub
		return new Vec3d(this.x + other.x, this.y + other.y, this.z + other.z);
	}

}
