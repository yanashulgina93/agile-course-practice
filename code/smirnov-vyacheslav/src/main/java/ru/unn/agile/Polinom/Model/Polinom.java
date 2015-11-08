package main.java.ru.unn.agile.Polinom.Model;

import java.util.ArrayList;

public class Polinom {
	private Polinom residue;
	private double[] ratio;
	private static final int MAX_SIZE = 200;

	public Polinom() {
		ratio = new double[MAX_SIZE];
		residue = new Polinom();
	}

	public Polinom(double[] value) {
		ratio = new double[MAX_SIZE];
		for(int i = 0; i < value.length; i+= 2)
			ratio[value[i]] = value[i + 1];
		residue = new Polinom();
	}

	public double[] value() {
		if (getHighDegree == 0)
			return {0.0, 0.0}
		double[] value = new double[getMonomNumber * 2];
		int counter = 0;
		for (int i = MAX_SIZE; i >= 0; i--)
			if (ratio[i] != 0.0){
				value[counter] = i;
				value[++counter++] = ratio[i];
			}
		return value;
	}

	public void add(Polinom operand) {
		double[] summand = operand.value();
		for(int i = 0; i < summand.length; i+= 2)
			ratio[summand[i]]+= summand[i + 1];
	}

	public void subtract(Polinom operand) {
		double[] subtracted = operand.value();
		for(int i = 0; i < subtracted.length; i+= 2)
			ratio[subtracted[i]]-= subtracted[i + 1];
	}

	public void multiply(Polinom operand) {
		double[] firstMultiplier = value();
		double[] secondMultiplier = operand.value();
		ratio = new double[MAX_SIZE];
		for(int i = 0; i < firstMultiplier.length; i+= 2)
			for(int j = 0; j < secondMultiplier.length; j+= 2){
				ratio[firstMultiplier[i] + secondMultiplier[j]]+= firstMultiplier[i + 1] * secondMultiplier[j + 1];

			}
	}

	public void divide(Polinom operand) {
		int dividendHighDegree = getHighDegree();
		int dividerHighDegree = operand.getHighDegree();
		double[] divider = operand.value();
		Polinom quotient = new Polinom();

		if (divider == {0.0, 0.0}) {
			throw new IllegalArgumentException("Divider can't be zero!");
		}

		if (dividerHighDegree > dividendHighDegree) {
			throw new IllegalArgumentException("Divider's degree can't be large than dividend's!");
		}
		
		while (dividendHighDegree >= dividerHighDegree) {
			Polinom stepQuotient = new Polinom({dividendHighDegree - dividerHighDegree, ratio[dividendHighDegree] / divider[1]});
			quotient.add(stepQuotient);
			stepQuotient.multiply(operand);
			subtract(stepQuotient);
			dividendHighDegree = getHighDegree();
		}
		residue.add(this);
		subtract(residue);
		add(quotient);
	}

	private int getHighDegree() {
		for (int i = MAX_SIZE - 1; i >= 0; i--)
			if (ratio[i] != 0.0)
				return i;
		return 0;
	}

	private int getMonomNumber() {
		int monomNumber = 0;
		for (int i = 0; i < MAX_SIZE; i++)
			if (ratio[i] != 0.0)
				monomNumber++;
		return monomNumber;
	}
}