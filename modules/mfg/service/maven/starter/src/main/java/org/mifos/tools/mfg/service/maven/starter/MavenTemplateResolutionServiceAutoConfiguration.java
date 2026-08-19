///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.maven.starter;

import static org.mifos.tools.mfg.service.maven.core.MavenTemplateResolutionServiceConstants.MIFOS_TOOLS_MFG_SERVICE_MAVEN_CORE_PACKAGE;
import static org.mifos.tools.mfg.service.maven.core.MavenTemplateResolutionServiceConstants.MIFOS_TOOLS_MFG_SERVICE_MAVEN_IMPLEMENTATION_PACKAGE;

import lombok.extern.slf4j.Slf4j;
import org.mifos.tools.mfg.service.maven.core.MavenTemplateResolutionServiceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@EnableConfigurationProperties({MavenTemplateResolutionServiceProperties.class})
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_MAVEN_CORE_PACKAGE)
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_MAVEN_IMPLEMENTATION_PACKAGE)
class MavenTemplateResolutionServiceAutoConfiguration {}
