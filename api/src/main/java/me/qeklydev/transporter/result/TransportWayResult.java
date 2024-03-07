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
package me.qeklydev.transporter.result;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class is used to proportionate status codes
 * for each operation that the plugin trigger and require.
 *
 * @since 0.0.1
 */
public record TransportWayResult<T>(@Nullable T result, byte status) {
  /**
   * The teleport was between two servers.
   *
   * @since 0.0.1
   */
  public static final byte SINGLE_SERVER_BOUND_STATUS = 0;
  /**
   * The teleport was between two worlds.
   *
   * @since 0.0.1
   */
  public static final byte SINGLE_WORLD_BOUND_STATUS = 1;
  /**
   * The teleport was between two locations in the same
   * world.
   *
   * @since 0.0.1
   */
  public static final byte SINGLE_LOCATION_BOUND_STATUS = 2;
  /**
   * Something went wrong during the teleport process.
   *
   * @since 0.0.1
   */
  public static final byte ERROR_STATUS = 3;

  /**
   * Creates a new transport way result with the server status type.
   *
   * @return The {@link TransportWayResult} with the
   *     {@link TransportWayResult#SINGLE_SERVER_BOUND_STATUS} type.
   * @since 0.0.1
   */
  public static <C> @NotNull TransportWayResult<@Nullable C> withServerBound() {
    return new TransportWayResult<>(null, SINGLE_SERVER_BOUND_STATUS);
  }

  /**
   * Creates a new transport way result with the world status type.
   *
   * @return The {@link TransportWayResult} with the
   *     {@link TransportWayResult#SINGLE_WORLD_BOUND_STATUS} type.
   * @since 0.0.1
   */
  public static <C> @NotNull TransportWayResult<@Nullable C> withWorldBound() {
    return new TransportWayResult<>(null, SINGLE_WORLD_BOUND_STATUS);
  }

  /**
   * Creates a new transport way result with the location status type.
   *
   * @return The {@link TransportWayResult} with the
   *     {@link TransportWayResult#SINGLE_LOCATION_BOUND_STATUS} type.
   * @since 0.0.1
   */
  public static <C> @NotNull TransportWayResult<@Nullable C> withLocationBound() {
    return new TransportWayResult<>(null, SINGLE_LOCATION_BOUND_STATUS);
  }

  /**
   * Creates a new transport way result with the error status type.
   *
   * @return The {@link TransportWayResult} with the
   *     {@link TransportWayResult#ERROR_STATUS} type.
   * @since 0.0.1
   */
  public static <C> @NotNull TransportWayResult<@Nullable C> withError() {
    return new TransportWayResult<>(null, ERROR_STATUS);
  }

  /**
   * Returns whether the status is for between Server type.
   *
   * @return Whether the status is
   *     {@link TransportWayResult#SINGLE_SERVER_BOUND_STATUS} type.
   * @since 0.0.1
   */
  public boolean betweenServer() {
    return this.status == SINGLE_SERVER_BOUND_STATUS;
  }

  /**
   * Returns whether the status is for between World type.
   *
   * @return Whether the status is
   *     {@link TransportWayResult#SINGLE_WORLD_BOUND_STATUS} type.
   * @since 0.0.1
   */
  public boolean betweenWorld() {
    return this.status == SINGLE_WORLD_BOUND_STATUS;
  }

  /**
   * Returns whether the status is for between Location type.
   *
   * @return Whether the status is
   *     {@link TransportWayResult#SINGLE_LOCATION_BOUND_STATUS} type.
   * @since 0.0.1
   */
  public boolean betweenLocation() {
    return this.status == SINGLE_LOCATION_BOUND_STATUS;
  }

  /**
   * Returns whether the status is for error type.
   *
   * @return Whether the status is for
   *     {@link TransportWayResult#ERROR_STATUS} type.
   * @since 0.0.1
   */
  public boolean error() {
    return this.status == ERROR_STATUS;
  }
}
