///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.usecase.standard.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mifos.tools.mfg.core.model.MfgSaveRequest;
import org.mifos.tools.mfg.core.model.MfgSaveResponse;
import org.mifos.tools.mfg.core.service.MfgTemplateService;
import org.mifos.tools.mfg.core.usecase.MfgSaveUsecase;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
final class StandardGeneratorSaveUsecase implements MfgSaveUsecase {
    private final MfgTemplateService templateService;

    @Override
    public MfgSaveResponse execute(MfgSaveRequest request) {
        // TODO: implement this!
        return MfgSaveResponse.builder().build();
    }
}
