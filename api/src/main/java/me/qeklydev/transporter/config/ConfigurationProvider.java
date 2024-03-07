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

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

/**
 * This class is used for creation and handling
 * of the configuration models.
 *
 * @param model the configuration model.
 * @param modelLoader the loader for the model.
 * @param modelClass the model serializable class.
 * @param <C> the model class.
 * @since 0.0.1
 */
public record ConfigurationProvider<C>(
    @NotNull AtomicReference<C> model, @NotNull HoconConfigurationLoader modelLoader, @NotNull Class<@NotNull C> modelClass) {
  /**
   * Reloads the serializable model and create a new
   * completable future that provides a status for
   * this operation.
   *
   * @return The {@link CompletableFuture} with a
   *     supply of boolean type.
   *     <p> </p>
   *
   *     - {@code true} if model was reloaded correctly.
   *     <p> </p>
   *
   *     - {@code false} if something went wrong.
   * @since 0.0.1
   */
  public @NotNull CompletableFuture<@NotNull Boolean> updateAndGet() {
    return CompletableFuture.supplyAsync(() -> {
      try {
        final var node = this.modelLoader.load();
        final var newModel = node.get(this.modelClass);
        node.set(this.modelClass, newModel);
        this.modelLoader.save(node);
        return true;
      } catch (final ConfigurateException exception) {
        exception.printStackTrace();
        return false;
      }
    });
  }

  /**
   * Returns the serializable model reference for this
   * configuration provider instance.
   *
   * @return The serializable model reference.
   * @since 0.0.1
   */
  public @NotNull C get() {
    return this.model.get();
  }

  /**
   * Creates a new configuration provider based on the information
   * given to the loader method.
   *
   * @param pluginFolder the plugin folder represented as a Path.
   * @param file the file name.
   * @param clazz the serializable model for the file.
   * @param <C> a model that implements usable config interface.
   * @return The built {@link ConfigurationProvider} or {@code null}
   *     if something went wrong during the load.
   * @since 0.0.1
   */
  public static <C extends UsableConfigInterface> @Nullable ConfigurationProvider<C> loadProvider(
      final @NotNull Path pluginFolder, final @NotNull String file, final @NotNull Class<@NotNull C> clazz) {
    final var providedModelLoader = HoconConfigurationLoader.builder()
        .defaultOptions(opts -> opts
            .header("""
                Transporter | Go to any server on your Velocity network, and any world or
                location in your Paper server.

                - This plugin make usage of the MiniMessage Format System which provides
                awesome features to formats customization.
                - This plugin don't support the legacy format, and isn't recommended still
                using it, an any moment could be removed definitively.""")
            .shouldCopyDefaults(true))
        .path(pluginFolder.resolve(file + ".conf"))
        .build();
    try {
      final var loadedCommentedNode = providedModelLoader.load();
      final var configurationModel = loadedCommentedNode.get(clazz);
      loadedCommentedNode.set(clazz, configurationModel);
      providedModelLoader.save(loadedCommentedNode);
      // Model based on the class given cannot be null.
      assert configurationModel != null;
      return new ConfigurationProvider<>(
          new AtomicReference<>(configurationModel), providedModelLoader, clazz);
    } catch (final ConfigurateException exception) {
      exception.printStackTrace();
      return null;
    }
  }
}
