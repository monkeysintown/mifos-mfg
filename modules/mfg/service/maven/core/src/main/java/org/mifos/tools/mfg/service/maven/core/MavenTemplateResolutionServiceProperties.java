///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.maven.core;

import static org.mifos.tools.mfg.service.maven.core.MavenTemplateResolutionServiceConstants.MIFOS_TOOLS_MFG_SERVICE_MAVEN_PROPERTIES_PREFIX;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = MIFOS_TOOLS_MFG_SERVICE_MAVEN_PROPERTIES_PREFIX)
public class MavenTemplateResolutionServiceProperties {
    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    private Boolean offline = false;

    @Builder.Default
    private Boolean withClasspathResolution = true;

    private String settingsLocation;
}
