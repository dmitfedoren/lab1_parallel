public class SequenceSumThread extends Thread {
    private final int id;
    private final int step;
    private volatile boolean canContinue = true;
    private long sum = 0;
    private long count = 0;

    public SequenceSumThread(int id, int step) {
        this.id = id;
        this.step = step;
    }

    @Override
    public void run() {
        long current = 0;
        while (canContinue) {
            sum += current;
            count++;
            current += step;
            try {
                Thread.sleep(10); // 10 мс
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Потік " + id + " завершено. Сума: " + sum + ", Кількість елементів: " + count);
    }

    public void stopRunning() {
        canContinue = false;
    }

    public long getSum() {
        return sum;
    }

    public long getCount() {
        return count;
    }

    public int getThreadId() {
        return id;
    }
}