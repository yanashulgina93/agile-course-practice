package main.java.ru.agile.Quaternion.Model;

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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Quat){
			Quat q = (Quat)obj;
			return ((this.scalar == q.scalar) && (this.vec.equals(q.vec)));
		}
		return false;
	}
	
	public boolean equals(Quat other, double eps) {
		boolean isEqualsScalar = Math.abs(this.scalar - other.scalar) < eps;
		boolean isEqualsI = Math.abs(this.getI() - other.getI()) < eps;
		boolean isEqualsJ = Math.abs(this.getJ() - other.getJ()) < eps;
		boolean isEqualsK = Math.abs(this.getK() - other.getK()) < eps;
		return isEqualsScalar && isEqualsI && isEqualsJ && isEqualsK;
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
		return new Quat(this.scalar + q2.scalar, this.getVec().add(q2.getVec()));
	}

	public double length() {
		return Math.sqrt(scalar * scalar + vec.dot(vec));
	}

	public Quat conj() {
		return new Quat(scalar, vec.mul(-1));
	}

	public Quat mul(Quat other) {
		Vec3d tmpVecPath = other.vec.mul(this.scalar).add(this.vec.mul(other.scalar));
		return new Quat(this.scalar * other.scalar - this.vec.dot(other.vec), this.vec.mul(other.vec).add(tmpVecPath));
	}
	
	public Quat mul(double scalar) {
		return new Quat(this.scalar * scalar, this.vec.mul(scalar));
	}

	public Quat inv() {
		Quat conjQuat = this.conj();
		double len2 = this.length() * this.length();
		double coeff = 1.0 / len2;
		return conjQuat.mul(coeff);
	}

	public double getArg() {
		return Math.acos(this.getScalar() / this.length());
	}

	public static Quat createFromAxisAngle(Vec3d vec, double angle) {
		double qw = Math.cos(angle / 2);
		double qx = vec.getX() * Math.sin(angle / 2);
		double qy = vec.getY() * Math.sin(angle / 2);
		double qz = vec.getZ() * Math.sin(angle / 2);
		return new Quat(qw, qx, qy, qz);
	}

	public static Quat createFromYPR(double yaw, double pitch, double roll) {
		double sr,sp,sy,cr,cp,cy;
		sy = Math.sin(yaw * 0.5);		cy = Math.cos(yaw * 0.5);
		sp = Math.sin(pitch * 0.5);		cp = Math.cos(pitch * 0.5);
		sr = Math.sin(roll * 0.5);		cr = Math.cos(roll * 0.5);
		
		double w = (sy * sp * sr + cy * cp * cr);
		double x = (-sy * sp * cr + cy * cp * sr);
		double y = (sy * cp * sr + cy * sp * cr);
		double z = (sy * cp * cr - cy * sp * sr);
		
		return new Quat(w, x, y, z);
	}
	
	public static Quat createFromYPR(Vec3d vec) {
		double sr,sp,sy,cr,cp,cy;
		sy = Math.sin(vec.getX() * 0.5);	cy = Math.cos(vec.getX() * 0.5);
		sp = Math.sin(vec.getY() * 0.5);	cp = Math.cos(vec.getY() * 0.5);
		sr = Math.sin(vec.getZ() * 0.5);	cr = Math.cos(vec.getZ() * 0.5);
		
		double srcp = sr * cp, crsp = cr * sp;
		double crcp = cr * cp, srsp = sr * sp;
		
		double w=(crcp * cy + srsp * sy);
		double x=(srcp * cy - crsp * sy);
		double y=(crsp * cy + srcp * sy);
		double z=(crcp * sy - srsp * cy);
		
		return new Quat(w, x, y, z);
	}

}
