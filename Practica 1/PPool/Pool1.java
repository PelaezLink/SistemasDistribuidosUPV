// CSD feb 2015 Juansa Sendra

public class Pool1 extends Pool {
    int i = 0;
    int k = 0;
    public void init(int ki, int cap) {}
    public synchronized void kidSwims() throws InterruptedException {
        while (i == 0) { //COMPLETAR la condicion
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
        while(k > 0 && i == 1){
            log.waitingToRest();
            wait();
        }
        i--;
        log.resting();
    }
}
