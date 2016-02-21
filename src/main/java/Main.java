import com.leapmotion.leap.*;

public class Main {

    private static boolean leftOpened, rightOpened,backAction;

    public static void main(String[] args) {
        Controller controller = new Controller();

        while(true) {
            Frame frame = controller.frame(); // controller is a Controller object
            HandList hands = frame.hands();
            if(hands.count()==2){
                backAction=true;
                for(Hand hand : hands){
                    boolean handOpened=true;
                    FingerList fingers = hand.fingers();
                    for(Finger finger : fingers){
                        if(!finger.isExtended()) handOpened=false;

                        if(backAction) {
                            if (finger.type() == Finger.Type.TYPE_THUMB && finger.isExtended())
                                backAction = false;
                            if (finger.type() == Finger.Type.TYPE_INDEX && !finger.isExtended())
                                backAction = false;
                            if (finger.type() == Finger.Type.TYPE_MIDDLE && finger.isExtended())
                                backAction = false;
                            if (finger.type() == Finger.Type.TYPE_RING && finger.isExtended())
                                backAction = false;
                            if (finger.type() == Finger.Type.TYPE_PINKY && !finger.isExtended())
                                backAction = false;
                        }
                    }

                    if(hand.isLeft()) leftOpened = handOpened;
                    else rightOpened = handOpened;
                }

                if(backAction) back();
                else if(leftOpened && rightOpened) run();
                else if(!leftOpened && !rightOpened) stop();
                else if(leftOpened && !rightOpened) right();
                else if(!leftOpened && rightOpened) left();
                //TODO: back
            }else stop();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Hand firstHand = hands.get(0);

    }

    public static void run(){
        System.out.println("----------CAR ACTION -> RUN");
        ServerAdapter.getInstance().sendAction(MoveAction.RUN);
    }

    public static void stop(){
        System.out.println("----------CAR ACTION -> STOP");
        ServerAdapter.getInstance().sendAction(MoveAction.STOP);
    }

    public static void left(){
        System.out.println("----------CAR ACTION -> LEFT");
        ServerAdapter.getInstance().sendAction(MoveAction.LEFT);
    }

    public static void right(){
        System.out.println("----------CAR ACTION -> RIGHT");
        ServerAdapter.getInstance().sendAction(MoveAction.RIGHT);
    }

    public static void back(){
        System.out.println("----------CAR ACTION -> BACK");
        ServerAdapter.getInstance().sendAction(MoveAction.BACK);
    }
}
