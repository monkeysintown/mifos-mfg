/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.mfg.generator.service.maven.starter;

import static org.mifos.mfg.generator.service.maven.core.MavenGeneratorServiceConstants.MIFOS_MFG_GENERATOR_SERVICE_MAVEN_CORE_PACKAGE;
import static org.mifos.mfg.generator.service.maven.core.MavenGeneratorServiceConstants.MIFOS_MFG_GENERATOR_SERVICE_MAVEN_IMPLEMENTATION_PACKAGE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(MIFOS_MFG_GENERATOR_SERVICE_MAVEN_CORE_PACKAGE)
@ComponentScan(MIFOS_MFG_GENERATOR_SERVICE_MAVEN_IMPLEMENTATION_PACKAGE)
class MavenGeneratorServiceAutoConfiguration {}
