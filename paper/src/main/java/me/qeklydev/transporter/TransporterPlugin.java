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

import java.util.Set;
import me.qeklydev.transporter.config.Configuration;
import me.qeklydev.transporter.config.ConfigurationProvider;
import me.qeklydev.transporter.config.Messages;
import me.qeklydev.transporter.module.ServiceModule;
import me.qeklydev.transporter.service.RegistrableServiceModel;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.InjectAll;
import team.unnamed.inject.Injector;
import team.unnamed.inject.Named;

import static me.qeklydev.transporter.utils.ComponentParsingUtils.apply;

@InjectAll
public final class TransporterPlugin extends JavaPlugin {
  private ComponentLogger logger;
  @Named("config")
  private ConfigurationProvider<Configuration> configProvider;
  @Named("messages")
  private ConfigurationProvider<Messages> messagesProvider;
  private Set<RegistrableServiceModel> services;

  @Override
  public void onLoad() {
    final var injector = Injector.create(new ServiceModule());
    injector.injectMembers(this);
  }

  @Override
  @SuppressWarnings("UnstableApiUsage")
  public void onEnable() {
    if ((this.configProvider == null) || (this.messagesProvider == null)) {
      this.logger.error(apply("<red>Some configuration provider have not been loaded yet!"));
      this.setEnabled(false);
      return;
    }
    for (final var serviceModel : this.services) {
      final var serviceIdentifierResolver = Placeholder.parsed("id", serviceModel.id());
      if (serviceModel.ok()) {
        continue;
      }
      if (!serviceModel.start()) {
        serviceModel.ok(false);
        this.setEnabled(false);
        this.logger.error(apply("<gradient:red:gold>The service <id> has not started due to some unexpected reason.", serviceIdentifierResolver));
        this.logger.error(apply("<gradient:gold:red>The plugin will not able usable if one service is not available."));
        break;
      }
      this.logger.info(apply("<gradient:green:blue>The service <id> has been enabled correctly.", serviceIdentifierResolver));
    }
  }

  @Override
  public void onDisable() {
    for (final var serviceModel : this.services) {
      final var serviceIdentifierResolver = Placeholder.parsed("id", serviceModel.id());
      if (!serviceModel.ok()) {
        continue;
      }
      if (!serviceModel.stop()) {
        this.logger.warn(apply("<gradient:red:gold>The service <id> have not been disabled correctly.", serviceIdentifierResolver));
        this.logger.warn(apply("<gradient:gold:red>This may affect some of the process, like data backup process or another more."));
        continue;
      }
      serviceModel.ok(false);
      this.logger.info(apply("<green>The service <id> has been stopped correctly.", serviceIdentifierResolver));
    }
  }
}
