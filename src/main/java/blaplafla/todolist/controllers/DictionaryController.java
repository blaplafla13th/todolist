package blaplafla.todolist.controllers;

import blaplafla.todolist.models.dictionary.Dictionary;
import blaplafla.todolist.models.dictionary.Vietnamese;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

@SuppressWarnings("rawtypes")
public class DictionaryController {
    private static DictionaryController instance;
    private Dictionary dictionary;

    private DictionaryController() {
        int code = setDictionaryDefault();
        if (code != 100)
            System.out.println(code);
    }

    public int setDictionaryDefault() {
        try {
            Class[] list = ClassController.getInstance().getClassList("blaplafla.todolist.models.dictionary");
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
        return 100;
    }

    public String getDictionary() {
        return dictionary.getInfo();
    }

    public int getLanguageList() {
        try {
            Class[] list = ClassController.getInstance().getClassList("blaplafla.todolist.models.dictionary");
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
            Class[] list = ClassController.getInstance().getClassList("blaplafla.todolist.models.dictionary");
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

    public static DictionaryController getInstance() {
        if (instance == null) {
            instance = new DictionaryController();
        }
        return instance;
    }

    public String errorExplain(int errorCode) {
        return dictionary.errorExplain(errorCode);
    }

    public String prettyTime(long day, long hour, long minute, long second) {
        return dictionary.prettyTime(day, hour, minute, second);
    }

    public String label(String label) {
        return dictionary.label(label);
    }


}
