package ua.com.juja;

import java.io.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * Created by Serzh on 8/12/16.
 */
public class Solver {
    public static void main(String[] args) {
        System.out.println(calculate("6-(2+(1*3)*2)"));
    }

    public static String calculate(String string) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Object eval = null;
        try {
            eval = engine.eval(string);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return eval.toString();
    }
}
