package models.loggers.api;

/*
 *
 * @author Roman Netesa
 *
 */

import java.io.IOException;

public interface CustomLoggerConfigurationLoader {

    public CustomLoggerConfiguration load(String path) throws IOException;

}
