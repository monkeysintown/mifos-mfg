///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.yml.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest(classes = {TestConfiguration.class})
final class YmlTemplateIndexServiceTest {
    private final YmlTemplateIndexService templateIndexService;

    @Test
    void parse() {
        var idx = templateIndexService.parse(
                YmlTemplateIndexServiceTest.class.getClassLoader().getResourceAsStream("idx.yml"));

        assertNotNull(idx);

        templateIndexService.validate(idx);

        var databaseType = idx.getParameters().stream()
                .filter(param -> param.getName().equals("databaseType"))
                .findFirst();
        assertTrue(databaseType.isPresent());
        assertEquals(3, databaseType.get().getOptions().size());

        var enableSecurity = idx.getParameters().stream()
                .filter(param -> param.getName().equals("enableSecurity"))
                .findFirst();
        assertTrue(enableSecurity.isPresent());
        assertFalse(enableSecurity.get().getDefaultValueAsBool());

        log.warn("Template index: {}", idx);
    }
}
