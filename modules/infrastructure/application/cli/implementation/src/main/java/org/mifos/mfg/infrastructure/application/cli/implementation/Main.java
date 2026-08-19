/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifos.mfg.infrastructure.application.cli.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;

@Slf4j
@Modulith(
        systemName = "Mifos Fast Generator",
        sharedModules = {"common"},
        useFullyQualifiedModuleNames = false,
        additionalPackages = {})
@SpringBootApplication
public class Main {
    static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
