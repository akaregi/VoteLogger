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
import com.vexsoftware.votifier.model.VotifierEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import lombok.NonNull;

public class VoteLoggerListener implements Listener {
    /**
     * Instance of VoteLogger.
     */
    private final VoteLogger vl;

    public VoteLoggerListener(VoteLogger vl) {
        this.vl = vl;
    }

    /**
     * Catches VotifierEvent and writes vote information to the log file.
     *
     * @param event Event for Votifier
     */
    @EventHandler
    public void onPlayerVote(@NonNull VotifierEvent event) {
        @NonNull
        Vote vote = event.getVote();

        writeVoteLog(vote);
    }

    /**
     * Writes vote information to the log file.
     *
     * @param vote Vote information to be logged
     */
    private void writeVoteLog(Vote vote) {
        try {
            @NonNull
            BufferedWriter writer = new BufferedWriter(new FileWriter(vl.LOGPATH, true));

            writer.write(
                    String.format(
                        "[%s] %s %s",
                        VoteLoggerUtil.epochToString(
                            Integer.parseInt(vote.getTimeStamp())
                        ),
                        vote.getServiceName(),
                        vote.getUsername())
                    );

            writer.newLine();
            writer.flush();
            writer.close();

            vl.log.info("Logged vote: " + vote.toString());

        } catch (IOException e) {
            vl.log.severe("Logging failure: " + vote.toString());
            e.printStackTrace();
        }
    }
}
