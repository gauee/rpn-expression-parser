package org.apache.logging.slf4j;

import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextRetriever;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentMap;

public class MockedLog4jLoggerFactory {

    public static Logger createMockedLogger(Class clazz, Log4jLoggerFactory log4jLoggerFactory) {
        Logger mockedLogger = Mockito.mock(Logger.class);
        LoggerContext context = LoggerContextRetriever.retrieveContext(log4jLoggerFactory);
        ConcurrentMap<String, Logger> loggersInContext = log4jLoggerFactory.getLoggersInContext(context);
        loggersInContext.putIfAbsent(clazz.getName(), mockedLogger);
        return mockedLogger;
    }
}
