/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.mfg.generator.core;

import static org.mifos.mfg.common.core.MfgConstants.MIFOS_MFG_ERROR_CODE_INCREMENT;
import static org.mifos.mfg.common.core.MfgConstants.MIFOS_MFG_ERROR_CODE_START;
import static org.mifos.mfg.common.core.MfgConstants.MIFOS_MFG_MESSAGE_BASE;
import static org.mifos.mfg.common.core.MfgConstants.MIFOS_MFG_MESSAGE_PREFIX;
import static org.mifos.mfg.common.core.MfgConstants.MIFOS_MFG_PACKAGE_BASE;
import static org.mifos.mfg.common.core.MfgConstants.MIFOS_MFG_PROPERTIES_PREFIX;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MfgGeneratorConstants {
    public static final String MIFOS_MFG_GENERATOR_MESSAGE_BASE = MIFOS_MFG_MESSAGE_BASE + "/generator/messages";
    public static final String MIFOS_MFG_GENERATOR_MESSAGE_PREFIX = MIFOS_MFG_MESSAGE_PREFIX + ".generator";
    public static final String MIFOS_MFG_GENERATOR_MESSAGE_ERROR_PREFIX = MIFOS_MFG_GENERATOR_MESSAGE_PREFIX + ".error";
    public static final String MIFOS_MFG_GENERATOR_MIME_TYPE_1_0 =
            "application/vnd.mifos.market.generator+json;charset=UTF-8;version=1.0";
    public static final String MIFOS_MFG_GENERATOR_ROUTE_BASE = "/market/generators";
    public static final String MIFOS_MFG_GENERATOR_TAG = "generators";
    public static final String MIFOS_MFG_GENERATOR_PACKAGE_BASE = MIFOS_MFG_PACKAGE_BASE + ".generator";
    public static final String MIFOS_MFG_GENERATOR_CORE_PACKAGE = MIFOS_MFG_GENERATOR_PACKAGE_BASE + ".core";
    public static final String MIFOS_MFG_GENERATOR_MAPPING_PACKAGE = MIFOS_MFG_GENERATOR_PACKAGE_BASE + ".mapping";
    public static final String MIFOS_MFG_GENERATOR_STARTER_PACKAGE = MIFOS_MFG_GENERATOR_PACKAGE_BASE + ".starter";
    public static final String MIFOS_MFG_GENERATOR_SERVICE_PACKAGE_BASE = MIFOS_MFG_GENERATOR_PACKAGE_BASE + ".service";
    public static final String MIFOS_MFG_GENERATOR_USECASE_PACKAGE_BASE = MIFOS_MFG_GENERATOR_PACKAGE_BASE + ".usecase";
    public static final String MIFOS_MFG_GENERATOR_PROPERTIES_PREFIX = MIFOS_MFG_PROPERTIES_PREFIX + ".generator";
    public static final String MIFOS_MFG_GENERATOR_PROPERTIES_ENABLED =
            MIFOS_MFG_GENERATOR_PROPERTIES_PREFIX + ".enabled";
    public static final int MIFOS_MFG_GENERATOR_ERROR_CODE_START = MIFOS_MFG_ERROR_CODE_START + 100;
    public static final int MIFOS_MFG_GENERATOR_ERROR_CODE_INCREMENT = MIFOS_MFG_ERROR_CODE_INCREMENT;
}
