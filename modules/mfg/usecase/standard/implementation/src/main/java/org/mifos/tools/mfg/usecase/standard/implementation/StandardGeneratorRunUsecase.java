///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.usecase.standard.implementation;

import static org.mifos.tools.mfg.core.exception.MfgException.MifosGeneratorErrorCode.MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL;

import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.tools.mfg.core.exception.MfgException;
import org.mifos.tools.mfg.core.model.MfgRunRequest;
import org.mifos.tools.mfg.core.model.MfgRunResponse;
import org.mifos.tools.mfg.core.model.MfgTemplateIndexData;
import org.mifos.tools.mfg.core.service.MfgTemplateDependencyService;
import org.mifos.tools.mfg.core.service.MfgTemplateIndexService;
import org.mifos.tools.mfg.core.service.MfgTemplateService;
import org.mifos.tools.mfg.core.usecase.MfgRunUsecase;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StandardGeneratorRunUsecase implements MfgRunUsecase {
    private final MfgTemplateDependencyService templateDependencyService;
    private final MfgTemplateIndexService templateIndexService;
    private final MfgTemplateService templateService;

    @Override
    public MfgRunResponse execute(MfgRunRequest request) {
        var files = Optional.ofNullable(request.getTemplateDependency())
                .filter(StringUtils::isNotBlank)
                .map(templateDependencyService::resolve)
                .orElseGet(() -> List.of(request.getTemplateFile()));

        var response = files.stream()
                .filter(StringUtils::isNotBlank)
                .map(templatePath -> templatePath
                        + Optional.ofNullable(request.getTemplateRef())
                                .map(s -> "@" + s)
                                .orElse(""))
                .flatMap(templatePath -> templatePath.contains("@")
                        ? Stream.of(templatePath)
                        : templateIndexService.index(Paths.get(templatePath)).getFiles().stream()
                                .map(MfgTemplateIndexData.TemplateFileDefinition::getTemplate)
                                .map(s -> templatePath + "@" + s))
                .findFirst()
                .map(template -> {
                    try {
                        // TODO: if no template ref is required; to avoid resolution service here, maybe send input
                        // stream of
                        // template

                        return templateService.eval(template, request.getContext());

                        // TODO: store to file system
                    } catch (Exception e) {
                        throw new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL, e));
                    }
                })
                .map(result -> MfgRunResponse.builder().result(result).build());

        return response.orElseThrow(() -> new MfgException(MifosError.of(MIFOS_TOOLS_MFG_TEMPLATE_ERROR_EVAL)));
    }
}
