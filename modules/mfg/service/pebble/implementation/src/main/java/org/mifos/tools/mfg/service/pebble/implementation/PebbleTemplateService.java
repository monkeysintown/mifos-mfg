///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.pebble.implementation;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL;
import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_TEMPLATE_ERROR_LOAD;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.Loader;
import io.pebbletemplates.pebble.utils.PathUtils;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;
import org.mifos.tools.mfg.core.service.MfgTemplateService;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PebbleTemplateService implements MfgTemplateService {
    @Override
    public String eval(String templateRef, Map<String, Object> context) {
        var engine = new PebbleEngine.Builder()
                .loader(new ZipLoader())
                .cacheActive(false)
                .newLineTrimming(false)
                .build();

        var template = engine.getTemplate(templateRef);

        try (var writer = new StringWriter()) {
            template.evaluate(writer, context);
            return writer.toString();
        } catch (IOException ioe) {
            throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL, ioe, List.of(templateRef)));
        }
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static final class ZipLoader implements Loader<String> {
        @Builder.Default
        private Charset charset = UTF_8;

        @Builder.Default
        private String prefix = "";

        @Builder.Default
        private String suffix = ".peb";

        @Override
        public Reader getReader(String templateName) {
            try {
                return Files.newBufferedReader(getFile(templateName), charset);
            } catch (IOException ioe) {
                throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_LOAD, ioe, List.of(templateName)));
            }
        }

        @Override
        public void setCharset(String charset) {
            this.charset = Charset.forName(charset);
        }

        @Override
        public String resolveRelativePath(String relativePath, String anchorPath) {
            return PathUtils.resolveRelativePath(relativePath, anchorPath, File.separatorChar);
        }

        @Override
        public String createCacheKey(String templateName) {
            return templateName;
        }

        @Override
        public boolean resourceExists(String templateName) {
            try {
                var filePath = getFile(templateName);

                return Files.exists(filePath);
            } catch (IOException ioe) {
                throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_LOAD, ioe, List.of(templateName)));
            }
        }

        private Path getFile(String templateName) throws IOException {
            templateName = templateName + (this.getSuffix() == null ? "" : this.getSuffix());
            templateName = PathUtils.sanitize(templateName, File.separatorChar);

            var parts = templateName.split("@");

            return FileSystems.newFileSystem(Path.of(parts[0])).getPath(parts[1]);
        }
    }
}
