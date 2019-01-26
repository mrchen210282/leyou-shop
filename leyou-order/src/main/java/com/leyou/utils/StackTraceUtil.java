package com.leyou.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTraceUtil {
    public StackTraceUtil() {
    }

    public static String getStackTrace(Throwable exception) {
        StringWriter sw = null;
        PrintWriter pw = null;

        String var3;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            var3 = sw.toString();
        } finally {
            if (pw != null) {
                pw.close();
            }

        }

        return var3;
    }
}
