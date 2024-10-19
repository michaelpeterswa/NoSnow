package codes.nw.nosnow;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class NoSnow extends JavaPlugin {

    private static NoSnow instance;

    @Override
    public void onEnable() {
        getLogger().info(" ");
        getLogger().info("\u001b[35m -------------\u001b[0m");
        getLogger().info("\u001b[35m NoSnow v1.0.0\u001b[0m");
        getLogger().info("\u001b[35m -------------\u001b[0m");
        getLogger().info(" ");
        instance = this;

        saveDefaultConfig();

        // bStats support
        int id = 4874;

        @SuppressWarnings("unused")
        Metrics metrics = new Metrics(this, id);

        SnowBlockFormListener snowBlockFormListenerInstance = new SnowBlockFormListener();
        getServer().getPluginManager().registerEvents(snowBlockFormListenerInstance, this);

        try {
            getCommand("nsinfo").setExecutor(new InfoCommand()); // main command hook
        } catch (Exception e) {
            getLogger().info("command hooks did not initialize properly" + e);
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("NoSnow has been disabled");
    }

    public static NoSnow getInst() {
        return instance;
    }

}
