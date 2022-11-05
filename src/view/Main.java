package view;

import models.loggers.filelogger.FileLogger;

import java.io.IOException;

/*
 *
 * @author Roman Netesa
 *
 */
public class Main {
    public static void main(String[] args) {

        FileLogger filelogger;


        filelogger = new FileLogger();


        filelogger.info("hello my name is Gorogo");
        filelogger.info("hello my name is Gorogo");
        filelogger.info("hello my name is Gorogo");
        filelogger.info("hello my name is Gorogo");
        System.out.println(filelogger.getFileLoggerConfig().getFile());
    }
}