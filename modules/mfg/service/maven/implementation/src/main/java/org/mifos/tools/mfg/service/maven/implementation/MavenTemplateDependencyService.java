///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.maven.implementation;

import static java.util.Objects.isNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.mifos.tools.mfg.core.service.MfgTemplateDependencyService;
import org.mifos.tools.mfg.service.maven.core.MavenTemplateResolutionServiceProperties;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
final class MavenTemplateDependencyService implements MfgTemplateDependencyService {
    private final MavenTemplateResolutionServiceProperties properties;

    @Override
    public List<String> resolve(String spec) {
        var files = Maven.configureResolver()
                .workOffline(properties.getOffline())
                .withClassPathResolution(properties.getWithClasspathResolution())
                .resolve(spec)
                .withoutTransitivity()
                .asFile();

        if (isNull(files)) {
            return List.of();
        }

        return Arrays.stream(files).map(File::getAbsolutePath).toList();
    }
}
