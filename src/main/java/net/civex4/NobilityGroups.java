package net.civex4;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import vg.civcraft.mc.civmodcore.ACivMod;

public class NobilityGroups extends ACivMod {
    private static Logger logger;

    private static NobilityGroups instance;

    public void onEnable() {
        instance = this;
        logger = getLogger();

        /*
         * Not needed until NameLayer is integrated
        if (!Bukkit.getPluginManager().isPluginEnabled("NameLayer")){
            logger.info("NobilityGroups is shutting down because it could not find NameLayer");
            this.getPluginLoader().disablePlugin(this); // shut down
        }
        */
    }
}
