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
package me.qeklydev.transporter.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RepositoryModel<T> {
  /**
   * Returns the value from the repository
   * based on the given identifier.
   *
   * @param id the value key.
   * @return The value or {@code null} if
   *     key mapping doesn't exist in the
   *     repository model.
   * @since 0.0.1
   */
  @Nullable T findOneSync(final @NotNull String id);

  /**
   * Puts a new cached model in the repository
   * using their key provided for mapping.
   *
   * @param t the value to store.
   * @since 0.0.1
   */
  void addOneSync(final @NotNull T t);

  /**
   * Removes the mapping for the given key from
   * the repository with their expected value.
   * </p>
   * If exists, will return the assigned value
   * of the removed key.
   *
   * @param id the value key.
   * @return The removed value.
   * @since 0.0.1
   */
  @Nullable T removeOneSync(final @NotNull String id);
}
