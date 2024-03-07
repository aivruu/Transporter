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
package me.qeklydev.transporter.service;

import org.jetbrains.annotations.NotNull;

public interface RegistrableServiceModel {
  /**
   * Returns the identifier for this registered
   * service model.
   *
   * @return The service identifier.
   * @since 0.0.1
   */
  @NotNull String id();

  /**
   * Returns whether the service is enabled and
   * can be used.
   *
   * @return Whether the service is enabled and
   *     usable.
   * @since 0.0.1
   */
  boolean ok();

  /**
   * Mark this registered service as enabled
   * and usable.
   *
   * @param markServiceAsUsable mark service as
   *                            usable.
   * @since 0.0.1
   */
  void ok(final boolean markServiceAsUsable);

  boolean start();

  default boolean stop() {
    return true;
  }
}
