package models.loggers.filelogger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Properties;

/*
 *
 * @author Roman Netesa
 *
 * This class checks if there is enough memory for message and writes it into file based on .properties file
 * Also this class extends Thread so logging process goes one-by-one and not simultaneously
 *
 */
public class FileLoggerThread extends Thread {

    //fields
    private final String message;
    private final FileLogger fl;
    private final DateTimeFormatter currentTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final FileLoggerConfigurationLoader flcload = new FileLoggerConfigurationLoader();
    private FileWriter fw;
    private Formatter formatter;
    private FileLoggerConfiguration flc;
    private File file;


    //constructor
    public FileLoggerThread(String message, FileLogger fl) {
        this.message = message;
        this.fl = fl;
        initFLC();
    }


    //public methods
    /*
     * run method calls all needed private methods, checks if there is enough memory
     */
    @Override
    public void run() {
        String resultMessage = formatMessage(message);

        try {

            if (enoughMemory(resultMessage)) {

                fw.append(String.valueOf(formatter));
                fw.close();

            } else {
                if (bigMessage(resultMessage)) {
                    System.out.println("message too big for file config!");
                } else {
                    System.out.println("not enough memory, creating a new file...");

                    createNewFileIfNoMemory();

                    initFLC();
                    fl.info(message);

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public FileLoggerConfiguration getFileLoggerConfig() {
        return flc;
    }

    public void setFileLoggerConfig(FileLoggerConfiguration newFlc) {
        flc = newFlc;
    }

    //private methods

    /*
     * formatMessage formats message based on FileLoggerConfiguration format field
     */

    private String formatMessage(String message) {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter currentTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter.format(flc.getLogFormat(), currentTimeFormat.format(now), message);

        return formatter.toString();
    }


    /*
     * returns true if there is enough memory for message
     */
    private boolean enoughMemory(String message) {

        long currentMessageMemory = 8 * ((((String.valueOf(message).length()) * 2) + 38) / 8); //MINIMAL MEMORY

        return currentMessageMemory + file.length() < flc.getMaxFileSize();
    }


    /*
     * returns true if message is bigger than maxFileSize field in FileLoggerConfiguration
     */
    private boolean bigMessage(String message) {

        long currentMessageMemory = 8 * ((((String.valueOf(message).length()) * 2) + 38) / 8); //MINIMAL MEMORY

        return currentMessageMemory > flc.getMaxFileSize();
    }

    /*
     * creates a new file, gives it a specific name, changes .property file
     */
    private void createNewFileIfNoMemory() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String newPath;
        int filenum = 1;

        FileInputStream in = new FileInputStream("resourses\\LogConfig1.properties");
        Properties prop = new Properties();
        prop.load(in);
        in.close();
        FileOutputStream out = new FileOutputStream("resourses\\LogConfig1.properties");

        newPath = "resourses\\log" + "(" + filenum + ")" + currentTimeFormat.format(now) + ".txt";
        file = new File(newPath);


        while (file.exists()) {
            filenum++;
            newPath = "resourses\\log" + "(" + filenum + ")" + currentTimeFormat.format(now) + ".txt";
            file = new File(newPath);
        }

        prop.setProperty("file_path", newPath);
        prop.store(out, null);
        out.close();

    }


    /*
     * loads .property file
     */
    private void initFLC() {

        try {
            this.flc = flcload.load("resourses\\LogConfig1.properties");
            file = new File(flc.getFile());
            file.createNewFile();
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.formatter = new Formatter();
    }


}
