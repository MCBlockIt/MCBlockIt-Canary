import it.mcblock.mcblockit.api.MCBIPlayer;

public class CanaryPlayer implements MCBIPlayer {

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

}
