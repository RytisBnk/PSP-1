package Entities;

public class Actions {
    private boolean canMove;
    private boolean canAttack;
    private boolean canUseAbilities;
    private boolean canUseItems;

    public Actions(boolean canMove, boolean canAttack, boolean canUseAbilities, boolean canUseItems) {
        this.canMove = canMove;
        this.canAttack = canAttack;
        this.canUseAbilities = canUseAbilities;
        this.canUseItems = canUseItems;
    }

    @Override
    public String toString() {
        return "actions{" +
                "canMove=" + canMove +
                ", canAttack=" + canAttack +
                ", canUseAbilities=" + canUseAbilities +
                ", canUseItems=" + canUseItems +
                '}';
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean canUseAbilities() {
        return canUseAbilities;
    }

    public void setCanUseAbilities(boolean canUseAbilities) {
        this.canUseAbilities = canUseAbilities;
    }

    public boolean canUseItems() {
        return canUseItems;
    }

    public void setCanUseItems(boolean canUseItems) {
        this.canUseItems = canUseItems;
    }
}
