package planit.util;

import planit.messages.PlanitExceptionMessages;
import planit.task.Deadline;
import planit.task.Event;
import planit.task.Task;
import planit.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents a storage system for Planit task management system.
 * It provides methods to save and load tasks from a file.
 */
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
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new IOException(String.format(PlanitExceptionMessages.UNABLE_TO_CREATE_DIR, file.getParentFile()));
                }
            }
            if (!file.createNewFile()) {
                throw new RuntimeException(PlanitExceptionMessages.DUPLICATE_FILE);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format(PlanitExceptionMessages.UNABLE_TO_CREATE_FILE, e.getMessage()));
        }
    }

    /**
     * Saves current list of tasks to a file in a human-readable format.
     *
     * @param tasksMap List of tasks of user.
     * @throws IOException If an error occurs while writing to file.
     */
    public void saveTaskList(HashMap<String, ArrayList<Task>> tasksMap) throws IOException {
        if (!file.exists()) {
            createFile();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (String taskType : tasksMap.keySet()) {
                for (Task task : tasksMap.get(taskType)) {
                    fileWriter.write(task.toFileFormat());
                    fileWriter.write("\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException(String.format(PlanitExceptionMessages.UNABLE_TO_WRITE_FILE, e.getMessage()));
        }
    }

    /**
     * Reads contents of file and returns a list of Task objects.
     *
     * @return List of tasks.
     * @throws IOException If unable to open or read file.
     */
    public ArrayList<Task> loadTaskList() throws IOException {
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
            throw new IOException(String.format(PlanitExceptionMessages.UNABLE_TO_READ_FILE, e.getMessage()));
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
