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

import java.text.MessageFormat;

import org.bukkit.configuration.file.FileConfiguration;

import lombok.Getter;
import lombok.NonNull;

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

    public VoteLoggerConfig(@NonNull VoteLogger vl) {
        this.vl = vl;
        loadConfig();
    }

    private void loadConfig() {
        vl.saveDefaultConfig();
        config = vl.getConfig();
    }

    /**
     * Gets plain message from config.yml.
     *
     * @param path Path to get YAML's message
     * @param args Arguments to replace message's variables
     */
    public String getLogMsg(@NonNull String path, Object ...args) {
        return MessageFormat.format(config.getString(path), args).replace("&", "§");
    }

    /**
     * Gets formatted message (prefix + message) from config.yml.
     *
     * @param path Path to get YAML's message
     * @param args Arguments to replace message's variables
     */
    public String getUserMsg(@NonNull String path, Object ... args) {
        return vl.prefix + getLogMsg(path, args);
    }
}
