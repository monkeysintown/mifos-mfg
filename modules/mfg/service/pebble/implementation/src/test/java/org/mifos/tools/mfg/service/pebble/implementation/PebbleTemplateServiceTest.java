///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.service.pebble.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest(classes = {TestConfiguration.class})
final class PebbleTemplateServiceTest {
    private final PebbleTemplateService templateService;

    @Test
    void eval() {
        var result =
                templateService.eval("./src/test/resources/templates.zip@content/Foo.java", Map.of("className", "Baz"));

        assertNotNull(result);

        log.error("Result: {}", result);
    }
}
