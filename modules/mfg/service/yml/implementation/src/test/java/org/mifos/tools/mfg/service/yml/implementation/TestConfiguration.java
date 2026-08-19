///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.yml.implementation;

import static org.mifos.tools.mfg.service.yml.core.YmlTemplateIndexServiceConstants.MIFOS_TOOLS_MFG_SERVICE_YML_CORE_PACKAGE;
import static org.mifos.tools.mfg.service.yml.core.YmlTemplateIndexServiceConstants.MIFOS_TOOLS_MFG_SERVICE_YML_IMPLEMENTATION_PACKAGE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tools.jackson.dataformat.yaml.YAMLMapper;

@Configuration
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_YML_CORE_PACKAGE)
@ComponentScan(MIFOS_TOOLS_MFG_SERVICE_YML_IMPLEMENTATION_PACKAGE)
class TestConfiguration {
    @Bean
    YAMLMapper yamlMapper() {
        return YAMLMapper.builder().build();
    }
}
