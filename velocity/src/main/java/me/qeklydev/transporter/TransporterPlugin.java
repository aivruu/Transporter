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
package me.qeklydev.transporter;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import java.io.File;
import me.qeklydev.transporter.config.Configuration;
import me.qeklydev.transporter.config.ConfigurationProvider;
import me.qeklydev.transporter.config.Messages;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Plugin(
    id = "transporter",
    name = "Transporter",
    authors = { "Qekly" },
    version = Constants.VERSION,
    url = "https://github.com/aivruu/Transporter"
)
public final class TransporterPlugin {
  @Inject
  private Logger logger;
  @Inject
  @DataDirectory
  private File pluginDirectory;
  private ConfigurationProvider<Configuration> configProvider;
  private ConfigurationProvider<Messages> messagesProvider;

  @Subscribe
  void onProxyInitialization(final @NotNull ProxyInitializeEvent event) {
    final var pluginDirectoryToPath = this.pluginDirectory.toPath();
    this.configProvider = ConfigurationProvider.loadProvider(pluginDirectoryToPath, "config", Configuration.class);
    this.messagesProvider = ConfigurationProvider.loadProvider(pluginDirectoryToPath, "messages", Messages.class);
    if ((this.configProvider == null) || (this.messagesProvider == null)) {
      throw new IllegalStateException("Some configuration provider have not been loaded yet!");
    }
  }
}
