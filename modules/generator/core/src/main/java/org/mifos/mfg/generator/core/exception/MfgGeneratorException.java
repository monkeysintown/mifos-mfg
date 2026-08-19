/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.mfg.generator.core.exception;

import static org.mifos.mfg.generator.core.MfgGeneratorConstants.MIFOS_MFG_GENERATOR_ERROR_CODE_INCREMENT;
import static org.mifos.mfg.generator.core.MfgGeneratorConstants.MIFOS_MFG_GENERATOR_ERROR_CODE_START;
import static org.mifos.mfg.generator.core.MfgGeneratorConstants.MIFOS_MFG_GENERATOR_MESSAGE_ERROR_PREFIX;

import java.io.Serial;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.mifos.commons.boot.core.exception.MifosBaseException;
import org.mifos.commons.boot.core.model.MifosError;
import org.mifos.commons.boot.core.model.MifosErrorCode;

public class MfgGeneratorException extends MifosBaseException {
    @Serial
    private static final long serialVersionUID = 1L;

    public MfgGeneratorException(MifosError error) {
        super(error);
    }

    @Getter
    @RequiredArgsConstructor
    public enum MifosBillingErrorCode implements MifosErrorCode {
        MIFOS_MARKET_CONTENT_ERROR_UNKNOWN(
                MIFOS_MFG_GENERATOR_ERROR_CODE_START, MIFOS_MFG_GENERATOR_MESSAGE_ERROR_PREFIX + ".unknown"),
        MIFOS_MARKET_CONTENT_ERROR_TEMPLATE_NOT_FOUND(
                MIFOS_MFG_GENERATOR_ERROR_CODE_START + MIFOS_MFG_GENERATOR_ERROR_CODE_INCREMENT,
                MIFOS_MFG_GENERATOR_MESSAGE_ERROR_PREFIX + ".template-not-found"),
        ;

        private final int value;
        private final String key;

        @Override
        public String getName() {
            return name();
        }
    }
}
