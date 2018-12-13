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

import org.bukkit.configuration.file.FileConfiguration;

import lombok.Getter;

public class VoteLoggerConfig {
    /**
     * Instance of VoteLogger.
     */
    private final VoteLogger vl;

    /**
     * Configuration file (config.yml) for VoteLogger.
     */
    @Getter
    private FileConfiguration config;

    public VoteLoggerConfig(VoteLogger vl) {
        this.vl = vl;
        loadConfig();
    }

    private void loadConfig() {
        vl.saveDefaultConfig();
        config = vl.getConfig();
    }
}
