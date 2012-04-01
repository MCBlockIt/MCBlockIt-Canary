
import java.util.Arrays;
import java.util.List;

import it.mcblock.mcblockit.api.MCBIConfig;

public class CanaryConfig implements MCBIConfig {

    private final PropertiesFile properties;

    public CanaryConfig(PropertiesFile properties) {
        this.properties = properties;
    }

    @Override
    public int getBanRestriction() {
        return this.properties.getInt("banRestrictionValue", 5);
    }

    @Override
    public int getCacheTimeout() {
        return this.properties.getInt("cacheTimeout", 5);
    }

    @Override
    public List<String> getFlagRestriction() {
        return Arrays.asList(this.properties.getString("flagRestrictionValue", "").split(","));
    }

    @Override
    public double getReputationRestriction() {
        return this.properties.getDouble("reputationRestrictionValue", 0.0D);
    }

    @Override
    public boolean isBanRestrictionEnabled() {
        return this.properties.getBoolean("banRestrictionEnable", false);
    }

    @Override
    public boolean isFlagRestrictionEnabled() {
        return this.properties.getBoolean("FlagRestrictionEnable", false);
    }

    @Override
    public boolean isReputationRestrictionEnabled() {
        return this.properties.getBoolean("reputationRestrictionEnable", true);
    }

}
