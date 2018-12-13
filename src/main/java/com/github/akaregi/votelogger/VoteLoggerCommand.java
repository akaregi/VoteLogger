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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VoteLoggerCommand implements CommandExecutor {
    private final VoteLogger vl;

    public VoteLoggerCommand(VoteLogger vl) {
        this.vl = vl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("votelogger.admin")) {
            // Hey you're admin, right?
            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("version")) {
                    sender.sendMessage(VoteLogger.prefix + vl.VERSION);
                    return true;
                }

                VoteLoggerUtil.sendMessage(sender, vl.config, "command-unknown");

                return false;
            } else {
                VoteLoggerUtil.sendMessage(sender, vl.config, "command-blank");

                return true;
            }
        } else {
            // YOU ARE NOT ADMINISTRATOR, SORRY.
            VoteLoggerUtil.sendMessage(sender, vl.config, "permission");
            return true;
        }
	}

}
