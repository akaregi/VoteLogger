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

import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneOffset;

import lombok.NonNull;

/**
 * Useful utilities.
 *
 * @author akaregi
 * @since 1.0.1-pre
 *
 */
final class VoteLoggerUtil {
    /**
     * Gets current time in ISO8601 format.
     *
     * @author akaregi
     * @since 1.0.4-pre
     *
     * @param offset Time difference. E.g. 9 in JST
     *
     * @return ISO8601 time
     */
    static String getTime(@NonNull Integer offset) {
        return Instant.now().atOffset(ZoneOffset.ofHours(offset)).toString();
    }

    /**
     * Returns formatted text.
     *
     * For example, Given "Message: {0}" as format, and "I love you." as args, this
     * method returns "Message: I love you.".
     *
     * @author akaregi
     * @since 1.0.6
     *
     * @param format Format text, should be included like {0}, {1}, and so on.
     * @param args   Arguments to be putted.
     *
     * @return Formatted text!
     */
    static String getFmtText(String format, Object... args) {
        return MessageFormat.format(format, args).replace("&", "§");
    }
}
