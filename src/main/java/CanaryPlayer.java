import it.mcblock.mcblockit.api.MCBIPlayer;

public class CanaryPlayer implements MCBIPlayer{

    Player player;
    
    public CanaryPlayer(Player player){
        this.player=player;
    }
    
    @Override
    public String getIP() {
        return player.getIP();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public void kick(String reason) {
        player.kick(reason);
    }

}
