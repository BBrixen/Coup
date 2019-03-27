package Cards;
public class Card {

    protected String block,action;
    protected boolean secret, dead;
    protected String name;

    public Card(String name, String action, String block) {
        this.name = name;
        this.block = block;
        this.action = action;
        this.secret = true;
        this.dead = false;
    }

    public String getBlock() {
        return block;
    }
    public void setBlock(String blocks) {
        this.block = blocks;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String actions) {
        this.action = actions;
    }
    public boolean isSecret() {
        return secret;
    }
    public void setSecret(boolean secret) {
        this.secret = secret;
    }
    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
