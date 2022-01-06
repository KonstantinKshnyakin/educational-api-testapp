/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;
import org.apiguardian.api.API;
import org.junit.platform.commons.JUnitException;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.console.options.CommandLineOptions;
import org.junit.platform.console.options.Details;
import org.junit.platform.console.options.Theme;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import ru.kshnykin.kg.qa.education.api.testapp.config.Configuration;
import ru.kshnykin.kg.qa.education.api.testapp.junit.console.launcher.commands.ICommand;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.apiguardian.api.API.Status.MAINTAINED;

/**
 * The {@code ConsoleLauncher} is a stand-alone application for launching the
 * JUnit Platform from the console.
 *
 * @since 1.0
 */
@API(status = MAINTAINED, since = "1.0")
public class ConsoleLauncher {

    private final JCommander jCommander;
    private final MainOptions mainOptions;
    private final ICommand[] commands;
    private final PrintWriter out;
    private final PrintWriter err;

    public ConsoleLauncher(MainOptions mainOptions, ICommand... commands) {
        this.jCommander = new JCommander();
        this.mainOptions = mainOptions;
        this.commands = commands;
        this.out = new PrintWriter(System.out);
        this.err = new PrintWriter(System.err);

        initJCommander();
    }

    private void initJCommander() {
        jCommander.setProgramName("Test App");
        jCommander.addObject(mainOptions);
        for (ICommand command : commands) {
            jCommander.addCommand(command);
        }
    }

    public void run(String... args) {
        try {
            jCommander.parse(args);
        } catch (Exception e) {
            jCommander.usage();
            exit(1, "Parse configuration error", e);
        }
        if (mainOptions.isHelp()) {
            jCommander.usage();
            exit(0);
        }
        ICommand command = findCommand();
        if (command == null) {
            jCommander.usage();
            exit(1, "One of the commands must be specified");
        }
        final CommandLineOptions commandLineOptions = getCommandLineOptions();
        if (command != null && command.getTagExpression() != null && !command.getTagExpression().isBlank()) {
            String tagExpression = command.getTagExpression();
            commandLineOptions.setIncludedTagExpressions(Collections.singletonList(tagExpression));
            out.println("Tag expression: " + tagExpression);
        }
        Configuration.init();
        Configuration.setEnvironment(mainOptions.getEnvironment());
        int exitCode = execute(commandLineOptions).getExitCode();
        exit(exitCode);
    }

    private ConsoleLauncherExecutionResult execute(CommandLineOptions options) {
        try {
            return executeTests(options, out);
        } catch (JUnitException ex) {
            err.println(ex.getMessage());
            err.println();
            jCommander.usage();
            return ConsoleLauncherExecutionResult.failed();
        } finally {
            out.flush();
            err.flush();
        }
    }

    private ConsoleLauncherExecutionResult executeTests(CommandLineOptions options, PrintWriter out) {
        try {
            TestExecutionSummary testExecutionSummary = new ConsoleTestExecutor(options).execute(out);
            return ConsoleLauncherExecutionResult.forSummary(testExecutionSummary, options);
        } catch (Exception exception) {
            exception.printStackTrace(err);
            err.println();
            jCommander.usage();
            return ConsoleLauncherExecutionResult.failed();
        }
    }

    private CommandLineOptions getCommandLineOptions() {
        final CommandLineOptions clo = new CommandLineOptions();
        clo.setScanClasspath(true);
        clo.setDisplayHelp(mainOptions.isHelp());
        clo.setAnsiColorOutputDisabled(true);
        clo.setDetails(Details.VERBOSE);
        clo.setTheme(Theme.valueOf(Charset.defaultCharset()));
        clo.setIncludedEngines(Collections.singletonList("junit-jupiter"));
        clo.setIncludedClassNamePatterns(Collections.singletonList(ClassNameFilter.STANDARD_INCLUDE_PATTERN));
        clo.setFailIfNoTests(false);
        clo.setIncludedPackages(Collections.singletonList("ru.kshnykin.kg.qa.education.api.testapp.tests"));
        String classpath = System.getProperty("java.class.path");
        List<Path> paths = Arrays.stream(classpath.split(";"))
                .map(str -> new File(str).toPath())
                .toList();
        clo.setAdditionalClasspathEntries(paths);
        return clo;
    }

    private ICommand findCommand() {
        String parsedCommandName = jCommander.getParsedCommand();
        for (ICommand command : commands) {
            Optional<Parameters> annotationOpt = AnnotationUtils.findAnnotation(command.getClass(), Parameters.class);
            if (annotationOpt.isPresent()) {
                Parameters annotation = annotationOpt.get();
                if (Arrays.asList(annotation.commandNames()).contains(parsedCommandName)) {
                    return command;
                }
            }
        }
        return null;
    }

    public void exit(int status) {
        exit(status, null, null);
    }

    public void exit(int status, String message) {
        exit(status, message, null);
    }

    public void exit(int status, String message, Exception e) {
        if (message != null || e != null) {
            if (status == 0) {
                if (message != null) {
                    out.println(message);
                }
            } else {
                if (e != null) {
                    String exMsg = e.getMessage();
                    if (message != null) {
                        message += "\n" + (exMsg == null ? e.getClass().getSimpleName() : exMsg);
                    } else {
                        message = e.getClass().getSimpleName() + ": " + (exMsg == null ? "" : exMsg);
                    }
                }
                if (e != null) {
                    err.println(message);
                    err.println(e);
                } else {
                    err.println(message);
                }
            }
        }
        System.exit(status);
    }

}