package enveeed.carambola.flogger;

import com.google.common.flogger.backend.LogData;
import enveeed.carambola.Statement;

public final class FloggerStatement implements Statement {

    private final LogData data;

    // ===

    private FloggerStatement(LogData data) {
        this.data = data;
    }

    // ===

    public static FloggerStatement of(LogData data) {
        return new FloggerStatement(data);
    }
}
