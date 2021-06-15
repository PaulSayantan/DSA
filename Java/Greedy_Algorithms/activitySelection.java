package Greedy_Algorithms;

import java.util.Arrays;
import java.util.Comparator;

class Activity {
    int start, finish;

    public Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }
}

class ActivityComparator implements Comparator<Activity> {

    @Override
    public int compare(Activity activity1, Activity activity2) {
        return activity1.start - activity2.start;
    }
}

public class activitySelection {
    public static void main(String[] args) {

        Activity[] tasks = {
                new Activity(3, 8),
                new Activity(2, 3),
                new Activity(1, 4),
                new Activity(6, 10)
        };

        System.out.println("Maximum no of tasks possible to perform is: "+maximumTasks(tasks));
    }

    // Inside this function we implement the Greedy Method for solving Activity Selection problem
    static int maximumTasks(Activity[] activities) {

        // Sort the Tasks in ascending order of their finish time
        Arrays.sort(activities, new ActivityComparator());

        int maxTasks = 1;
        int prevEnd = activities[0].finish;

        for (Activity activity : activities) {
            if (activity.start >= prevEnd) {
                prevEnd = activity.finish;
                maxTasks++;
            }
        }

        return maxTasks;
    }
}
