package enveeed.carambola;

public final class Carambola {

    private static Carambola carambola = null;

    // ===

    private Carambola() {}

    // ===

    public static Carambola get() {
        if(carambola == null) carambola = new Carambola();
        return carambola;
    }

    // ===

    private final HandlerRegistry handlers = new HandlerRegistry();

    // ===

    public void log(Statement statement) {}

    // ===

    public HandlerRegistry getHandlers() {
        return this.handlers;
    }
}
