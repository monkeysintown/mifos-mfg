///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.starter;

import lombok.extern.slf4j.Slf4j;
import org.mifos.tools.mfg.core.MfgProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Slf4j
@EnableConfigurationProperties(MfgProperties.class)
@Import({MfgCacheConfiguration.class})
final class MfgAutoConfiguration {}
