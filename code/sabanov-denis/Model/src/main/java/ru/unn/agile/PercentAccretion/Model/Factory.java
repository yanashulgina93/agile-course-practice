package ru.unn.agile.PercentAccretion.Model;

public class Factory {
    public enum PercentAccretionOperations {
        SIMPLE_PERCENT_SUM("simple"),
        COMPLEX_PERCENT_SUM("complex");

        private String operation;

        PercentAccretionOperations(final String operation) {
            this.operation = operation;
        }

        @Override
        public String toString() {
            return operation;
        }
    }

    public PercentAccretion getPercentAccretion(final PercentAccretionOperations operation) {
        if (operation == PercentAccretionOperations.SIMPLE_PERCENT_SUM) {
            return new SimplePercentAccretion();
        } else if (operation == PercentAccretionOperations.COMPLEX_PERCENT_SUM) {
            return new ComplexPercentAccretion();
        }
        return null;
    }
}
