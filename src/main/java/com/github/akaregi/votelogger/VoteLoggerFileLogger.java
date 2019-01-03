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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.vexsoftware.votifier.model.Vote;

import lombok.NonNull;

/**
 * Logger to write a vote to the file.
 *
 * @author akaregi
 * @since 1.0.3-pre
 *
 */
class VoteLoggerFileLogger {
    /**
     * Writes vote information to the log file.
     *
     * @author akaregi
     * @since 1.0.4-pre
     *
     * @param vote    Vote object, must be contained the time, service name,
     *                address, and username.
     * @param format  Logging format. It must be like: "[{0}] {1}({2}) {3}". {0} is
     *                the time. {1} is the service name. {2} is the address. {4} is
     *                the username.
     * @param offset  Time offset. For example, in Japan, must be "9" (UTC+9).
     * @param logPath Path you want to write, includes file name (i.e.
     *                path/vote.log)
     */
    static boolean writeVoteLog(@NonNull Vote vote, String format, Integer offset, String logPath) {
        try {
            write(logPath, createRecord(vote, format, offset));

            return true;
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * Attempts to write a record to specified file.
     *
     * @author akaregi
     * @since 1.0.6
     *
     * @see #writeVoteLog(Vote, String, Integer, String)
     * @see #write(String, String)
     *
     * @param vote   See {@see #writeVoteLog}
     * @param format See {@see writeVoteLog}
     * @param offset See {@see writeVoteLog}
     */
    private static String createRecord(Vote vote, String format, Integer offset) {
        return VoteLoggerUtil.getFmtText(
            // format style from config.yml
            format,

            // config.yml:logformat Time ({0})
            // NOTE: In Protocol v1 format of vote.getTimeStamp() can't be expected
            // So uses system time.
            VoteLoggerUtil.getTime(offset),

            // config.yml:logformat Service name ({1})
            vote.getServiceName(),

            // config.yml:logformat Address ({2})
            vote.getAddress(),

            // config.yml:logformat Username ({3})
            vote.getUsername()
        );
    }

    /**
     * Attempts to write a record to specified file.
     *
     * @author akaregi
     * @since 1.0.6
     *
     * @see #createRecord(Vote, String, Integer)
     *
     * @param path   See {@see #writeVoteLog}
     * @param record Record which will be written
     */
    private static void write(String path, String record) throws IOException {
        Files.write(
            Paths.get(path),
            record.getBytes(),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        );
    }
}
