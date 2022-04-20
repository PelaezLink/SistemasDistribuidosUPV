// CSD feb 2015 Juansa Sendra

public class Pool2 extends Pool{ //max kids/instructor
    int i = 0;
    int k = 0;
    int ki;
    int cap;
    public void init(int ki, int cap) {
        this.ki = ki;
        this.cap = cap;
    }
    public synchronized void kidSwims() throws InterruptedException {
        while (i == 0 || k + 1 > ki * i) { //COMPLETAR la condicion
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
    public synchronized void instructorSwims() {
        log.swimming();
        i++;
        notifyAll();
    }
    public synchronized void instructorRests() throws InterruptedException {
        while((k > 0 && i == 1) || ki * (i - 1) < k){
            log.waitingToRest();
            wait();
        }
        i--;
        log.resting();
    }
}
