///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.yml.implementation;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static org.mifos.commons.boot.core.exception.MifosCliException.MifosCliErrorCode.MIFOS_COMMONS_ERROR_CLI_GENERIC;
import static org.mifos.tools.mfg.core.MfgConstants.MIFOS_TOOLS_MFG_CACHE_TEMPLATE;
import static org.mifos.tools.mfg.core.model.MfgTemplateIndexData.TemplateParameterType.ENUM;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mifos.commons.boot.core.exception.MifosCliException;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.model.MfgTemplateIndexData;
import org.mifos.tools.mfg.core.service.MfgTemplateIndexService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tools.jackson.dataformat.yaml.YAMLMapper;

@Slf4j
@RequiredArgsConstructor
@Cacheable(MIFOS_TOOLS_MFG_CACHE_TEMPLATE)
@Service
class YmlTemplateIndexService implements MfgTemplateIndexService {
    private final YAMLMapper mapper;

    @Cacheable(key = "#filePath")
    @Override
    public MfgTemplateIndexData index(Path filePath) {
        // TODO: implement this!

        return MfgTemplateIndexData.builder()
                .files(List.of(MfgTemplateIndexData.TemplateFileDefinition.builder()
                        .template("content/Foo.java")
                        .build()))
                .build();
    }

    @Override
    public MfgTemplateIndexData parse(InputStream data) {
        return mapper.readValue(data, MfgTemplateIndexData.class);
    }

    @Override
    public void validate(MfgTemplateIndexData index) {
        // TODO: implement this
        requireNonNull(index, "Template is required");
        requireNonNull(index.getMetadata(), "Template metadata is required");

        if (StringUtils.isBlank(index.getMetadata().getName())) {
            throw new MifosCliException(
                    MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, "name", "Template name is required"));
        }

        if (nonNull(index.getParameters())) {
            for (var param : index.getParameters()) {
                validateParameter(param);
            }
        }

        if (index.getFiles() == null || index.getFiles().isEmpty()) {
            throw new MifosCliException(MifosError.of(
                    MIFOS_COMMONS_ERROR_CLI_GENERIC, "files", "At least one file definition is required"));
        }
    }

    private void validateParameter(MfgTemplateIndexData.TemplateParameter param) {
        if (StringUtils.isBlank(param.getName())) {
            throw new MifosCliException(
                    MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, "name", "Parameter name is required"));
        }

        if (param.getType() == null) {
            throw new MifosCliException(
                    MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, param.getName(), "Parameter type is required"));
        }

        if (ENUM.equals(param.getType())
                && (param.getOptions() == null || param.getOptions().isEmpty())) {
            throw new MifosCliException(
                    MifosError.of(MIFOS_COMMONS_ERROR_CLI_GENERIC, param.getName(), "Enum parameter requires options"));
        }
    }
}
