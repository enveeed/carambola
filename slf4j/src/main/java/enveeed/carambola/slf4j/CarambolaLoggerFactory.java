package enveeed.carambola.slf4j;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public final class CarambolaLoggerFactory implements ILoggerFactory {

    @Override
    public Logger getLogger(String name) {
        return new CarambolaLoggerAdapter(name);
    }
}
