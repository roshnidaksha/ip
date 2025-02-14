package planit.util;

import planit.task.Deadline;
import planit.task.Event;
import planit.task.Task;
import planit.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    /**
     * Constructs a Storage object to save data.
     *
     * @param filename Name of file.
     */
    public Storage(String filename) {
        file = new File(filename);
    }

    /**
     * Creates a new file for storing tasks.
     *
     * @throws IOException If unable to create file.
     */
    public void createFile() throws IOException {
        try {
            if (!file.createNewFile()) {
                throw new RuntimeException("File already exists!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to create file: " + e.getMessage());
        }
    }

    /**
     * Saves current list of tasks to a file in a human-readable format.
     *
     * @param taskList List of tasks of user.
     * @throws IOException If an error occurs while writing to file.
     */
    public void saveTaskList(ArrayList<Task> taskList) throws IOException {
        if (!file.exists()) {
            createFile();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : taskList) {
                fileWriter.write(task.toFileFormat());
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException("Unable to write to file: " + e.getMessage());
        }
    }

    /**
     * Reads contents of file and returns a list of Task objects.
     *
     * @return List of tasks.
     * @throws RuntimeException If unable to open or read file.
     */
    public ArrayList<Task> loadTaskList() throws RuntimeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskString(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException("File error: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Parse a string representation to a task object.
     *
     * @param input String representation of task from file.
     * @return Task object.
     */
    public Task parseTaskString(String input) {
        Task task;
        switch (input.charAt(0)) {
        case 'T':
            task = Todo.parseTask(input);
            break;
        case 'D':
            task = Deadline.parseTask(input);
            break;
        case 'E':
            task = Event.parseTask(input);
            break;
        default:
            task = null;
        }
        return task;
    }
}
