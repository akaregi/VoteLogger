/*
 *  This file is part of VoteLogger.
 *
 *  VoteLogger: A software for logging votes,works with Votifier.
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

import java.util.Date;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public final class VoteLoggerUtil {

    /**
     * Converts epoch time to String.
     *
     * @param  epoch Epoch time
     *
     * @return String that is converted from epoch time.
     *
     */
    public static String epochToString(long epoch) {
        return new Date(epoch * 1000).toString();
    }

    /**
     * Sends message to sender.
     *
     * @param sender CommandSender, who executed some command.
     * @param config Configuration that contains language settings
     * @param path   Path of language file's item
     *
     * @return Formatted message that prefix is connected.
     */
    public static void sendMessage(CommandSender sender, FileConfiguration config, String path) {
        sender.sendMessage(VoteLogger.prefix + config.getString(path));
    }
}
