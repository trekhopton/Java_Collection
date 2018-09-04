public class EnhancedReactionController implements Controller{

    final int INSERTCOIN = 0;
    final int PRESSGO = 1;
    final int WAIT = 2;
    final int STOPWATCH = 3;
    final int FINALTIME = 4;
    int state;
    int timer;
    int score;
    Random cRand;
    Gui cGui;

    //Connect controller to gui
    //(This method will be called before ANY other methods)
    public void connect(Gui gui, Random rng){
        cGui = gui;
        cRand = rng;
    }

    //Called to initialise the controller
    public void init(){
        state = INSERTCOIN;
        timer = 0;
        score = 0;
    }

    //Called whenever a coin is inserted into the machine
    public void coinInserted(){
        switch(state){
            case INSERTCOIN:
                state = PRESSGO;
                break;
            default:
                break;
        }
    }

    //Called whenever the go/stop button is pressed
    public void goStopPressed(){
        switch(state){
            case PRESSGO:
                timer = cRand.getRandom(100,250);
                state = WAIT;
                break;
            case WAIT:
                state = INSERTCOIN;
                break;
            case STOPWATCH:
                score = timer;
                timer = 300;
                state = FINALTIME;
                break;
            case FINALTIME:
                state = INSERTCOIN;
                break;
            default:
                break;
        }
    }

    //Called to deliver a TICK to the controller
    public void tick(){
        switch(state){
            case INSERTCOIN:
                cGui.setDisplay("Insert coin");
                break;
            case PRESSGO:
                cGui.setDisplay("Press GO!");
                break;
            case WAIT:
                if(timer > 0){
                    timer -= 1;
                } else if(timer == 0){
                    state = STOPWATCH;
                }
                cGui.setDisplay("Wait...");
                break;
            case STOPWATCH:
                if(timer < 200){
                    timer += 1;
                } else if(timer == 200){
                    score = timer;
                    timer = 300;
                    state = FINALTIME;
                }
                cGui.setDisplay(Integer.toString(timer));
                break;
            case FINALTIME:
                if(timer > 0){
                    timer -= 1;
                } else if(timer == 0){
                    state = INSERTCOIN;
                }
                cGui.setDisplay(Integer.toString(score));
                break;
            default:
                break;
        }

    }

}