/**
 * 
 */
package main.java.ru.agile.Quaternion.Model;

/**
 * @author Clalenko Pavel
 *
 */
public class Quat {

	double scalar;
	Vec3d vec;
	
	public Quat(double scalar, double i, double j, double k) {
		this.scalar = scalar;
		this.vec = new Vec3d(i, j, k);
	}

	public Quat(double scalar, Vec3d vector) {
		this.scalar = scalar;
		this.vec = new Vec3d(vector);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(scalar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vec == null) ? 0 : vec.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Quat){
			Quat q = (Quat)obj;
			return ((this.scalar == q.scalar) && (this.vec.equals(q.vec)));
		}
		return false;
	}

	public double getScalar() {
		return scalar;
	}

	public double getI() {
		return vec.getX();
	}
	
	public double getJ() {
		return vec.getY();
	}

	public double getK() {
		return vec.getZ();
	}

	public Vec3d getVec() {
		return new Vec3d(vec);
	}

	public Quat add(Quat q2) {
		// TODO Auto-generated method stub
		return new Quat(this.scalar + q2.scalar, this.getVec().add(q2.getVec()));
	}

	public double length() {
		// TODO Auto-generated method stub
		return Math.sqrt(scalar * scalar + vec.dot(vec));
	}

	public Quat conj() {
		// TODO Auto-generated method stub
		return new Quat(scalar, vec.mul(-1));
	}
}
