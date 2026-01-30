package Duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> listOfTasks;
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public ArrayList<Task> get() {
        return this.listOfTasks;
    }

    //TASKLIST WITH STORAGE  NEED ADDTASK in TASKLIST
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

    public void addTask(Storage storage, Task task) {
        this.listOfTasks.add(task);
        storage.updateDataFile(this);
    }

    public void markAsDone(Storage storage,  int index) {
        listOfTasks.get(index).markAsDone();
        storage.updateDataFile(this);
    }

    public void unmarkAsDone(Storage storage,  int index) {
        listOfTasks.get(index).unmarkAsDone();
        storage.updateDataFile(this);
    }





}
