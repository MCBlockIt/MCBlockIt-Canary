
public class MCBlockIt extends Plugin{

    @Override
    public void disable() {
        
    }

    @Override
    public void enable() {
        
    }
    
    @Override
    public void initialize(){
        etc.getLoader().addListener(PluginLoader.Hook.PLAYER_CONNECT, new Listener(), this, PluginListener.Priority.CRITICAL);
    }

}
