/*
 *  This file is part of VoteLogger.
 *
 *  VoteLogger: A software for logging votes,worksworks with Votifier.
 *  Copyright (C) 2018 akaregi <akg.tachibana@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.akaregi.votelogger;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class VoteLogger extends JavaPlugin {

    /**
     * Version of VoteLogger.
     */
    protected final String VERSION = this.getDescription().getVersion();

    /**
     * Logger for VoteLogger
     */
    protected final Logger log = Logger.getLogger(getName());

    /**
     * Targeted path to log for VoteLogger.
     */
    protected final String LOGPATH = getDataFolder() + "/vote.log";

    /**
     * Config structure.
     */
    protected final FileConfiguration config = new VoteLoggerConfig(this).getConfig();

    /**
     * Message prefix.
     */
    protected static String prefix;

    @Override
    public void onEnable() {
        // Initialize prefix
        prefix = config.getString("prefix") + " ";

        // Register event VotifierEvent
        getServer().getPluginManager().registerEvents(
            new VoteLoggerListener(this), this
        );

        // Register VoteListener Command
        getCommand("vl").setExecutor(new VoteLoggerCommand(this));

        // GO GO GO
        log.info(config.getString("enable"));
    }

    @Override
    public void onDisable() {
        // Unregister all events
        HandlerList.unregisterAll(this);

        // GOOD BYE.
        log.info(config.getString("disable"));
    }
}
