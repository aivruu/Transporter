/*
 * This file is part of Transporter - https://github.com/aivruu/Transporter
 * Copyright (C) 2020-2024 Aivruu (https://github.com/aivruu)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package me.qeklydev.transporter.request;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used to represent and handle transport requests.
 *
 * @param id the player id.
 * @param type the request type.
 * @param name the targeted player name, can be {@code null}.
 * @param requestTime the time since the request was created.
 * @since 0.0.1
 */
public record CachedRequestModel(
    @NotNull String id, @NotNull String type, @Nullable String name, long requestTime) {
  /**
   * Returns whether the request time has expired.
   *
   * @param timeout the maximum time that the request
   *                can stay in memory.
   * @return The boolean status for this operation.
   * @since 0.0.1
   */
  public boolean expiredWithTimeout(final byte timeout) {
    /*
     * If the elapsed time since the request creation is
     * more than the maximum timeout given, the request has expired.
     */
    return (System.currentTimeMillis() - this.requestTime) > timeout;
  }
}
