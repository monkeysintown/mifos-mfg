/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.mfg.generator.usecase.pebble.implementation;

import io.pebbletemplates.pebble.PebbleEngine;
import java.io.File;
import java.io.StringWriter;
import java.util.Map;
import java.util.zip.ZipFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
final class PebbleGeneratorRunUsecaseTest {
    @Test
    void run() throws Exception {
        try (var loader = PebbleZipLoader.builder()
                .zipFile(new ZipFile(new File("src/test/resources/templates.zip")))
                .build()) {

            var engine = new PebbleEngine.Builder()
                    .loader(loader)
                    .cacheActive(false)
                    .newLineTrimming(false)
                    .build();

            var template = engine.getTemplate("templates/hello.java");

            var writer = new StringWriter();

            template.evaluate(writer, Map.of("className", "Foo"));

            // log.warn(writer.toString());
            System.out.println(writer);
        }
    }
}
