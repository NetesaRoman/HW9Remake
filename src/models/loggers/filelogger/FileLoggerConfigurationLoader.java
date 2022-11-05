package models.loggers.filelogger;

/*
 *
 * @author Roman Netesa
 *  This class load from .properties file
 */
import models.loggers.api.CustomLoggerConfigurationLoader;
import models.loggers.api.LoggingLevel;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileLoggerConfigurationLoader implements CustomLoggerConfigurationLoader {


    private FileReader reader;
    private Properties prop;

    @Override
    public FileLoggerConfiguration load(String path) throws IOException {

        LoggingLevel loglvl;
        long l;

        reader = new FileReader(path);
        prop = new Properties();
        prop.load(reader);

        l = Long.parseLong(prop.getProperty("max_size"));
        loglvl = LoggingLevel.valueOf(prop.getProperty("logging_level"));

        //DEBUG--------------------------------------
        System.out.println(prop.getProperty("file_path") +
                loglvl +
                l +
                prop.getProperty("log_format"));
        //--------------------------------------------


        return new FileLoggerConfiguration(
                prop.getProperty("file_path"),
                loglvl,
                l,
                prop.getProperty("log_format"));
    }
}
