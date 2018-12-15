/*
 *  This file is part of VoteLogger.
 *
 *  VoteLogger: A software for logging votes, works with Votifier.
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

import java.time.Instant;

import com.vexsoftware.votifier.model.Vote;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import lombok.NonNull;
import lombok.val;

/**
 * Dispatches and executes commands for VoteLogger.
 *
 * @author akaregi
 * @since 1.0.2-pre
 *
 */
public class VoteLoggerCommand implements CommandExecutor {
    private final VoteLogger vl;

    /**
     * String of implemented sub-commands.
     */
    private final String availableCommands = "help, version";

    public VoteLoggerCommand(@NonNull VoteLogger vl) {
        this.vl = vl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("votelogger.admin")) {
            // Hey you're admin, right?
            if (args.length != 0) {
                // /vl version
                if (
                    args[0].equalsIgnoreCase("version") ||
                    args[0].equalsIgnoreCase("ver")
                ) {
                    sender.sendMessage(vl.config.getUserMsg(
                        "plugin-version", vl.version, vl.author
                    ));

                    return true;
                }

                // /vl help
                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(vl.config.getUserMsg(
                        "command-available", availableCommands
                    ));

                    return true;
                }

                // /vl log
                if (args[0].equalsIgnoreCase("log")) {
                    return commandLog(sender, args);
                }

                sender.sendMessage(vl.config.getUserMsg("command-unknown"));

                return false;
            } else {
                sender.sendMessage(vl.config.getUserMsg("command-blank"));

                sender.sendMessage(vl.config.getUserMsg(
                    "command-available", availableCommands
                ));

                return false;
            }
        } else {
            // YOU ARE NOT ADMINISTRATOR, SORRY.
            sender.sendMessage(vl.config.getLogMsg("permission-insufficient"));
            return true;
        }
	}

    /**
     * /vl log [serviceName] [username] [address]
     *
     * @author akaregi
     * @since 1.0.3-pre
     *
     * @param sender Sender who executed this command
     * @param args   Arguments for logging.
     *
     * @return If logging ended in success, true, otherwise false.
     */
    private boolean commandLog(CommandSender sender, String[] args) {
        @NonNull
        val serviceName = (args.length == 2) ? args[1] : "serviceName";

        @NonNull
        val username = (args.length == 3) ? args[2] : "username";

        @NonNull
        val address = (args.length == 4) ? args[3] : "address.com";

        @NonNull
        val timeStamp = String.valueOf(Instant.now().toEpochMilli() / 1000L);

        val vote = new Vote(serviceName, username, address, timeStamp);

        if (new VoteLoggerFileLogger(vl).writeVoteLog(vote)) {
            sender.sendMessage(vl.config.getUserMsg("log-write-success", vote));
            return true;
        } else {
            sender.sendMessage(vl.config.getUserMsg("log-write-failure", vote));
            return false;
        }
    }

}
