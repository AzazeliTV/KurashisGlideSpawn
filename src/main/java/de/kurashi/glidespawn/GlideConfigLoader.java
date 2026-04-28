package de.kurashi.glidespawn;

import de.kurashi.lib.config.AbstractConfigLoader;
import java.nio.file.Path;

/**
 * Loader fuer {@code glidespawn.json}. Nutzt KurashiLib's {@link AbstractConfigLoader}
 * fuer Atomic-Write + Schema-Versioning.
 */
public final class GlideConfigLoader extends AbstractConfigLoader<GlideConfig> {

    public GlideConfigLoader(Path dataDir) {
        super(dataDir.resolve("glidespawn.json"), GlideConfig.class, new GlideConfig(), 1);
    }
}
