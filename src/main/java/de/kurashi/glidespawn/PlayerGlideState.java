package de.kurashi.glidespawn;

import java.util.concurrent.ScheduledFuture;

public class PlayerGlideState {

    // Gleitet der Spieler gerade?
    public volatile boolean gliding = false;

    // War die Sprung-Taste im letzten Paket gedrueckt? (Flanken-Erkennung fuer Boost)
    public volatile boolean wasJumping = false;

    // Boost in dieser Glide-Session schon benutzt? (einmaliger Boost pro Flug)
    public volatile boolean boostUsed = false;

    // Y-Tracking fuer Landungserkennung
    public volatile double lastY = -1;
    public volatile int stuckYTicks = 0;

    // Glide-Lifecycle Timestamps
    public volatile long glideStartTime = 0;
    public volatile long lastGlideEndTime = 0;

    // Debug-Logging Counter (loggt alle N Pakete)
    public volatile int debugCounter = 0;

    // Periodischer Descent-Task (sanfter Sinkflug waehrend Gleiten)
    public volatile ScheduledFuture<?> descentTask = null;
}
