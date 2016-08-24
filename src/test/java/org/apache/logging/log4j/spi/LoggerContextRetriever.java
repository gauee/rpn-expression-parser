package org.apache.logging.log4j.spi;

import org.apache.logging.log4j.util.StackLocatorUtil;
import org.slf4j.LoggerFactory;

public class LoggerContextRetriever {

    public static LoggerContext retrieveContext(AbstractLoggerAdapter abstractLoggerAdapter){
        return abstractLoggerAdapter.getContext(StackLocatorUtil.getCallerClass(LoggerFactory.class));
    }
}
