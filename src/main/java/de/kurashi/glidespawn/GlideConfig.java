package de.kurashi.glidespawn;

public class GlideConfig {

    // Koordinaten der Spawn-Insel
    public double spawnX = 0;
    public double spawnY = 200;
    public double spawnZ = 0;

    // XZ-Radius um den Spawn in dem Gleiten aktiviert wird
    public double activationRadius = 50;

    // Name der Survival-Welt
    public String survivalWorldName = "survival";

    // Spieler beim ERSTEN Betreten der Survival-Welt auf die Insel teleportieren?
    public boolean teleportOnFirstJoin = true;

    // Spieler bei JEDEM Betreten der Survival-Welt auf die Insel teleportieren?
    public boolean teleportOnJoin = false;

    // ──────────────────────────────────────────────────────────────────
    // Glide-Aktivierungs-Zone (Y-Band unter der Spawn-Insel)
    // ──────────────────────────────────────────────────────────────────

    // Glide-Zone beginnt X Bloecke UNTER spawnY (z.B. spawnY=200, glideZoneTop=5 -> ab y=195)
    public double glideZoneTop = 5.0;

    // Glide-Zone endet X Bloecke UNTER spawnY (z.B. glideZoneBottom=150 -> bis y=50)
    public double glideZoneBottom = 150.0;

    // Mindest-Fallgeschwindigkeit (Bloecke pro Tick) damit Gleiten aktiviert wird
    public double minFallSpeed = 0.5;

    // Mindest-Fallhoehe (in Bloecken) bevor Gleiten aktiviert wird (Legacy, falls genutzt)
    public double minFallHeight = 5.0;

    // Cooldown zwischen Glide-Sessions (ms)
    public long reactivationCooldownMs = 5000;

    // ──────────────────────────────────────────────────────────────────
    // Landungserkennung (Y-Stuck-Detection)
    // ──────────────────────────────────────────────────────────────────

    // Y-Bewegungs-Schwellwert: Aenderung kleiner als das = "stuck"
    public double yStuckThreshold = 0.05;

    // Anzahl Ticks Y-stuck = gelandet
    public int landingStuckTicks = 10;

    // Mindest-Glide-Dauer bevor Landung registriert wird (ms)
    public long minGlideDurationMs = 1000;

    // Maximaler Glide-Timeout (ms)
    public long maxGlideDurationMs = 30000;

    // ──────────────────────────────────────────────────────────────────
    // Flugmechanik
    // ──────────────────────────────────────────────────────────────────

    // canFly Speed-Werte (werden waehrend Glide gesetzt)
    public float horizontalFlySpeed = 12.0f;
    public float verticalFlySpeed = 6.0f;

    // MovementSettings fuer den Glide-Effekt (zusaetzlich zur nativen gliding-Physik)
    public float glideAirDragMax = 0.9998f;
    public float glideAirFrictionMax = 0.003f;

    // Kraft des periodischen Vorwaertsimpulses (Elytra-Mechanik, pro Tick)
    public double glideForwardForce = 3.0;

    // Interval des Glide-Ticks in Millisekunden
    public long glideTickIntervalMs = 100;

    // ──────────────────────────────────────────────────────────────────
    // Descent-Task (sanfter Sinkflug waehrend Glide)
    // ──────────────────────────────────────────────────────────────────

    // Intervall des Descent-Ticks (ms)
    public long descentIntervalMs = 50;

    // Sinkrate (Bloecke pro Tick)
    public double descentSpeed = 1.5;

    // ──────────────────────────────────────────────────────────────────
    // Boost (Spacebar waehrend Gleiten)
    // ──────────────────────────────────────────────────────────────────

    // Boost-Mechanik aktiv?
    public boolean boostEnabled = true;

    // Boost-Kraft (Spacebar waehrend Gleiten)
    public double boostForce = 18.0;

    // Cooldown zwischen zwei Boosts in Millisekunden
    public long boostCooldownMs = 3000;

    // ──────────────────────────────────────────────────────────────────
    // Notification
    // ──────────────────────────────────────────────────────────────────

    // Benachrichtigung wenn Gleiten aktiviert wird (leer lassen = deaktiviert)
    public String notificationTitle = "Gleiten aktiv";
    public String notificationSubtitle = "Leertaste für Boost!";
}
