package com.norbertschmelhaus.eehomework2.producer;

import com.norbertschmelhaus.eehomework2.qualifiers.LoggerQualifier;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
public class LoggingProducer {

    @Produces @LoggerQualifier
    public Logger createLogger(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}
