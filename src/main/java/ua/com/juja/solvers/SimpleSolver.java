package ua.com.juja.solvers;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;


/**
 * Created by Serzh on 8/12/16.
 */
public class SimpleSolver implements Example{

    @Override
    public String count(String task) {
        return run(task);
    }

    public static String run(String task) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Object eval = null;
        try {
            eval = engine.eval(task);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        double d = Double.valueOf(eval.toString());
//        double roundOff = Math.round(d * 100.0) / 100.0;
        double roundOff = Math.ceil(d * 100.0) / 100.0;
        return String.valueOf(roundOff);
    }
}
