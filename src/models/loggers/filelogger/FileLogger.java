package models.loggers.filelogger;
/*
 *
 * @author Roman Netesa
 * This class creates and joins FileLoggerThread and gives them a command to write in a file
 */

import models.loggers.api.CustomLogger;
import models.loggers.api.LoggingLevel;

import java.io.*;


public class FileLogger implements CustomLogger{

    //fields
    private FileLoggerConfiguration flc;
    private FileLoggerConfigurationLoader flcload = new FileLoggerConfigurationLoader();


    //constructor
    public FileLogger(){
        try {
            this.flc = flcload.load("resourses\\LogConfig1.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //public methods

    /*
    * joins a new FileLoggerThread, and gives it a command to write a message
    */
    @Override
    public void info(String message) {

            Thread flt = new FileLoggerThread(message, this);
        try {
            flt.start();
            flt.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }

    /*
     * does debug stuff and calls info method
     */
    @Override
    public void debug(String message) {
        if (getFileLoggerConfig().getLoggingLevel() == LoggingLevel.DEBUG) {
            info("DEBUG SUCCESS! " + message + " DEBUG SUCCESS!");
        } else {

            System.out.println("LoggingLevel is not DEBUG!");
        }
    }

    public FileLoggerConfiguration getFileLoggerConfig() {
        return flc;
    }

    public void setFileLoggerConfig(FileLoggerConfiguration newFlc) {
        flc = newFlc;
    }





}
