package ukma.edu.ua.HospitalApp.logger;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
    name = "MapAppender",
    category = Core.CATEGORY_NAME,
    elementType = Appender.ELEMENT_TYPE,
    printObject = true
)
public class MapAppender extends AbstractAppender {
  private final Map<Long, String> logs = new HashMap<>();

  protected MapAppender(
      String name,
      Filter filter,
      Layout<? extends Serializable> layout,
      boolean ignoreExceptions
  ) {
    super(name, filter, layout, ignoreExceptions, null);
  }

  @PluginFactory
  public static Appender createAppender(
      @PluginAttribute("name") String name,
      @PluginElement("Layout") Layout<? extends Serializable> layout,
      @PluginElement("Filter") final Filter filter
  ) {
    return new MapAppender(name, filter, layout, true);
  }

  @Override
  public void append(LogEvent event) {
    logs.put(new Date().getTime(), event.getMessage().getFormattedMessage());
  }
}
