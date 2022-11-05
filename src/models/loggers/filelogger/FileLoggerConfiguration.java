package models.loggers.filelogger;

/*
 *
 * @author Roman Netesa
 *
 */
import models.loggers.api.CustomLoggerConfiguration;
import models.loggers.api.LoggingLevel;


public class FileLoggerConfiguration extends CustomLoggerConfiguration {
    //fields
    private String file;

    private Long maxFileSize;


    //constructor
    public FileLoggerConfiguration(String file, LoggingLevel loglvl, Long maxFileSize, String logFormat) {
        super(loglvl, logFormat);
        this.file = file;
        this.maxFileSize = maxFileSize;

    }

    //getters setters
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

}