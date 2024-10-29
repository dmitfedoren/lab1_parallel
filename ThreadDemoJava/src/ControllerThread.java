import java.util.List;

public class ControllerThread extends Thread {
    private final List<SequenceSumThread> threads;
    private final long[] sleepTimes; // Час затримки для кожного потоку у мілісекундах

    public ControllerThread(List<SequenceSumThread> threads, long[] sleepTimes) {
        this.threads = threads;
        this.sleepTimes = sleepTimes;
    }

    @Override
    public void run() {
        for (int i = 0; i < threads.size(); i++) {
            try {
                Thread.sleep(sleepTimes[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threads.get(i).stopRunning();
            System.out.println("Керуючий потік: Потік " + threads.get(i).getThreadId() + " отримав сигнал на зупинку.");
        }
    }
}