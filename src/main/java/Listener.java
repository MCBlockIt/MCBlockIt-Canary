import it.mcblock.mcblockit.api.Utils;


public class Listener extends PluginListener {
    @Override
    public String onLoginChecks(String name) {
        if(CanaryBlockItAPI.isBanned(name)){
            return CanaryBlockItAPI.KICK_REASON_BANNED;
        }
        return null;
    }
    @Override
    public void onLogin(Player player){
        if(!CanaryBlockItAPI.playerJoin(new CanaryPlayer(player))){
            player.kick(CanaryBlockItAPI.KICK_REASON_BLOCKED);
        }
    }

    @Override
    public void onDisconnect(Player player) {
        CanaryBlockItAPI.playerQuit(CanaryBlockItAPI.getPlayer(player.getName()));
    }

    @Override
    public boolean onCommand(Player player, String[] split){
        split[0]=split[0].substring(1);
        return this.processCommand(player, split);
    }

    @Override
    public boolean onConsoleCommand(String[] split){
        return this.processCommand(null, split);
    }

    private boolean processCommand(Player player, String[] split){
        if(split[0].equalsIgnoreCase("ban") && (player==null || player.canUseCommand("ban"))){
            if (split.length < 3) {
                return true;
            }
            final String reason = Utils.combineSplit(split, " ", 2, split.length - 1);
            String name;
            if(player==null){
                name="CONSOLE";
            } else{
                name=player.getName();
            }
            CanaryBlockItAPI.ban(split[1], name, reason);
            return true;
        }
        if(split[0].equalsIgnoreCase("unban") && (player==null || player.canUseCommand("unban"))){
            if(split.length>1){
                CanaryBlockItAPI.unban(split[1]);
            }
            return true;
        }
        return false;
    }

}