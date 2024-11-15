package Utilittes;

import org.apache.logging.log4j.LogManager;

public class LogsUtils {

    public static String Logs_Path ="test-OutPut/Logs";

    public static void trace(String Massage ){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .trace(Massage);
    }
    public static void debug(String Massage ){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .debug(Massage);
    }
    public static void info(String Massage ){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .info(Massage);
    }
    public static void warn(String Massage ){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .warn(Massage);
    }
    public static void error(String Massage ){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .error(Massage);
    }
    public static void fatal(String Massage ){
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .fatal(Massage);
    }

}
