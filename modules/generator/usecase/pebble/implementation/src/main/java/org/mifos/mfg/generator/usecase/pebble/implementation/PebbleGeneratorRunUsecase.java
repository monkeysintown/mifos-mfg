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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mifos.mfg.generator.core.model.MfgGeneratorRunRequest;
import org.mifos.mfg.generator.core.model.MfgGeneratorRunResponse;
import org.mifos.mfg.generator.core.service.MfgGeneratorTemplateResolutionService;
import org.mifos.mfg.generator.core.usecase.MfgGeneratorRunUsecase;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PebbleGeneratorRunUsecase implements MfgGeneratorRunUsecase {
    private final MfgGeneratorTemplateResolutionService resolutionService;
    private final PebbleEngine engine;

    @Override
    public MfgGeneratorRunResponse execute(MfgGeneratorRunRequest request) {
        var files = resolutionService.resolve(request.getTemplateDependency());

        files.forEach(templateFile -> {
            try (var loader = PebbleZipLoader.builder()
                    .zipFile(new ZipFile(new File(templateFile)))
                    .build()) {

                var engine = new PebbleEngine.Builder()
                        .loader(loader)
                        .cacheActive(false)
                        .build();

                var template = engine.getTemplate(request.getTemplate());

                var writer = new StringWriter();

                template.evaluate(writer, Map.of("className", "Dummy"));

                // log.warn(writer.toString());
                System.out.println(writer);
            } catch (Exception e) {

            }
        });
        return null;
    }
}
