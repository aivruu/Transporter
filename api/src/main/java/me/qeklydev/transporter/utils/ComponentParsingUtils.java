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
package me.qeklydev.transporter.utils;

import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to perform string/s deserialization
 * into components and tags resolving.
 *
 * @since 0.0.1
 */
public final class ComponentParsingUtils {
  public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
  private static final Component NEW_LINE_COMPONENT = Component.newline();

  private ComponentParsingUtils() {
    // -> Static classes cannot be instantiated.
    throw new UnsupportedOperationException("This class is for utility and cannot be instantiated.");
  }

  /**
   * Uses the text string to create a component based
   * on the content of the string given.
   *
   * @param text the preferred text.
   * @return The {@link Component}.
   * @since 0.0.1
   */
  public static @NotNull Component apply(final @NotNull String text, final TagResolver @NotNull... resolvers) {
    return MINI_MESSAGE.deserialize(text, resolvers);
  }

  /**
   * Uses the text list to create a component based
   * on the content of the list given.
   *
   * @param text the preferred text.
   * @return The {@link Component}.
   * @since 0.0.1
   */
  public static @NotNull Component apply(final @NotNull List<@NotNull String> text) {
    final var componentBuilder = Component.text();
    for (final var iterated : text ) {
      componentBuilder.append(MINI_MESSAGE.deserialize(iterated)).append(NEW_LINE_COMPONENT);
    }
    return componentBuilder.build();
  }
}
