package sijay.zheng.z.app.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sijay
 * @description LogFilter
 * @date 2023/8/7 16:30
 */
public class SqlLogFilter extends AbstractMatcherFilter<ILoggingEvent> {
    final Logger logger = LoggerFactory.getLogger("sqlLog");

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel().levelInt == Level.TRACE_INT && "org.hibernate.resource.jdbc.internal.ResourceRegistryStandardImpl".equalsIgnoreCase(event.getLoggerName())) {
            String message = event.getFormattedMessage();
            if (message.startsWith("Closing prepared statement")) {
                logger.info("SQL => [" + message.split("ClientPreparedStatement: ")[1]);
            }
            return FilterReply.DENY;
        } else if (event.getLevel().levelInt > Level.DEBUG_INT) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }
}
