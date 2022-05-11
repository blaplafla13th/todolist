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
public class DictionaryController{
    private Dictionary dictionary;

    DictionaryController() {
        int code = setDictionaryDefault();
        if (code != 100) {
            System.out.println("Language file not found. Stop program, exit code: " + code);
            System.exit(code);
        }
    }

    public int setDictionaryDefault() {
        try {
            Class[] list = getClassList("blaplafla.todolist.models.dictionary");
            Class<?> first = null;
            for (Class aClass : list) {
                if (!aClass.getName().contains("Dictionary")) {
                    first = Class.forName(aClass.getName());
                }
            }
            if (first != null) {
                Object obj = first.getDeclaredConstructor().newInstance();
                if (obj instanceof Dictionary) {
                    dictionary = (Dictionary) obj;
                    return 100;
                } else return 401;
            } else return 401;
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
        return dictionary.getInfo();
    }

    public int getLanguageList() {
        try {
            Class[] list = getClassList("blaplafla.todolist.models.dictionary");
            for (int i = 0; i < list.length; i++) {
                if (!list[i].getName().contains("Dictionary")) {
                    System.out.println(i + " " + list[i].getSimpleName());
                }
            }
            return 100;
        } catch (ClassNotFoundException e) {
            return 201;
        } catch (IOException e) {
            return 301;
        }
    }

    public int setDictionary(int i) {
        try {
            Class[] list = getClassList("blaplafla.todolist.models.dictionary");
            Class<?> first = Class.forName(list[i].getName());
            Object obj = first.getDeclaredConstructor().newInstance();
            if (obj instanceof Dictionary) {
                dictionary = (Dictionary) obj;
            } else return 401;
        } catch (ClassNotFoundException | IndexOutOfBoundsException e) {
            return 201;
        } catch (IOException e) {
            return 301;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            return 401;
        }
        return 100;
    }

    public String errorExplain(int errorCode) {
        return dictionary.errorExplain(errorCode);
    }

    public String prettyTime(long[] time) {
        if (time !=null && time.length==4)
            return dictionary.prettyTime(time[0], time[1], time[2], time[3]);
        return null;
    }

    public String label(String label) {
        return dictionary.label(label);
    }

    public void changeLanguage(){
        MainController.getInstance().setLanguageView().run();
    }

    public Class[] getClassList(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path =packageName.replace('.', '/');
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
        return classes.toArray(new Class[classes.size()]);
    }
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
