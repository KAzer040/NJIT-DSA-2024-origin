package oy.tol.tra;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class DailyTasks {

    private QueueInterface<String> dailyTaskQueue = null;
    private Timer timer = null;
    private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;

    private DailyTasks() {
    }

    public static void main(String[] args) {
        DailyTasks tasks = new DailyTasks();
        tasks.run();
    }

    private void run() {
        try {
            dailyTaskQueue = new QueueImplementation<>(); // Create a new queue for daily tasks
            readTasks(); // Read the tasks for today

            timer = new Timer(); // Create Java Timer object
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!dailyTaskQueue.isEmpty()) { // Check if there are tasks in the queue
                        String task = dailyTaskQueue.dequeue(); // Dequeue the task
                        System.out.println(task); // Print the task
                    } else {
                        timer.cancel(); // Cancel the timer if there are no tasks
                    }
                }
            }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
        } catch (IOException e) {
            System.out.println("Something went wrong :( " + e.getLocalizedMessage());
        }
    }

    private void readTasks() throws IOException {
        String tasks;
        tasks = new String(getClass().getClassLoader().getResourceAsStream("DailyTasks.txt").readAllBytes());
        String[] allTasks = tasks.split("\\r?\\n");
        for (String task : allTasks) {
            dailyTaskQueue.enqueue(task); // Enqueue the task to the Queue implementation
        }
        System.out.println("Number of tasks in the queue: " + dailyTaskQueue.size()); // Print the number of tasks
    }
}
