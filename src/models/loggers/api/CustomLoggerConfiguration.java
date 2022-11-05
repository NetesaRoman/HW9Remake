package models.loggers.api;

/*
 *
 * @author Roman Netesa
 *
 */
public abstract class CustomLoggerConfiguration {

    private LoggingLevel loglvl;
    private String logFormat;

    public CustomLoggerConfiguration(LoggingLevel loglvl, String logFormat){
        this.logFormat = logFormat;
        this.loglvl = loglvl;
    }

    public LoggingLevel getLoggingLevel() {
        return loglvl;
    }

    public void setLoggingLevel(LoggingLevel loglvl) {
        this.loglvl = loglvl;
    }

    public String getLogFormat() {
        return logFormat;
    }

    public void setLogFormat(String fileFormat) {
        this.logFormat = fileFormat;
    }

}
