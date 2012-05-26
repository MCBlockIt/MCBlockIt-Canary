import it.mcblock.mcblockit.api.MCBIPlayer;

public class CanaryPlayer extends MCBIPlayer {

    Player player;

    public CanaryPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getIP() {
        return this.player.getIP();
    }

    @Override
    public String getName() {
        return this.player.getName();
    }

    @Override
    public void kick(String reason) {
        this.player.kick(reason);
    }

    @Override
    public void messageIfAdmin(String message) {
        // TODO Auto-generated method stub
        
    }

}
