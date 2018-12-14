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
