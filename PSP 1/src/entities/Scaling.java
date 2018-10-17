package entities;

public class Scaling {
    private Unit.Stat scalingStat;
    private double scaleRatio;
    private StatOwner statOwner;

    public enum StatOwner {
        CASTER,
        TARGET
    }

    public Scaling(Unit.Stat scalingStat, double scaleRatio, StatOwner statOwner) {
        this.scalingStat = scalingStat;
        this.scaleRatio = scaleRatio;
        this.statOwner = statOwner;
    }

    public Unit.Stat getScalingStat() {
        return scalingStat;
    }

    public void setScalingStat(Unit.Stat scalingStat) {
        this.scalingStat = scalingStat;
    }

    public double getScaleRatio() {
        return scaleRatio;
    }

    public void setScaleRatio(float scaleRatio) {
        this.scaleRatio = scaleRatio;
    }

    public StatOwner getStatOwner() {
        return statOwner;
    }

    public void setStatOwner(StatOwner statOwner) {
        this.statOwner = statOwner;
    }
}
