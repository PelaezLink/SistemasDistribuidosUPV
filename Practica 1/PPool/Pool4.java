// CSD feb 2013 Juansa Sendra

public class Pool4 extends Pool { //kids cannot enter if there are instructors waiting to exit
    int i = 0;
    int k = 0;
    int i_waiting = 0;
    int ki;
    int cap;
    public void init(int ki, int cap) {
        this.ki = ki;
        this.cap = cap;
    }
    public synchronized void kidSwims() throws InterruptedException {
        while (i == 0 || k + 1 > ki * i || k + i + 1 > cap || i_waiting > 0) { //COMPLETAR la condicion
            log.waitingToSwim();//para visualizar la posicion del nadador
            wait();
        }
        k++;
        log.swimming(); //para visualizar la posicion del nadador
    }
    public synchronized void kidRests() {
        log.resting();
        k--;
        notifyAll();
    }
    public synchronized void instructorSwims() throws InterruptedException {
        while(k + i + 1 > cap) {
            log.waitingToSwim();//para visualizar la posicion del nadador
            wait();
        }
        log.swimming();
        i++;
        notifyAll();
    }
    public synchronized void instructorRests() throws InterruptedException {
        while((k > 0 && i == 1) || ki * (i - 1) < k){
            log.waitingToRest();
            i_waiting++;
            wait();
            i_waiting--;
        }
        i--;
        log.resting();
        notifyAll();
    }
}
