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
package me.qeklydev.transporter.module;

import java.nio.file.Path;
import me.qeklydev.transporter.config.Configuration;
import me.qeklydev.transporter.config.ConfigurationProvider;
import me.qeklydev.transporter.config.Messages;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jetbrains.annotations.NotNull;
import team.unnamed.inject.AbstractModule;

public final class MainModule extends AbstractModule {
  private final ComponentLogger logger;
  private final Path pluginFolder;

  public MainModule(final @NotNull ComponentLogger logger, final @NotNull Path pluginFolder) {
    this.logger = logger;
    this.pluginFolder = pluginFolder;
  }

  @Override
  protected void configure() {
    super.bind(ComponentLogger.class).toInstance(this.logger);
    // Inject references for configuration providers.
    super.bind(ConfigurationProvider.class).named("config")
        .toInstance(ConfigurationProvider.loadProvider(pluginFolder, "config", Configuration.class));
    super.bind(ConfigurationProvider.class).named("messages")
        .toInstance(ConfigurationProvider.loadProvider(pluginFolder, "messages", Messages.class));
  }
}
