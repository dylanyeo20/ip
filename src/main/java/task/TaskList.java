package task;

import java.io.IOException;
import java.util.*;

import duke.Storage;


/**
 * Represents a TaskList that keeps tracks of all existing tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;
    private TreeSet<Task> upcomingTasks;

    /**
     * Initiates listofTasks and upcoming tasks
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
        populateUpComingTasks();
    }

    /**
     * Adds all upcoming tasks (within 7 days) to upcomingTasks List
     */
    public void populateUpComingTasks() {
        this.upcomingTasks = new TreeSet<>(Comparator.comparingInt(Task::getIndex));
        for (int index = 0; index < listOfTasks.size(); index++) {
            Task t = listOfTasks.get(index);
            if (t.getClass().getSimpleName().equals("Todos")) {
                continue;
            }
            if (t.isUpcoming()) {
                upcomingTasks.add(t);
            }
        }
    }


    public ArrayList<Task> get() {
        return this.listOfTasks;
    }

    public ArrayList<Task> getUpcomingTasks() {
        ArrayList<Task> list = new ArrayList<>(upcomingTasks);

        return list;
    }

    /**
     * Deletes existing tasks from task list and update data file.
     *
     * @param storage Data file
     * @param index   Index of task to be removed
     */
    public Task deleteTask(Storage storage, int index) throws IOException {
        Task task = listOfTasks.get(index);
        upcomingTasks.remove(task);
        listOfTasks.remove(index);
        Task.reduceTask();

        //Update index fields in each of the tasks
        for (int i = index; i < listOfTasks.size(); i++) {
            Task t = listOfTasks.get(i);
            t.reduceIndex();
        }

        storage.updateDataFile(this);
        return task;
    }

    /**
     * Add new task into task list and update data file.
     *
     * @param storage Data file
     * @param task    New task to be added
     */
    public void addTask(Storage storage, Task task) throws IOException {
        this.listOfTasks.add(task);
        if (task.isUpcoming()) {
            upcomingTasks.add(task);
        }
        storage.updateDataFile(this);
    }

    /**
     * Mark task as done and update data file
     *
     * @param storage Data file
     * @param index   Index of task to be mark as done
     */
    public Task markAsDone(Storage storage, int index) throws IOException {
        Task task = listOfTasks.get(index);
        task.markAsDone();
        storage.updateDataFile(this);
        return task;
    }


    /**
     * Mark task as not done and update data file
     *
     * @param storage Data file
     * @param index   Index of task to be mark as not done
     */
    public Task unmarkAsDone(Storage storage, int index) throws IOException {
        Task task = listOfTasks.get(index);
        task.unmarkAsDone();
        storage.updateDataFile(this);
        return task;
    }

    /**
     * Returns a list of tasks containing searched name
     */
    public ArrayList<Task> findTask(String searchedName) throws IOException {
        ArrayList<Task> resultList = new ArrayList<>();
        for (Task task : listOfTasks) {
            if (task.name.contains(searchedName.trim())) {
                resultList.add(task);
            }
        }
        return resultList;
    }

}
