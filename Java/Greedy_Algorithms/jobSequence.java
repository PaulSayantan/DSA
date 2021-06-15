package Greedy_Algorithms;

import java.util.Arrays;
import java.util.Comparator;

class Job {
    int deadline, profit;

    Job(int deadline, int profit) {
        this.deadline = deadline;
        this.profit = profit;
    }
}

class JobComparator implements Comparator<Job> {

    @Override
    public int compare(Job o1, Job o2) {
        return o2.profit - o1.profit;
    }
}


public class jobSequence {
    public static void main(String[] args) {
        Job[] jobs = {
                new Job(2, 100),
                new Job(1, 50),
                new Job(2, 10),
                new Job(1, 20),
                new Job(3, 30)
                    };

        System.out.println("Maximum Profit Earned: " + maximumProfit(jobs));
    }

    public static int maximumProfit(Job[] jobs) {
        Arrays.sort(jobs, new JobComparator());
        int profit = 0;
        int max_deadline = 0;

        for (Job job : jobs) {
            if (job.deadline > max_deadline) {
                max_deadline = job.deadline;
            }
        }

        boolean[] time_slots = new boolean[max_deadline];

        for (Job job: jobs) {
            for (int i = job.deadline - 1; i >= 0; i--) {
                if (!time_slots[i]) {
                    time_slots[i] = true;
                    profit += job.profit;
                    break;
                }
            }
        }

        return profit;
    }
}
