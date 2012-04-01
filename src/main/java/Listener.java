import it.mcblock.mcblockit.api.MCBlockItAPI;
import it.mcblock.mcblockit.api.Utils;

public class Listener extends PluginListener {
    @Override
    public boolean onCommand(Player player, String[] split) {
        split[0] = split[0].substring(1);
        return this.processCommand(player, split);
    }

    @Override
    public boolean onConsoleCommand(String[] split) {
        return this.processCommand(null, split);
    }

    @Override
    public void onDisconnect(Player player) {
        MCBlockItAPI.playerQuit(MCBlockItAPI.getPlayer(player.getName()));
    }

    @Override
    public void onLogin(Player player) {
        if (!MCBlockItAPI.playerJoin(new CanaryPlayer(player))) {
            player.kick(MCBlockItAPI.KICK_REASON_BLOCKED);
        }
    }

    @Override
    public String onLoginChecks(String name) {
        if (CanaryBlockItAPI.isBanned(name)) {
            return MCBlockItAPI.KICK_REASON_BANNED;
        }
        return null;
    }

    private boolean processCommand(Player player, String[] split) {
        if (split[0].equalsIgnoreCase("ban") && ((player == null) || player.canUseCommand("ban"))) {
            if (split.length < 3) {
                return true;
            }
            final String reason = Utils.combineSplit(split, " ", 2, split.length - 1);
            String name;
            if (player == null) {
                name = "CONSOLE";
            } else {
                name = player.getName();
            }
            MCBlockItAPI.ban(split[1], name, reason);
            return true;
        }
        if (split[0].equalsIgnoreCase("unban") && ((player == null) || player.canUseCommand("unban"))) {
            if (split.length > 1) {
                MCBlockItAPI.unban(split[1]);
            }
            return true;
        }
        return false;
    }

}