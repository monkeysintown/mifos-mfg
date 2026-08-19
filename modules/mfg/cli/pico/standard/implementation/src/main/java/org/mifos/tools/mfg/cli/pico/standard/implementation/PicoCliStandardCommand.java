///
/// This Source Code Form is subject to the terms of the Mozilla Public
/// License, v. 2.0. If a copy of the MPL was not distributed with this
/// file, You can obtain one at http://mozilla.org/MPL/2.0/.
///
package org.mifos.tools.mfg.cli.pico.standard.implementation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mifos.commons.boot.cli.pico.command.MifosCliPicoCommand;
import org.mifos.tools.mfg.core.model.MfgRunRequest;
import org.mifos.tools.mfg.core.usecase.MfgRunUsecase;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.shell.jline3.PicocliCommands;

@Slf4j
@RequiredArgsConstructor
@Component
@Command(
        name = "",
        description = "Test",
        footer = """

@|bold,yellow,faint ------------------------------- |@
@|bold,yellow ° Mifos Initiative - (c) 2026 ° |@""",
        subcommands = {CommandLine.HelpCommand.class, PicocliCommands.ClearScreen.class})
public final class PicoCliStandardCommand implements MifosCliPicoCommand {
    private final MfgRunUsecase runUsecase;

    @Option(
            names = {"-d", "--dependency"},
            description = "Dependency")
    private String dependency;

    @Option(
            names = {"-f", "--file"},
            description = "File")
    private String file;

    @Option(
            names = {"-t", "--template"},
            description = "Template")
    private String template;

    @Option(
            names = {"-P", "--params"},
            mapFallbackValue = Option.NULL_VALUE)
    Map<String, Optional<String>> params;

    @Override
    @SuppressWarnings("java:S106")
    public void run() {
        var response = runUsecase.execute(MfgRunRequest.builder()
                .templateDependency(dependency)
                .templateFile(file)
                .templateRef(template)
                .context(params.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().orElse(""),
                                (existing, _) -> existing,
                                LinkedHashMap::new)))
                .build());

        System.out.println(response.getResult());

        var str = CommandLine.Help.Ansi.AUTO.string("@|bold,green,underline Hello, colored world!|@");
        System.out.println(str);
    }
}
