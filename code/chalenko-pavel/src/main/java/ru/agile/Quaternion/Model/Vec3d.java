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

	public Vec3d(Vec3d vec) {
		if (vec != null){
			this.x = vec.x;
			this.y = vec.y;
			this.z = vec.z;
		} else {
			throw new NullPointerException();
		}
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
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}

	public Vec3d add(Vec3d other) {
		return new Vec3d(this.x + other.x, this.y + other.y, this.z + other.z);
	}

	public double dot(Vec3d other) {
		return this.x * other.x + this.y * other.y + this.z * other.z;
	}

	public double length() {
		return Math.sqrt(this.dot(this));
	}

	public Vec3d mul(Vec3d other) {
		double tmpX = this.y * other.z - this.z * other.y;
		double tmpY = - this.x * other.z + this.z * other.x;
		double tmpZ = this.x * other.y - this.y * other.x;
		return new Vec3d(tmpX, tmpY, tmpZ);
	}

	public Vec3d mul(double scalar) {
		return new Vec3d(scalar * this.x, scalar * this.y, scalar * this.z);
	}

	public void norm() {
		double len = length();
		if (len != 0) {
			this.x /= len;
			this.y /= len;
			this.z /= len;
		} else {
			throw new ArithmeticException("Division by zero");
		}
	}
}
