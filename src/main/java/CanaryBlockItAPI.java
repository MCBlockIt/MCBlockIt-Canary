
import java.io.*;
import java.util.HashSet;

import it.mcblock.mcblockit.api.MCBIConfig;
import it.mcblock.mcblockit.api.MCBlockItAPI;

public class CanaryBlockItAPI extends MCBlockItAPI{

    private CanaryConfig config;
    private File dataFolder;

    public CanaryBlockItAPI(String APIKey, File dataFolder) {
        super(APIKey, dataFolder);
        this.dataFolder=dataFolder;
        this.config=new CanaryConfig(new PropertiesFile(dataFolder+"/mcblockit.properties"));
        File file=new File(dataFolder,"bans.txt");
        if (file.exists()) {
            try {
                final BufferedReader input = new BufferedReader(new FileReader(file));
                String line;
                while ((line = input.readLine()) != null) {
                    if (line.length() < 1) {
                        continue;
                    }
                    banList.add(line.toLowerCase());
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

    @Override
    protected void banName(String name) {
        CanaryBlockItAPI.banList.add(name.toLowerCase());
        this.updateBanList();
    }

    @Override
    protected void unbanName(String name) {
        CanaryBlockItAPI.banList.remove(name.toLowerCase());
        this.updateBanList();
    }

    private void updateBanList(){
        try {
            final BufferedWriter outputUnbans = new BufferedWriter(new FileWriter(new File(this.dataFolder,"bans.txt")));

            for(String name:banList){
                outputUnbans.write(name+"\n");
            }
            outputUnbans.close();
        } catch (final IOException e) {
            System.out.println("Failed to write bans");
            e.printStackTrace();
        }
    }

    public static boolean isBanned(String name) {
        return banList.contains(name.toLowerCase());
    }

    public static HashSet<String> banList=new HashSet<String>();

}
