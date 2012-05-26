import java.io.*;
import java.util.HashSet;
import java.util.logging.Level;

import it.mcblock.mcblockit.api.MCBIConfig;
import it.mcblock.mcblockit.api.MCBlockItAPI;

public class CanaryBlockItAPI extends MCBlockItAPI {

    public static boolean isBanned(String name) {
        return CanaryBlockItAPI.banList.contains(name.toLowerCase());
    }
    private final CanaryConfig config;

    private final File dataFolder;

    public static HashSet<String> banList = new HashSet<String>();

    public CanaryBlockItAPI(String APIKey, File dataFolder) {
        super(APIKey, dataFolder);
        this.dataFolder = dataFolder;
        this.config = new CanaryConfig(new PropertiesFile(dataFolder + "/mcblockit.properties"));
        final File file = new File(dataFolder, "bans.txt");
        if (file.exists()) {
            try {
                final BufferedReader input = new BufferedReader(new FileReader(file));
                String line;
                while ((line = input.readLine()) != null) {
                    if (line.length() < 1) {
                        continue;
                    }
                    CanaryBlockItAPI.banList.add(line.toLowerCase());
                }
                input.close();
            } catch (final IOException e) {
                System.out.println("Failed to read " + file);
                e.printStackTrace();
            }
        }
    }

    @Override
    public MCBIConfig getConfig() {
        return this.config;
    }

    private void updateBanList() {
        try {
            final BufferedWriter outputUnbans = new BufferedWriter(new FileWriter(new File(this.dataFolder, "bans.txt")));

            for (final String name : CanaryBlockItAPI.banList) {
                outputUnbans.write(name + "\n");
            }
            outputUnbans.close();
        } catch (final IOException e) {
            System.out.println("Failed to write bans");
            e.printStackTrace();
        }
    }

    //@Override
    protected void banName(String name) {
        CanaryBlockItAPI.banList.add(name.toLowerCase());
        this.updateBanList();
    }

    //@Override
    protected void unbanName(String name) {
        CanaryBlockItAPI.banList.remove(name.toLowerCase());
        this.updateBanList();
    }

    @Override
    public String getVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void log(Level level, String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void log(Level level, String message, Throwable thrown) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void log(String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void shutdown() {
        // TODO Auto-generated method stub
        
    }

}
