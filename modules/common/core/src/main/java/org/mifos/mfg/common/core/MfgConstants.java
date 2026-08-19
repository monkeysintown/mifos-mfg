/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.mfg.common.core;

import static org.mifos.commons.boot.core.MifosCommonsBootConstants.MIFOS_COMMONS_BOOT_ERROR_CODE_CUSTOM_INCREMENT;
import static org.mifos.commons.boot.core.MifosCommonsBootConstants.MIFOS_COMMONS_BOOT_ERROR_CODE_CUSTOM_START;
import static org.mifos.commons.boot.core.MifosConstants.MIFOS_MESSAGE_BASE;
import static org.mifos.commons.boot.core.MifosConstants.MIFOS_PACKAGE_BASE;
import static org.mifos.commons.boot.core.MifosConstants.MIFOS_PROPERTIES_PREFIX;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MfgConstants {
    public static final String MIFOS_MFG_MESSAGE_BASE = MIFOS_MESSAGE_BASE + "/mfg";
    public static final String MIFOS_MFG_MESSAGE_PREFIX = MIFOS_PACKAGE_BASE + ".mfg";
    public static final String MIFOS_MFG_PACKAGE_BASE = MIFOS_PACKAGE_BASE + ".mfg";
    public static final String MIFOS_MFG_PROPERTIES_PREFIX = MIFOS_PROPERTIES_PREFIX + ".mfg";
    public static final int MIFOS_MFG_ERROR_CODE_START = MIFOS_COMMONS_BOOT_ERROR_CODE_CUSTOM_START + 10_000;
    public static final int MIFOS_MFG_ERROR_CODE_INCREMENT = MIFOS_COMMONS_BOOT_ERROR_CODE_CUSTOM_INCREMENT;
}
