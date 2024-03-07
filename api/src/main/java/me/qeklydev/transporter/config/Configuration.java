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

@ConfigSerializable
public final class Configuration implements UsableConfigInterface {
  @Comment("""
      Format used for the prefix that will be used on
      the plugin messages.
      -> This is an example of MiniMessage format usage.""")
  public String prefix = "<gradient:purple:aqua>• Transporter →</gradient>";

  @Comment("""
      Verifies if the targeted player is on ground
      before be teleported to him.""")
  public boolean checkIfPlayerIsOnGround = true;

  @Comment("""
      The maximum time allowed to accept a transport
      request from another player to the current playerr
      location. If need be disable it, type '0'.

      Administrators can bypass this and be transported
      immediately.""")
  public byte transportRequestTimeout = 60;
}
