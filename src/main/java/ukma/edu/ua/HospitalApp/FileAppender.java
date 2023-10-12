package ukma.edu.ua.HospitalApp;


import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class FileAppender extends AbstractAppender {

    private static final ConfigurationBuilder<BuiltConfiguration> builder =
            ConfigurationBuilderFactory.newConfigurationBuilder();

    private static final String APPENDER_NAME = "FileAppender";

    protected FileAppender(String name, Layout<? extends Serializable> layout) {
        super(name, null, layout);
    }

    @Override
    public void append(LogEvent event) {
        FileAppender appender = builder.newAppender(APPENDER_NAME, "File")
                .addAttribute("fileName", "file.log")
                .addAttribute("append", true)
                .add(layout())
                .build();
        appender.start();
        appender.append(event);
        appender.stop();
    }

    public static Appender createAppender(Layout<? extends Serializable> layout) {
        return new FileAppender(APPENDER_NAME, layout);
    }
}
