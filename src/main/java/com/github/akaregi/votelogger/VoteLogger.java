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

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class VoteLogger extends JavaPlugin {

    /**
     * Version of VoteLogger.
     */
    protected final String version = getDescription().getVersion();

    /**
     * Logger for VoteLogger
     */
    protected final Logger log = Logger.getLogger(getName());

    /**
     * Config structure.
     */
    protected final VoteLoggerConfig config = new VoteLoggerConfig(this);

    /**
     * Author name
     */
    protected final List<String> author = getDescription().getAuthors();

    /**
     * Message prefix.
     */
    protected String prefix = "";

    @Override
    public void onEnable() {
        // Initialize prefix
        prefix = config.getLogMsg("plugin-prefix") + " ";

        // Register event VotifierEvent
        getServer().getPluginManager().registerEvents(
            new VoteLoggerListener(this), this
        );

        // Register VoteListener Command
        getCommand("vl").setExecutor(new VoteLoggerCommand(this));

        // GO GO GO
        log.info(config.getLogMsg("plugin-enabled", version));
    }

    @Override
    public void onDisable() {
        // Unregister all events
        HandlerList.unregisterAll(this);

        // GOOD BYE.
        log.info(config.getLogMsg("plugin-disabled", version));
    }
}
