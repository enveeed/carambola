package enveeed.carambola.slf4j;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

public final class CarambolaSLF4JServiceProvider implements SLF4JServiceProvider {

    private final CarambolaLoggerFactory factory = new CarambolaLoggerFactory();

    // ===

    @Override
    public ILoggerFactory getLoggerFactory() {
        return this.factory;
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return null;
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return null;
    }

    @Override
    public String getRequesteApiVersion() {
        return "1.8.0-beta4";
    }


    @Override
    public void initialize() {
        // NO-OP
    }

    // ===

    /**
     * Create the CarambolaSLF4JServiceProvider service (called by the ServiceLoader)
     * @return a new CarambolaSLF4JServiceProvider
     */
    public static CarambolaSLF4JServiceProvider provider() {
        return new CarambolaSLF4JServiceProvider();
    }
}
