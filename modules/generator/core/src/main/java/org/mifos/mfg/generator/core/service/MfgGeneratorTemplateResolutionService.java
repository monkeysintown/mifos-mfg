/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.mfg.generator.core.service;

import java.util.List;

public interface MfgGeneratorTemplateResolutionService {
    List<String> resolve(String spec);
}
