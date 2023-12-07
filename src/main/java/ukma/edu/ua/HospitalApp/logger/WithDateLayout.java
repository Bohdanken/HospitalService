package ukma.edu.ua.HospitalApp.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

@Plugin(
    name = "WithDateLayout",
    category = Node.CATEGORY,
    elementType = Layout.ELEMENT_TYPE,
    printObject = true
)
public class WithDateLayout extends AbstractStringLayout {
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  protected WithDateLayout() {
    super(null, null, null);
  }

  @PluginFactory
  public static WithDateLayout createLayout() {
    return new WithDateLayout();
  }

  @Override
  public String toSerializable(LogEvent event) {
    return "["
        + dateFormat.format(new Date(event.getTimeMillis()))
        + "] "
        + event.getMessage().getFormattedMessage()
        + System.lineSeparator();
  }
}
