package blaplafla.todolist.controllers;

import blaplafla.todolist.models.task.ListTask;

import java.io.*;

public class FileController {
    private File userfile;
    private boolean hasFile = false;
    private ListTask listTask;
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;
    private static FileController controller;

    private FileController() {
    }

    public boolean hasFile() {
        return userfile != null;
    }

    public int setFile(File userfile) {
        this.userfile = userfile;
        try {
            fileInputStream = new FileInputStream(userfile);
            objectInputStream = new ObjectInputStream(fileInputStream);
            fileOutputStream = new FileOutputStream(userfile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            hasFile = true;
            return 1;
        } catch (FileNotFoundException e) {
            return 2;
        } catch (IOException e) {
            return 3;
        }
    }

    public ListTask getListTask() {
        return listTask;
    }

    public int importListTask() {
        if (hasFile) {
            try {
                listTask = (ListTask) objectInputStream.readObject();
                return 1;
            } catch (IOException e) {
                return 3;
            } catch (ClassNotFoundException e) {
                return 4;
            }
        }
        return 2;
    }
    public int exportListTask() {
        if (hasFile) {
            try {
                objectOutputStream.writeObject(listTask);
                return 1;
            } catch (IOException e) {
                return 3;
            }
        }
        return 2;
    }

    public static FileController getInstance() {
        if (controller == null) {
            controller = new FileController();
        }
        return controller;
    }
}
