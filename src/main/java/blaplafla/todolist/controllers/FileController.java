package blaplafla.todolist.controllers;

import blaplafla.todolist.models.task.ListTask;

import java.io.*;

public class FileController {
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

    public int setFile(String userfile) {
        this.userfile = new File(userfile);
        try {
            if (!this.userfile.exists())
                this.userfile.createNewFile();
            fileInputStream = new FileInputStream(this.userfile);
            objectInputStream = new ObjectInputStream(fileInputStream);
            fileOutputStream = new FileOutputStream(this.userfile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            hasFile = true;
            return 100;
        } catch (FileNotFoundException e) {
            return 200;
        } catch (IOException e) {
            return 300;
        }
    }

    public int unsetFile() {
        this.userfile = null;
        fileInputStream = null;
        objectInputStream = null;
        fileOutputStream = null;
        objectOutputStream = null;
        return 100;
    }

    public int importListTask() {
        if (hasFile) {
            try {
                MainController.getInstance().listTask = (ListTask) objectInputStream.readObject();
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

    public void openFile() {
        MainController.getInstance().openFileView().run();
    }

    public void saveFile() {
        if (!hasFile())
            openFile();
        MainController.getInstance().saveFileView().run();
    }

    public boolean isFolder(String file) {
        return (new File(file)).isDirectory();
    }

    public boolean isNullFile() {
        return userfile.isFile() && userfile.length() == 0;
    }
}
