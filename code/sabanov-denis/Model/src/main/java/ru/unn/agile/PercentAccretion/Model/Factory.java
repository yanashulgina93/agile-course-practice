package ru.unn.agile.PercentAccretion.Model;

public class Factory {
    public PercentAccretion getPercentAccretion(final String id) {
        if (id == "simple") {
            return new SimplePercentAccretion();
        } else if (id == "complex") {
            return new ComplexPercentAccretion();
        }
        return null;
    }
}
