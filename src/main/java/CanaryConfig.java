

import java.util.Arrays;
import java.util.List;

import it.mcblock.mcblockit.api.MCBIConfig;


public class CanaryConfig implements MCBIConfig{

    private PropertiesFile properties;

    public CanaryConfig(PropertiesFile properties){
        this.properties=properties;
    }
    
    @Override
    public int getBanRestriction() {
        return properties.getInt("banRestrictionValue", 5);
    }

    @Override
    public List<String> getFlagRestriction() {
        return Arrays.asList(properties.getString("flagRestrictionValue", "").split(","));
    }

    @Override
    public double getReputationRestriction() {
        return properties.getDouble("reputationRestrictionValue",0.0D);
    }

    @Override
    public boolean isBanRestrictionEnabled() {
        return properties.getBoolean("banRestrictionEnable", false);
    }

    @Override
    public boolean isFlagRestrictionEnabled() {
        return properties.getBoolean("FlagRestrictionEnable", false);
    }

    @Override
    public boolean isReputationRestrictionEnabled() {
        return properties.getBoolean("reputationRestrictionEnable", true);
    }

}
