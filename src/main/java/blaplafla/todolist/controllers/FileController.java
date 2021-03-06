package blaplafla.todolist.controllers;

import blaplafla.todolist.models.task.ListTask;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public class FileController {
    private File userfile;
    private boolean hasFile;
    private ObjectInputStream objectInputStream;

    public boolean hasFile() {
        return userfile != null;
    }

    public int setFile(String userfile) {
        this.userfile = new File(userfile);
        try {
            if (!this.userfile.exists() || this.userfile.length() == 0) {
                this.userfile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(this.userfile);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(null);
            }
            FileInputStream fileInputStream = new FileInputStream(this.userfile);
            objectInputStream = new ObjectInputStream(fileInputStream);
            hasFile = true;
            return 100;
        }
        catch (FileNotFoundException e) {
            return 200;
        }
        catch (IOException e) {
            return 300;
        }
    }

    public int unsetFile() {
        userfile = null;
        return 100;
    }

    public int importListTask() {
        if (hasFile) {
            try {
                Object object = objectInputStream.readObject();
                if (object != null) {
                    MainController.getInstance().listTask = (ListTask) object;
                }
                return 100;
            }
            catch (IOException e) {
                return 300;
            }
            catch (ClassNotFoundException e) {
                return 400;
            }
        }
        return 200;
    }

    public int exportListTask() {
        if (hasFile) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(userfile);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(MainController.getInstance().listTask());
                objectOutputStream.flush();
                return 100;
            }
            catch (IOException e) {
                return 300;
            }
        }
        return 200;
    }

    public void openFile() {
        MainController.getInstance().openFileView().run();
    }

    public void saveFile() {
        MainController.getInstance().saveFileView().run();
    }

    public boolean isFolder(String file) {
        return (new File(file)).isDirectory();
    }

    public boolean isNullFile() {
        try {
            BasicFileAttributes basicFileAttributes =
                    Files.readAttributes(userfile.toPath(), BasicFileAttributes.class);
            return basicFileAttributes.isRegularFile() && basicFileAttributes.size() == 0 &&
                   objectInputStream.readObject() == null;
        }
        catch (IOException | ClassNotFoundException e) {
            return true;
        }
    }

    public String filePath() {
        return userfile.getAbsolutePath();
    }
}
