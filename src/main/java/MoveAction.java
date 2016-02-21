/**
 * Created by Duffman on 20/2/16.
 */
public enum MoveAction {
    RUN(0),
    STOP(1),
    LEFT(2),
    RIGHT(3),
    BACK(4);

    private int value;

    private MoveAction(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
