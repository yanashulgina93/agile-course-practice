/**
 * Created by Dmitriy on 29.10.2015.
 */
class ComplexNumber implements Comparable<ComplexNumber> {
    public ComplexNumber(final double real, final double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public void setReal(final double real) {
         this.real = real;
    }

    public void setImaginary(final double imaginary) {
         this.imaginary = imaginary;
    }

    @Override
    public boolean equals(final Object object) {
        ComplexNumber number = (ComplexNumber) object;
        return this.real == number.real && this.imaginary == number.imaginary;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(final ComplexNumber number) {
        if (this.real < number.real) {
            return -1;
        }

        if (this.real > number.real) {
            return 1;
        }

        if (this.imaginary < number.imaginary) {
            return -1;
        }

        if (this.imaginary > number.imaginary) {
            return 1;
        }

        return 0;
    }

    private double real;
    private double imaginary;
}
