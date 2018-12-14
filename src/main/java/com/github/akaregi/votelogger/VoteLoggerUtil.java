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

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

import org.bukkit.Bukkit;

import lombok.NonNull;

public final class VoteLoggerUtil {
    /**
     * Converts epoch time to String formatted with ISO 8601.
     *
     * @param epoch Epoch time
     * @param offset Offset of Time
     *
     * @return String formatted with ISO 8601
     *
     */
    public static String epochToISO8601(long epoch, @NonNull Integer offset) {
        return Instant.ofEpochSecond(epoch).atOffset(ZoneOffset.ofHours(offset)).toString();
    }

    /**
     * Converts Minecraft ID to UUID
     *
     * @param id Minecraft ID to convert
     * @return UUID, or new UUID(0, 0) if ID is unknown
     */
    public static UUID MCIDToUUID(@NonNull String id) {
        try {
            return Bukkit.getPlayer(id).getUniqueId();
        } catch (NullPointerException e) {
            return new UUID(0, 0);
        }
    }
}
