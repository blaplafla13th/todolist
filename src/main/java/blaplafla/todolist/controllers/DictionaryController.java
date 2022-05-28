package blaplafla.todolist.controllers;

import blaplafla.todolist.models.dictionary.Dictionary;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings({"rawtypes", "resource"})
public class DictionaryController {
    private final ArrayList<Dictionary> langlist = new ArrayList<>();
    private Dictionary dictionary;

    DictionaryController() {
        int code = loadLanguage();
        if (code != 100) {
            System.out.println("Language file not found. Stop program, exit code: " + code);
            System.exit(code);
        }
        dictionary = langlist.get(0);
    }

    public ArrayList<Dictionary> getLanglist() {
        return langlist;
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
            e.printStackTrace();
            return 301;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | URISyntaxException e) {
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

    public ArrayList<Class> getClassList(String packageName) throws ClassNotFoundException, IOException, URISyntaxException {
        ArrayList<Class> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        URI uri = Objects.requireNonNull(getClass().getResource("/" + path)).toURI();
        Path myPath;
        if (uri.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
            myPath = fileSystem.getPath("/" + path);
        } else {
            myPath = Paths.get(uri);
        }
        Stream<Path> walk = Files.walk(myPath, 1);
        for (Iterator<Path> iter = walk.iterator(); iter.hasNext(); ) {
            Path temp = iter.next();
            String className = temp.toAbsolutePath().toString().replace("/", ".");
            className = className.substring(className.indexOf("blaplafla"));
            if (className.contains(".class") && !className.contains("Dictionary.class"))
                classes.add(Class.forName(className.replace(".class", "")));
        }
        return classes;
    }
}
