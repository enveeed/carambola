package com.google.common.flogger.backend;

import enveeed.carambola.flogger.CarambolaPlatform;

public final class PlatformProvider {
    private PlatformProvider() {}

    // ===

    public static Platform getPlatform() {
        return new CarambolaPlatform();
    }
}
