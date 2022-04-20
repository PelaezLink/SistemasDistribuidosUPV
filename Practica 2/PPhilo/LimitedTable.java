// CSD Mar 2013 Juansa Sendra

public class LimitedTable extends RegularTable { //max 4 in dinning-room
    int philosIn = 0;
    public LimitedTable(StateManager state) {super(state);}
    public synchronized void enter(int id) throws InterruptedException {
        while (philosIn > 3) {state.wenter(id); wait();}
        philosIn++;
        state.enter(id);
    }
    public synchronized void exit(int id) {
        philosIn--;
        state.exit(id);
    }
}
