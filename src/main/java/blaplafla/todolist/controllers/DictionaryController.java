package blaplafla.todolist.controllers;

import blaplafla.todolist.models.dictionary.Dictionary;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@SuppressWarnings("rawtypes")
public class DictionaryController {
    private Dictionary dictionary;

    private ArrayList<Dictionary> langlist = new ArrayList<>();

    public ArrayList<Dictionary> getLanglist() {
        return langlist;
    }

    DictionaryController() {
        int code = loadLanguage();
        if (code != 100) {
            System.out.println("Language file not found. Stop program, exit code: " + code);
            System.exit(code);
        }
        dictionary = langlist.get(0);
    }

    public int loadLanguage() {
        try {
            ArrayList<Class> langlist = getClassList("blaplafla.todolist.models.dictionary");
            Class<?> temp;
            for (Class aClass : langlist) {
                temp = Class.forName(aClass.getName());
                Object obj = temp.getDeclaredConstructor().newInstance();
                if (obj instanceof Dictionary dictionary) {
                    boolean inserted = false;
                    for (Dictionary dict : this.langlist) {
                        if (dict.toString().equals(dictionary.toString()))
                            inserted = true;
                    }
                    if (!inserted)
                        this.langlist.add(dictionary);
                }
            }
            return 100;
        } catch (ClassNotFoundException e) {
            return 201;
        } catch (IOException e) {
            return 301;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            return 401;
        }

    }

    public String getDictionary() {
        return dictionary.toString();
    }

    public int setDictionary(int i) {
        try {
            dictionary = langlist.get(i);
        } catch (IndexOutOfBoundsException e) {
            return 301;
        }
        return 300;
    }

    public String errorExplain(int errorCode) {
        return dictionary.errorExplain(errorCode);
    }

    public String prettyTime(long[] time) {
        if (time != null && time.length == 4)
            return dictionary.prettyTime(time[0], time[1], time[2], time[3]);
        return null;
    }

    public String label(String label) {
        return dictionary.label(label);
    }

    public void changeLanguage() {
        MainController.getInstance().setLanguageView().run();
    }

    public ArrayList<Class> getClassList(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles(file -> !file.getName().equals("Dictionary.class"));
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }
        return classes;
    }
}
