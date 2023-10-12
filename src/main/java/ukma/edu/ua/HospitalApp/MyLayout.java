package ukma.edu.ua.HospitalApp;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.AbstractLayout;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.core.util.StringBuilderWriter;
import java.text.SimpleDateFormat;

import java.nio.charset.Charset;
import java.util.Date;

@Plugin(name = "MyLayout", category = "Core", elementType = "layout", printObject = true)
public class MyLayout extends AbstractStringLayout {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MyLayout() {
        super(null, null, null);
    }

    @Override
    public String toSerializable(LogEvent event) {
        StringBuilder writer = new StringBuilder();

        writer.append(dateFormat.format(new Date(event.getTimeMillis()))).append(" ")
                .append(event.getMessage().getFormattedMessage())
                .append(System.lineSeparator());

        return writer.toString();
    }
}
