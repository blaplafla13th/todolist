package blaplafla.todolist.controllers;

import blaplafla.todolist.models.task.ListTask;

import java.io.*;

public class FileController{
    private File userfile;
    private boolean hasFile = false;
    private FileInputStream fileInputStream;
    private ObjectInputStream objectInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;

    public FileController() {
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
            return 100;
        } catch (FileNotFoundException e) {
            return 200;
        } catch (IOException e) {
            return 300;
        }
    }

    public int importListTask() {
        if (hasFile) {
            try {
                MainController.getInstance().listTask= (ListTask) objectInputStream.readObject();
                return 100;
            } catch (IOException e) {
                return 300;
            } catch (ClassNotFoundException e) {
                return 400;
            }
        }
        return 200;
    }
    public int exportListTask() {
        if (hasFile) {
            try {
                objectOutputStream.writeObject(MainController.getInstance().listTask);
                return 100;
            } catch (IOException e) {
                return 300;
            }
        }
        return 200;
    }

}
