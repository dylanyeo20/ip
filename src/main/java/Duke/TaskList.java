package Duke;

import java.util.ArrayList;

/**
 * Represents a TaskList that keeps tracks of all existing tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public ArrayList<Task> get() {
        return this.listOfTasks;
    }

    /**
     * Deletes existing tasks from task list and update data file.
     *
     * @param storage Data file
     * @param index Index of task to be removed
     */
    public void deleteTask(Storage storage, int index) {
        String taskStatus = listOfTasks.get(index).getStatus();
        listOfTasks.remove(index);
        Task.reduceTask();
        System.out.println("Noted. I've removed this task: \n" + taskStatus);
        System.out.println("Now you have " + Task.totalTask() + " tasks in the list.");

        //Update index fields in each of the tasks
        for(int i = index; i < listOfTasks.size(); i++) {
            Task t = listOfTasks.get(i);
            t.reduceIndex();
        }

        storage.updateDataFile(this);
    }

    /**
     * Add new task into task list and update data file.
     *
     * @param storage Data file
     * @param task New task to be added
     */
    public void addTask(Storage storage, Task task) {
        this.listOfTasks.add(task);
        storage.updateDataFile(this);
    }

    /**
     * Mark task as done and update data file
     *
     * @param storage Data file
     * @param index Index of task to be mark as done
     */
    public void markAsDone(Storage storage,  int index) {
        listOfTasks.get(index).markAsDone();
        storage.updateDataFile(this);
    }

    /**
     * Mark task as not done and update data file
     *
     * @param storage Data file
     * @param index Index of task to be mark as not done
     */
    public void unmarkAsDone(Storage storage,  int index) {
        listOfTasks.get(index).unmarkAsDone();
        storage.updateDataFile(this);
    }

}
