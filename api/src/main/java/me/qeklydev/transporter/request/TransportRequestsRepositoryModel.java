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

import java.util.HashMap;
import java.util.Map;
import me.qeklydev.transporter.repository.RepositoryModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Repository model used for handling transport
 * requests in cache.
 *
 * @since 0.0.1
 */
public final class TransportRequestsRepositoryModel implements RepositoryModel<CachedRequestModel> {
  private final Map<String, CachedRequestModel> requests;

  public TransportRequestsRepositoryModel() {
    this.requests = new HashMap<>();
  }

  @Override
  public @Nullable CachedRequestModel findOneSync(final @NotNull String id) {
    return this.requests.get(id);
  }

  @Override
  public void addOneSync(final @NotNull CachedRequestModel cachedRequestModel) {
    this.requests.put(cachedRequestModel.id(), cachedRequestModel);
  }

  @Override
  public @Nullable CachedRequestModel removeOneSync(final @NotNull String id) {
    return this.requests.remove(id);
  }
}
