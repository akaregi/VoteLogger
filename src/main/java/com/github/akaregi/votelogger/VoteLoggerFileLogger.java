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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.vexsoftware.votifier.model.Vote;

import lombok.NonNull;

/**
 * Logger to write a vote to the file.
 *
 * @author akaregi
 * @since 1.0.3-pre
 *
 */
public class VoteLoggerFileLogger {
    /**
     * Instance of VoteLogger.
     */
    private final VoteLogger vl;

    /**
     * Targeted path to log for VoteLogger.
     */
    private final String logpath;

    public VoteLoggerFileLogger(@NonNull VoteLogger vl) {
        this.vl = vl;
        logpath = vl.getDataFolder() + "/" + vl.config.getLogMsg("log-name");
    }

    /**
     * Writes vote information to the log file.
     *
     * @param vote Vote information to be logged
     */
    public boolean writeVoteLog(@NonNull Vote vote) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(logpath, true));

            writer.write(vl.config.getLogMsg(
                    // format style from config.yml
                    "logFormat",

                    // config.yml:logformat Time ({0})
                    VoteLoggerUtil.epochToISO8601(Integer.parseInt(vote.getTimeStamp()),
                            vl.config.getConfig().getInt("log-offset")),

                    // config.yml:logformat Service name ({1})
                    vote.getServiceName(),

                    // config.yml:logformat Username ({2})
                    vote.getUsername()));

            writer.newLine();
            writer.flush();
            writer.close();

            vl.log.info(vl.config.getLogMsg("console-log-success", vote.toString()));

            return true;

        } catch (IOException e) {
            vl.log.severe(vl.config.getLogMsg("console-log-failure", vote.toString()));
            e.printStackTrace();

            return false;
        }
    }
}
