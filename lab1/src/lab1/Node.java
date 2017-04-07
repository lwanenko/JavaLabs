package lab1;

public class Node {
    private int[] roole;
    private int teg;
    private LlkContext firstFollowK;

    public Node(int[] roole1, int len) {
        this.roole = new int[len];
        for (int ii = 0; ii < len; ++ii) {
            this.roole[ii] = roole1[ii];
        }
        this.teg = 0;
        this.firstFollowK = null;
    }

    public void addFirstFollowK(LlkContext rezult) {
        this.firstFollowK = rezult;
    }

    public LlkContext getFirstFollowK() {
        return this.firstFollowK;
    }

    public int[] getRoole() {
        return this.roole;
    }

    public int getTeg() {
        return this.teg;
    }

    public void setTeg(int val) {
        this.teg = val;
    }
}