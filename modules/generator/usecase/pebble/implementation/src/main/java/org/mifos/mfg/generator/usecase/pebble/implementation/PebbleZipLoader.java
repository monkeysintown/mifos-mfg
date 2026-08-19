/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.mfg.generator.usecase.pebble.implementation;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mifos.mfg.generator.core.exception.MfgGeneratorException.MifosBillingErrorCode.MIFOS_MARKET_CONTENT_ERROR_TEMPLATE_NOT_FOUND;

import io.pebbletemplates.pebble.loader.Loader;
import io.pebbletemplates.pebble.utils.PathUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.mfg.generator.core.exception.MfgGeneratorException;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
final class PebbleZipLoader implements Loader<String>, AutoCloseable {
    private ZipFile zipFile;

    @Builder.Default
    private Charset charset = UTF_8;

    @Builder.Default
    private String prefix = "";

    @Builder.Default
    private String suffix = ".peb";

    @Override
    public Reader getReader(String templateName) {
        var resolvedName = resolveName(templateName);
        var entry = zipFile.getEntry(resolvedName);

        if (entry == null) {
            return null;
        }

        try {
            var inputStream = zipFile.getInputStream(entry);
            return new InputStreamReader(inputStream, charset);
        } catch (IOException ioe) {
            throw new MfgGeneratorException(
                    MifosError.of(MIFOS_MARKET_CONTENT_ERROR_TEMPLATE_NOT_FOUND, ioe, List.of(templateName)));
        }
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
        String resolvedName = resolveName(templateName);
        return zipFile.getEntry(resolvedName) != null;
    }

    @Override
    public void setCharset(String charset) {
        this.charset = Charset.forName(charset);
    }

    ///
    /// Closes the underlying ZipFile. Call this when shutting down your application.
    ///
    @Override
    public void close() throws IOException {
        if (zipFile != null) {
            zipFile.close();
        }
    }

    ///
    /// Resolves the template name by appending the prefix and suffix.
    /// Also normalizes Windows backslashes to forward slashes, as ZIP entries
    /// always use forward slashes.
    ///
    private String resolveName(String templateName) {
        String name = prefix + templateName + suffix;
        return name.replace('\\', '/');
    }
}
