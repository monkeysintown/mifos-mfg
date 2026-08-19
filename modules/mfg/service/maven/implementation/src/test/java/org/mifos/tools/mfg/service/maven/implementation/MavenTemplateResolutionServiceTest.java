///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.maven.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.extern.slf4j.Slf4j;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.jupiter.api.Test;

@Slf4j
final class MavenTemplateResolutionServiceTest {
    @Test
    void resolve() {
        var files = Maven.configureResolver()
                .workOffline(false)
                .withClassPathResolution(true)
                .resolve("info.picocli:picocli:4.7.7")
                .withoutTransitivity()
                .asFile();

        assertNotNull(files, "No files found!");

        for (var file : files) {
            log.warn("Resolved: {}", file.getAbsolutePath());
        }
    }
}
