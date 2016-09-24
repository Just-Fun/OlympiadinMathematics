package ua.com.juja.solvers;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
/**
 * Created by Serzh on 8/12/16.
 */
// slow on large data, but simple & perfect :)
public class SimpleSolver implements Solver {

    @Override
    public String count(String task) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Object eval = null;
        try {
            eval = engine.eval(task);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        double d = Double.valueOf(eval.toString());
        double roundOff = Math.ceil(d * 100.0) / 100.0;
        String string = String.valueOf(roundOff);
        return (string.substring(string.length() - 2)).equals(".0") ? string.substring(0, string.length() - 2) : string;
    }
}
