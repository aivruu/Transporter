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
package me.qeklydev.transporter.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.util.List;

@ConfigSerializable
public final class Messages implements UsableConfigInterface {
  public String consoleDenied = "<prefix> <red>The console cannot execute this command.";

  public String permission = "<prefix> <red>You don't have permission for this!";

  public String unknownSubCommand = "<prefix> <red>That sub-command don't exist!";

  public List<String> help = List.of(
      "<gradient:purple:aqua>Transporter •</gradient> <gray>Available Commands Information",
      " <green>→ <gray><hover:show_text:'<yellow>Click to execute!'><click:run_command:'/ter help'>/transporter help</click></hover></gray> <white>Shows this same message.",
      " <green>→ <gray><hover:show_text:'<yellow>Click to execute!'><click:run_command:'/ter reload'>/transporter reload</click></hover></gray> <white>Reloads the plugin configuration.",
      " <green>→ <gray><hover:show_text:'<yellow>Click to execute!'><click:run_command:'/tp '>/transport <server|world|loc> <id|location> [player]</click></hover></gray> <white>Handle the teleport.");

  public String reloadSuccess = "<prefix> <green>The plugin configurations have been reloaded successful!";

  public String reloadFailed = "<prefix> <gradient:red:gold>Something went wrong during configuration reload, check the console for more information.";

  public String offlineTarget = "<prefix> <red>This player is not connected!";

  @Comment("""
      This message is used for between
      Proxies transport.""")
  public String unknownServer = "<prefix> <red>The server specified doesn't exist!";

  public String unknownWorld = "<prefix> <red>The world specified doesn't exist!";

  public String worldIsNotLoaded = "<prefix> <red>The world specified is not loaded!";

  public String chunkForLocationIsNotLoaded = "<prefix> <red>The chunk/s for the given location is not loaded.";

  public String transportSuccess = "<gradient:blue:green>Teleport to the <param:0> <type> successfuly!";

  public String transportWithPlayerSuccess = "<gradient:dark_purple:green>Teleport to the <param:0> <type>, and redirected to the player <target> successfuly!";
}
