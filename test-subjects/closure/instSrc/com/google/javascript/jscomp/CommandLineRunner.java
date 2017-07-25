/*
 * Copyright 2009 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.common.io.LimitInputStream;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;
import org.kohsuke.args4j.spi.StringOptionHandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * CommandLineRunner translates flags into Java API calls on the Compiler.
 *
 * This class may be extended and used to create other Java classes
 * that behave the same as running the Compiler from the command line. If you
 * want to run the compiler in-process in Java, you should look at this class
 * for hints on what API calls to make, but you should not use this class
 * directly.
 *
 * Example:
 * <pre>
 * class MyCommandLineRunner extends CommandLineRunner {
 *   MyCommandLineRunner(String[] args) {
 *     super(args);
 *   }
 *
 *   {@code @Override} protected CompilerOptions createOptions() {
 *     CompilerOptions options = super.createOptions();
 *     addMyCrazyCompilerPassThatOutputsAnExtraFile(options);
 *     return options;
 *   }
 *
 *   public static void main(String[] args) {
 *     MyCommandLineRunner runner = new MyCommandLineRunner(args);
 *     if (runner.shouldRunCompiler()) {
 *       runner.run();
 *     } else {
 *       System.exit(-1);
 *     }
 *   }
 * }
 * </pre>
 *
 * This class is totally not thread-safe.
 *
 * @author bolinfest@google.com (Michael Bolin)
 */
public class CommandLineRunner extends
    AbstractCommandLineRunner<Compiler, CompilerOptions> {
  static {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.ping();
  }

  // I don't really care about unchecked warnings in this class.
  @SuppressWarnings("unchecked")
  private static class Flags {
    private static final WarningGuardSpec warningGuardSpec =
        new WarningGuardSpec();

    @Option(name = "--help",
        handler = BooleanOptionHandler.class,
        usage = "Displays this message")
    private boolean display_help = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[1]++;
  }

    @Option(name = "--print_tree",
        handler = BooleanOptionHandler.class,
        usage = "Prints out the parse tree and exits")
    private boolean print_tree = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[2]++;
  }

    @Option(name = "--print_ast",
        handler = BooleanOptionHandler.class,
        usage = "Prints a dot file describing the internal abstract syntax"
        + " tree and exits")
    private boolean print_ast = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[3]++;
  }

    @Option(name = "--print_pass_graph",
        handler = BooleanOptionHandler.class,
        usage = "Prints a dot file describing the passes that will get run"
        + " and exits")
    private boolean print_pass_graph = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[4]++;
  }

    // Turn on (very slow) extra sanity checks for use when modifying the
    // compiler.
    @Option(name = "--jscomp_dev_mode",
        // hidden, no usage
        aliases = {"--dev_mode"})
    private CompilerOptions.DevMode jscomp_dev_mode =
        CompilerOptions.DevMode.OFF;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[5]++;
  }

    @Option(name = "--logging_level",
        usage = "The logging level (standard java.util.logging.Level"
        + " values) for Compiler progress. Does not control errors or"
        + " warnings for the JavaScript code under compilation")
    private String logging_level = Level.WARNING.getName();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[6]++;
  }

    @Option(name = "--externs",
        usage = "The file containing JavaScript externs. You may specify"
        + " multiple")
    private List<String> externs = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[7]++;
  }

    @Option(name = "--js",
        usage = "The JavaScript filename. You may specify multiple")
    private List<String> js = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[8]++;
  }

    @Option(name = "--js_output_file",
        usage = "Primary output filename. If not specified, output is " +
        "written to stdout")
    private String js_output_file = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[9]++;
  }

    @Option(name = "--module",
        usage = "A JavaScript module specification. The format is "
        + "<name>:<num-js-files>[:[<dep>,...][:]]]. Module names must be "
        + "unique. Each dep is the name of a module that this module "
        + "depends on. Modules must be listed in dependency order, and JS "
        + "source files must be listed in the corresponding order. Where "
        + "--module flags occur in relation to --js flags is unimportant. "
        + "Provide the value 'auto' to trigger module creation from CommonJS"
        + "modules.")
    private List<String> module = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[10]++;
  }

    @Option(name = "--variable_map_input_file",
        usage = "File containing the serialized version of the variable "
        + "renaming map produced by a previous compilation")
    private String variable_map_input_file = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[11]++;
  }

    @Option(name = "--property_map_input_file",
        usage = "File containing the serialized version of the property "
        + "renaming map produced by a previous compilation")
    private String property_map_input_file = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[12]++;
  }

    @Option(name = "--variable_map_output_file",
        usage = "File where the serialized version of the variable "
        + "renaming map produced should be saved")
    private String variable_map_output_file = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[13]++;
  }

    @Option(name = "--create_name_map_files",
        handler = BooleanOptionHandler.class,
        usage = "If true, variable renaming and property renaming map "
        + "files will be produced as {binary name}_vars_map.out and "
        + "{binary name}_props_map.out. Note that this flag cannot be used "
        + "in conjunction with either variable_map_output_file or "
        + "property_map_output_file")
    private boolean create_name_map_files = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[14]++;
  }

    @Option(name = "--property_map_output_file",
        usage = "File where the serialized version of the property "
        + "renaming map produced should be saved")
    private String property_map_output_file = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[15]++;
  }

    @Option(name = "--third_party",
        handler = BooleanOptionHandler.class,
        usage = "Check source validity but do not enforce Closure style "
        + "rules and conventions")
    private boolean third_party = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[16]++;
  }


    @Option(name = "--summary_detail_level",
        usage = "Controls how detailed the compilation summary is. Values:"
        + " 0 (never print summary), 1 (print summary only if there are "
        + "errors or warnings), 2 (print summary if the 'checkTypes' "
        + "diagnostic  group is enabled, see --jscomp_warning), "
        + "3 (always print summary). The default level is 1")
    private int summary_detail_level = 1;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[17]++;
  }

    @Option(name = "--output_wrapper",
        usage = "Interpolate output into this string at the place denoted"
        + " by the marker token %output%. Use marker token %output|jsstring%"
        + " to do js string escaping on the output.")
    private String output_wrapper = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[18]++;
  }

    @Option(name = "--module_wrapper",
        usage = "An output wrapper for a JavaScript module (optional). "
        + "The format is <name>:<wrapper>. The module name must correspond "
        + "with a module specified using --module. The wrapper must "
        + "contain %s as the code placeholder. The %basename% placeholder can "
        + "also be used to substitute the base name of the module output file.")
    private List<String> module_wrapper = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[19]++;
  }

    @Option(name = "--module_output_path_prefix",
        usage = "Prefix for filenames of compiled JS modules. "
        + "<module-name>.js will be appended to this prefix. Directories "
        + "will be created as needed. Use with --module")
    private String module_output_path_prefix = "./";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[20]++;
  }

    @Option(name = "--create_source_map",
        usage = "If specified, a source map file mapping the generated " +
        "source files back to the original source file will be " +
        "output to the specified path. The %outname% placeholder will " +
        "expand to the name of the output file that the source map " +
        "corresponds to.")
    private String create_source_map = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[21]++;
  }

    @Option(name = "--source_map_format",
        usage = "The source map format to produce. " +
        "Options: V1, V2, V3, DEFAULT. DEFAULT produces V2.")
    private SourceMap.Format source_map_format = SourceMap.Format.DEFAULT;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[22]++;
  }

    // Used to define the flag, values are stored by the handler.
    @SuppressWarnings("unused")
    @Option(name = "--jscomp_error",
        handler = WarningGuardErrorOptionHandler.class,
        usage = "Make the named class of warnings an error. Options:" +
        DiagnosticGroups.DIAGNOSTIC_GROUP_NAMES)
    private List<String> jscomp_error = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[23]++;
  }

    // Used to define the flag, values are stored by the handler.
    @SuppressWarnings("unused")
    @Option(name = "--jscomp_warning",
        handler = WarningGuardWarningOptionHandler.class,
        usage = "Make the named class of warnings a normal warning. " +
        "Options:" + DiagnosticGroups.DIAGNOSTIC_GROUP_NAMES)
    private List<String> jscomp_warning = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[24]++;
  }

    // Used to define the flag, values are stored by the handler.
    @SuppressWarnings("unused")
    @Option(name = "--jscomp_off",
        handler = WarningGuardOffOptionHandler.class,
        usage = "Turn off the named class of warnings. Options:" +
        DiagnosticGroups.DIAGNOSTIC_GROUP_NAMES)
    private List<String> jscomp_off = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[25]++;
  }

    @Option(name = "--define",
        aliases = {"--D", "-D"},
        usage = "Override the value of a variable annotated @define. " +
        "The format is <name>[=<val>], where <name> is the name of a @define " +
        "variable and <val> is a boolean, number, or a single-quoted string " +
        "that contains no single quotes. If [=<val>] is omitted, " +
        "the variable is marked true")
    private List<String> define = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[26]++;
  }

    @Option(name = "--charset",
        usage = "Input and output charset for all files. By default, we " +
                "accept UTF-8 as input and output US_ASCII")
    private String charset = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[27]++;
  }

    @Option(name = "--compilation_level",
        usage = "Specifies the compilation level to use. Options: " +
        "WHITESPACE_ONLY, SIMPLE_OPTIMIZATIONS, ADVANCED_OPTIMIZATIONS")
    private CompilationLevel compilation_level =
        CompilationLevel.SIMPLE_OPTIMIZATIONS;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[28]++;
  }

    @Option(name = "--use_types_for_optimization",
        usage = "Experimental: perform additional optimizations " +
        "based on available information.  Inaccurate type annotations " +
        "may result in incorrect results.")
    private boolean use_types_for_optimization = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[29]++;
  }

    @Option(name = "--warning_level",
        usage = "Specifies the warning level to use. Options: " +
        "QUIET, DEFAULT, VERBOSE")
    private WarningLevel warning_level = WarningLevel.DEFAULT;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[30]++;
  }

    @Option(name = "--use_only_custom_externs",
        handler = BooleanOptionHandler.class,
        usage = "Specifies whether the default externs should be excluded")
    private boolean use_only_custom_externs = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[31]++;
  }

    @Option(name = "--debug",
        handler = BooleanOptionHandler.class,
        usage = "Enable debugging options")
    private boolean debug = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[32]++;
  }

    @Option(name = "--generate_exports",
        handler = BooleanOptionHandler.class,
        usage = "Generates export code for those marked with @export")
    private boolean generate_exports = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[33]++;
  }

    @Option(name = "--formatting",
        usage = "Specifies which formatting options, if any, should be "
        + "applied to the output JS. Options: "
        + "PRETTY_PRINT, PRINT_INPUT_DELIMITER, SINGLE_QUOTES")
    private List<FormattingOption> formatting = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[34]++;
  }

    @Option(name = "--process_common_js_modules",
        usage = "Process CommonJS modules to a concatenable form.")
    private boolean process_common_js_modules = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[35]++;
  }

    @Option(name = "--common_js_module_path_prefix",
        usage = "Path prefix to be removed from CommonJS module names.")
    private String common_js_path_prefix =
        ProcessCommonJSModules.DEFAULT_FILENAME_PREFIX;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[36]++;
  }

    @Option(name = "--common_js_entry_module",
        usage = "Root of your common JS dependency hierarchy. "+
            "Your main script.")
    private String common_js_entry_module;

    @Option(name = "--transform_amd_modules",
        usage = "Transform AMD to CommonJS modules.")
    private boolean transform_amd_modules = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[37]++;
  }

    @Option(name = "--process_closure_primitives",
        handler = BooleanOptionHandler.class,
        usage = "Processes built-ins from the Closure library, such as "
        + "goog.require(), goog.provide(), and goog.exportSymbol()")
    private boolean process_closure_primitives = true;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[38]++;
  }

    @Option(name = "--manage_closure_dependencies",
        handler = BooleanOptionHandler.class,
        usage = "Automatically sort dependencies so that a file that "
        + "goog.provides symbol X will always come before a file that "
        + "goog.requires symbol X. If an input provides symbols, and "
        + "those symbols are never required, then that input will not "
        + "be included in the compilation.")
    private boolean manage_closure_dependencies = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[39]++;
  }

    @Option(name = "--only_closure_dependencies",
        handler = BooleanOptionHandler.class,
        usage = "Only include files in the transitive dependency of the "
        + "entry points (specified by closure_entry_point). Files that do "
        + "not provide dependencies will be removed. This supersedes"
        + "manage_closure_dependencies")
    private boolean only_closure_dependencies = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[40]++;
  }

    @Option(name = "--closure_entry_point",
        usage = "Entry points to the program. Must be goog.provide'd "
        + "symbols. Any goog.provide'd symbols that are not a transitive "
        + "dependency of the entry points will be removed. Files without "
        + "goog.provides, and their dependencies, will always be left in. "
        + "If any entry points are specified, then the "
        + "manage_closure_dependencies option will be set to true and "
        + "all files will be sorted in dependency order.")
    private List<String> closure_entry_point = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[41]++;
  }

    @Option(name = "--process_jquery_primitives",
        handler = BooleanOptionHandler.class,
        usage = "Processes built-ins from the Jquery library, such as "
        + "jQuery.fn and jQuery.extend()")
    private boolean process_jquery_primitives = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[42]++;
  }

    @Option(name = "--angular_pass",
        handler = BooleanOptionHandler.class,
        usage = "Generate $inject properties for AngularJS for functions "
        + "annotated with @ngInject")
    private boolean angular_pass = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[43]++;
  }

    @Option(name = "--output_manifest",
        usage = "Prints out a list of all the files in the compilation. "
        + "If --manage_closure_dependencies is on, this will not include "
        + "files that got dropped because they were not required. "
        + "The %outname% placeholder expands to the JS output file. "
        + "If you're using modularization, using %outname% will create "
        + "a manifest for each module.")
    private String output_manifest = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[44]++;
  }

    @Option(name = "--output_module_dependencies",
        usage = "Prints out a JSON file of dependencies between modules.")
    private String output_module_dependencies = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[45]++;
  }

    @Option(name = "--accept_const_keyword",
        usage = "Allows usage of const keyword.")
    private boolean accept_const_keyword = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[46]++;
  }

    @Option(name = "--language_in",
        usage = "Sets what language spec that input sources conform. "
        + "Options: ECMASCRIPT3 (default), ECMASCRIPT5, ECMASCRIPT5_STRICT")
    private String language_in = "ECMASCRIPT3";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[47]++;
  }

    @Option(name = "--version",
        handler = BooleanOptionHandler.class,
        usage = "Prints the compiler version to stderr.")
    private boolean version = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[48]++;
  }

    @Option(name = "--translations_file",
        usage = "Source of translated messages. Currently only supports XTB.")
    private String translationsFile = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[49]++;
  }

    @Option(name = "--translations_project",
        usage = "Scopes all translations to the specified project." +
        "When specified, we will use different message ids so that messages " +
        "in different projects can have different translations.")
    private String translationsProject = null;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[50]++;
  }

    @Option(name = "--flagfile",
        usage = "A file containing additional command-line options.")
    private String flag_file = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[51]++;
  }

    @Option(name = "--warnings_whitelist_file",
        usage = "A file containing warnings to suppress. Each line should be " +
            "of the form\n" +
            "<file-name>:<line-number>?  <warning-description>")
    private String warnings_whitelist_file = "";
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[52]++;
  }

    @Option(name = "--extra_annotation_name",
        usage = "A whitelist of tag names in JSDoc. You may specify multiple")
    private List<String> extra_annotation_name = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[53]++;
  }

    @Argument
    private List<String> arguments = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[54]++;
  }

    /**
     * Users may specify JS inputs via the legacy {@code --js} option, as well
     * as via additional arguments to the Closure Compiler. For example, it is
     * convenient to leverage the additional arguments feature when using the
     * Closure Compiler in combination with {@code find} and {@code xargs}:
     * <pre>
     * find MY_JS_SRC_DIR -name '*.js' \
     *     | xargs java -jar compiler.jar --manage_closure_dependencies
     * </pre>
     * The {@code find} command will produce a list of '*.js' source files in
     * the {@code MY_JS_SRC_DIR} directory while {@code xargs} will convert them
     * to a single, space-delimited set of arguments that are appended to the
     * {@code java} command to run the Compiler.
     * <p>
     * Note that it is important to use the
     * {@code --manage_closure_dependencies} option in this case because the
     * order produced by {@code find} is unlikely to be sorted correctly with
     * respect to {@code goog.provide()} and {@code goog.requires()}.
     */
    List<String> getJsFiles() {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[55]++;
      List<String> allJsInputs = Lists.newArrayListWithCapacity(
          js.size() + arguments.size());
      allJsInputs.addAll(js);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[56]++;
      allJsInputs.addAll(arguments);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[57]++;
      return allJsInputs;
    }

    // Our own option parser to be backwards-compatible.
    // It needs to be public because of the crazy reflection that args4j does.
    public static class BooleanOptionHandler extends OptionHandler<Boolean> {
      private static final Set<String> TRUES =
          Sets.newHashSet("true", "on", "yes", "1");
      private static final Set<String> FALSES =
          Sets.newHashSet("false", "off", "no", "0");

      public BooleanOptionHandler(
          CmdLineParser parser, OptionDef option,
          Setter<? super Boolean> setter) {
        super(parser, option, setter);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[58]++;
      }

      @Override
      public int parseArguments(Parameters params) throws CmdLineException {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[59]++;
        String param = null;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[60]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
          param = params.getParameter(0);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[61]++;
        } catch (CmdLineException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[2]++;} finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[1]++;
}
  }
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[62]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((param == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[3]++;
          setter.addValue(true);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[63]++;
          return 0;

        } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[4]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[64]++;
          String lowerParam = param.toLowerCase();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[65]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((TRUES.contains(lowerParam)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[5]++;
            setter.addValue(true);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[66]++;

          } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[6]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[67]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((FALSES.contains(lowerParam)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[7]++;
            setter.addValue(false);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[68]++;

          } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[8]++;
            setter.addValue(true);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[69]++;
            return 0;
          }
}
          return 1;
        }
      }

      @Override
      public String getDefaultMetaVariable() {
        return null;
      }
    }

    // Our own parser for warning guards that preserves the original order
    // of the flags.
    public static class WarningGuardErrorOptionHandler
        extends StringOptionHandler {
      public WarningGuardErrorOptionHandler(
          CmdLineParser parser, OptionDef option,
          Setter<? super String> setter) {
        super(parser, option, new WarningGuardSetter(setter, CheckLevel.ERROR));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[70]++;
      }
    }

    public static class WarningGuardWarningOptionHandler
        extends StringOptionHandler {
      public WarningGuardWarningOptionHandler(
          CmdLineParser parser, OptionDef option,
          Setter<? super String> setter) {
        super(parser, option,
            new WarningGuardSetter(setter, CheckLevel.WARNING));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[71]++;
      }
    }

    public static class WarningGuardOffOptionHandler
        extends StringOptionHandler {
      public WarningGuardOffOptionHandler(
          CmdLineParser parser, OptionDef option,
          Setter<? super String> setter) {
        super(parser, option, new WarningGuardSetter(setter, CheckLevel.OFF));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[72]++;
      }
    }

    private static class WarningGuardSetter implements Setter<String> {
      private final Setter<? super String> proxy;
      private final CheckLevel level;

      private WarningGuardSetter(
          Setter<? super String> proxy, CheckLevel level) {
        this.proxy = proxy;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[73]++;
        this.level = level;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[74]++;
      }

      @Override public boolean isMultiValued() {
        return proxy.isMultiValued();
      }

      @Override public Class<String> getType() {
        return (Class<String>) proxy.getType();
      }

      @Override public void addValue(String value) throws CmdLineException {
        proxy.addValue(value);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[75]++;
        warningGuardSpec.add(level, value);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[76]++;
      }
    }
  }

  /**
   * Set of options that can be used with the --formatting flag.
   */
  private static enum FormattingOption {
    PRETTY_PRINT,
    PRINT_INPUT_DELIMITER,
    SINGLE_QUOTES
    ;

    private void applyToOptions(CompilerOptions options) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[77]++;
      switch (this) {
        case PRETTY_PRINT:
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[9]++;
          options.prettyPrint = true;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[78]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[79]++;
          break;
        case PRINT_INPUT_DELIMITER:
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[10]++;
          options.printInputDelimiter = true;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[80]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[81]++;
          break;
        case SINGLE_QUOTES:
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[11]++;
          options.setPreferSingleQuotes(true);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[82]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[83]++;
          break;
        default:
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[12]++;
          throw new RuntimeException("Unknown formatting option: " + this);
      }
    }
  }

  private final Flags flags = new Flags();
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[84]++;
  }

  private boolean isConfigValid = false;
  {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[85]++;
  }

  /**
   * Create a new command-line runner. You should only need to call
   * the constructor if you're extending this class. Otherwise, the main
   * method should instantiate it.
   */
  protected CommandLineRunner(String[] args) {
    super();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[86]++;
    initConfigFromFlags(args, System.err);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[87]++;
  }

  protected CommandLineRunner(String[] args, PrintStream out, PrintStream err) {
    super(out, err);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[88]++;
    initConfigFromFlags(args, err);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[89]++;
  }

  /**
   * Split strings into tokens delimited by whitespace, but treat quoted
   * strings as single tokens. Non-whitespace characters adjacent to quoted
   * strings will be returned as part of the token. For example, the string
   * {@code "--js='/home/my project/app.js'"} would be returned as a single
   * token.
   *
   * @param lines strings to tokenize
   * @return a list of tokens
   */
  private List<String> tokenizeKeepingQuotedStrings(List<String> lines) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[90]++;
    List<String> tokens = Lists.newArrayList();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[91]++;
    Pattern tokenPattern =
        Pattern.compile("(?:[^ \t\f\\x0B'\"]|(?:'[^']*'|\"[^\"]*\"))+");
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[92]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[1]++;



    for (String line : lines) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[1]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[2]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[3]++;
}
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[93]++;
      Matcher matcher = tokenPattern.matcher(line);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[94]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
      while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((matcher.find()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[4]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[5]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[6]++;
}
        tokens.add(matcher.group(0));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[95]++;
      }
    }
    return tokens;
  }

  private List<String> processArgs(String[] args) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[96]++;
    // Args4j has a different format that the old command-line parser.
    // So we use some voodoo to get the args into the format that args4j
    // expects.
    Pattern argPattern = Pattern.compile("(--[a-zA-Z_]+)=(.*)");
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[97]++;
    Pattern quotesPattern = Pattern.compile("^['\"](.*)['\"]$");
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[98]++;
    List<String> processedArgs = Lists.newArrayList();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[99]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[7]++;



    for (String arg : args) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[7]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[8]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[9]++;
}
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[100]++;
      Matcher matcher = argPattern.matcher(arg);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[101]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((matcher.matches()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[13]++;
        processedArgs.add(matcher.group(1));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[102]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[103]++;

        String value = matcher.group(2);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[104]++;
        Matcher quotesMatcher = quotesPattern.matcher(value);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[105]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((quotesMatcher.matches()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[15]++;
          processedArgs.add(quotesMatcher.group(1));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[106]++;

        } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[16]++;
          processedArgs.add(value);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[107]++;
        }

      } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[14]++;
        processedArgs.add(arg);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[108]++;
      }
    }

    return processedArgs;
  }

  private void processFlagFile(PrintStream err)
            throws CmdLineException, IOException {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[109]++;
    File flagFileInput = new File(flags.flag_file);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[110]++;
    List<String> argsInFile = tokenizeKeepingQuotedStrings(
        Files.readLines(flagFileInput, Charset.defaultCharset()));

    flags.flag_file = "";
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[111]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[112]++;
    List<String> processedFileArgs
        = processArgs(argsInFile.toArray(new String[] {}));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[113]++;
    CmdLineParser parserFileArgs = new CmdLineParser(flags);
    Flags.warningGuardSpec.clear();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[114]++;
    parserFileArgs.parseArgument(processedFileArgs.toArray(new String[] {}));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[115]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[116]++;
int CodeCoverConditionCoverageHelper_C7;

    // Currently we are not supporting this (prevent direct/indirect loops)
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((flags.flag_file.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[17]++;
      err.println("ERROR - Arguments in the file cannot contain "
          + "--flagfile option.");
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[117]++;
      isConfigValid = false;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[118]++;

    } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[18]++;}
  }

  private void initConfigFromFlags(String[] args, PrintStream err) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[119]++;

    List<String> processedArgs = processArgs(args);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[120]++;

    CmdLineParser parser = new CmdLineParser(flags);
    Flags.warningGuardSpec.clear();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[121]++;
    isConfigValid = true;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[122]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[123]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
    try {
CodeCoverTryBranchHelper_Try2 = true;
      parser.parseArgument(processedArgs.toArray(new String[] {}));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[124]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[125]++;
int CodeCoverConditionCoverageHelper_C8;
      // For contains --flagfile flag
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((flags.flag_file.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[20]++;
        processFlagFile(err);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[126]++;

      } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[21]++;}
    } catch (CmdLineException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[22]++;
      err.println(e.getMessage());
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[127]++;
      isConfigValid = false;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[128]++;
    } catch (IOException ioErr) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[23]++;
      err.println("ERROR - " + flags.flag_file + " read error.");
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[129]++;
      isConfigValid = false;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[130]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[19]++;
}
  }
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[131]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((flags.version) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[24]++;
      err.println(
          "Closure Compiler (http://code.google.com/closure/compiler)\n" +
          "Version: " + Compiler.getReleaseVersion() + "\n" +
          "Built on: " + Compiler.getReleaseDate());
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[132]++;
      err.flush();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[133]++;

    } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[25]++;}
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[134]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((flags.process_common_js_modules) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[26]++;
      flags.process_closure_primitives = true;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[135]++;
      flags.manage_closure_dependencies = true;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[136]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[137]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((flags.common_js_entry_module == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[28]++;
        err.println("Please specify --common_js_entry_module.");
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[138]++;
        err.flush();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[139]++;
        isConfigValid = false;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[140]++;

      } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[29]++;}
      flags.closure_entry_point = Lists.newArrayList(
          ProcessCommonJSModules.toModuleName(flags.common_js_entry_module));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[141]++;

    } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[27]++;}
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[142]++;
int CodeCoverConditionCoverageHelper_C12;

    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((isConfigValid) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((flags.display_help) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[30]++;
      isConfigValid = false;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[143]++;
      parser.printUsage(err);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[144]++;

    } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[31]++;
      CodingConvention conv;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[145]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((flags.third_party) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[32]++;
        conv = CodingConventions.getDefault();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[146]++;

      } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[33]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[147]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((flags.process_jquery_primitives) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[34]++;
        conv = new JqueryCodingConvention();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[148]++;

      } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[35]++;
        conv = new ClosureCodingConvention();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[149]++;
      }
}

      getCommandLineConfig()
          .setPrintTree(flags.print_tree)
          .setPrintAst(flags.print_ast)
          .setPrintPassGraph(flags.print_pass_graph)
          .setJscompDevMode(flags.jscomp_dev_mode)
          .setLoggingLevel(flags.logging_level)
          .setExterns(flags.externs)
          .setJs(flags.getJsFiles())
          .setJsOutputFile(flags.js_output_file)
          .setModule(flags.module)
          .setVariableMapInputFile(flags.variable_map_input_file)
          .setPropertyMapInputFile(flags.property_map_input_file)
          .setVariableMapOutputFile(flags.variable_map_output_file)
          .setCreateNameMapFiles(flags.create_name_map_files)
          .setPropertyMapOutputFile(flags.property_map_output_file)
          .setCodingConvention(conv)
          .setSummaryDetailLevel(flags.summary_detail_level)
          .setOutputWrapper(flags.output_wrapper)
          .setModuleWrapper(flags.module_wrapper)
          .setModuleOutputPathPrefix(flags.module_output_path_prefix)
          .setCreateSourceMap(flags.create_source_map)
          .setSourceMapFormat(flags.source_map_format)
          .setWarningGuardSpec(Flags.warningGuardSpec)
          .setDefine(flags.define)
          .setCharset(flags.charset)
          .setManageClosureDependencies(flags.manage_closure_dependencies)
          .setOnlyClosureDependencies(flags.only_closure_dependencies)
          .setClosureEntryPoints(flags.closure_entry_point)
          .setOutputManifest(ImmutableList.of(flags.output_manifest))
          .setOutputModuleDependencies(flags.output_module_dependencies)
          .setAcceptConstKeyword(flags.accept_const_keyword)
          .setLanguageIn(flags.language_in)
          .setProcessCommonJSModules(flags.process_common_js_modules)
          .setCommonJSModulePathPrefix(flags.common_js_path_prefix)
          .setTransformAMDToCJSModules(flags.transform_amd_modules)
          .setWarningsWhitelistFile(flags.warnings_whitelist_file);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[150]++;
    }
  }

  @Override
  protected CompilerOptions createOptions() {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[151]++;
    CompilerOptions options = new CompilerOptions();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[152]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((flags.process_jquery_primitives) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[36]++;
      options.setCodingConvention(new JqueryCodingConvention());
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[153]++;

    } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[37]++;
      options.setCodingConvention(new ClosureCodingConvention());
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[154]++;
    }

    options.setExtraAnnotationNames(flags.extra_annotation_name);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[155]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[156]++;

    CompilationLevel level = flags.compilation_level;
    level.setOptionsForCompilationLevel(options);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[157]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[158]++;
int CodeCoverConditionCoverageHelper_C16;

    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((flags.debug) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[38]++;
      level.setDebugOptionsForCompilationLevel(options);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[159]++;

    } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[39]++;}
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[160]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((flags.use_types_for_optimization) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[40]++;
      level.setTypeBasedOptimizationOptions(options);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[161]++;

    } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[41]++;}
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[162]++;
int CodeCoverConditionCoverageHelper_C18;

    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((flags.generate_exports) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[42]++;
      options.setGenerateExports(flags.generate_exports);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[163]++;

    } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[43]++;}
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[164]++;

    WarningLevel wLevel = flags.warning_level;
    wLevel.setOptionsForWarningLevel(options);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[165]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[166]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[10]++;


    for (FormattingOption formattingOption : flags.formatting) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[10]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[11]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[12]++;
}
      formattingOption.applyToOptions(options);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[167]++;
    }

    options.closurePass = flags.process_closure_primitives;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[168]++;

    options.jqueryPass = flags.process_jquery_primitives &&
        CompilationLevel.ADVANCED_OPTIMIZATIONS == level;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[169]++;

    options.angularPass = flags.angular_pass;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[170]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[171]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((flags.translationsFile.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[44]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[172]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
      try {
CodeCoverTryBranchHelper_Try3 = true;
        options.messageBundle = new XtbMessageBundle(
            new FileInputStream(flags.translationsFile),
            flags.translationsProject);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[173]++;
      } catch (IOException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[47]++;
        throw new RuntimeException("Reading XTB file", e);
      } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[46]++;
}
  }

    } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[45]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[174]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((CompilationLevel.ADVANCED_OPTIMIZATIONS == level) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[48]++;
      // In SIMPLE or WHITESPACE mode, if the user hasn't specified a
      // translations file, they might reasonably try to write their own
      // implementation of goog.getMsg that makes the substitution at
      // run-time.
      //
      // In ADVANCED mode, goog.getMsg is going to be renamed anyway,
      // so we might as well inline it.
      options.messageBundle = new EmptyMessageBundle();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[175]++;

    } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[49]++;}
}

    return options;
  }

  @Override
  protected Compiler createCompiler() {
    return new Compiler(getErrorPrintStream());
  }

  @Override
  protected List<SourceFile> createExterns() throws FlagUsageException,
      IOException {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[176]++;
    List<SourceFile> externs = super.createExterns();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[177]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((flags.use_only_custom_externs) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((isInTestMode()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[50]++;
      return externs;

    } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[51]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[178]++;
      List<SourceFile> defaultExterns = getDefaultExterns();
      defaultExterns.addAll(externs);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[179]++;
      return defaultExterns;
    }
  }

  // The externs expected in externs.zip, in sorted order.
  private static final List<String> DEFAULT_EXTERNS_NAMES = ImmutableList.of(
    // JS externs
    "es3.js",
    "es5.js",

    // Event APIs
    "w3c_event.js",
    "w3c_event3.js",
    "gecko_event.js",
    "ie_event.js",
    "webkit_event.js",

    // DOM apis
    "w3c_dom1.js",
    "w3c_dom2.js",
    "w3c_dom3.js",
    "gecko_dom.js",
    "ie_dom.js",
    "webkit_dom.js",

    // CSS apis
    "w3c_css.js",
    "gecko_css.js",
    "ie_css.js",
    "webkit_css.js",

    // Top-level namespaces
    "google.js",

    "deprecated.js",
    "fileapi.js",
    "flash.js",
    "gears_symbols.js",
    "gears_types.js",
    "gecko_xml.js",
    "html5.js",
    "ie_vml.js",
    "iphone.js",
    "webstorage.js",
    "w3c_anim_timing.js",
    "w3c_css3d.js",
    "w3c_elementtraversal.js",
    "w3c_geolocation.js",
    "w3c_indexeddb.js",
    "w3c_navigation_timing.js",
    "w3c_range.js",
    "w3c_selectors.js",
    "w3c_xml.js",
    "window.js",
    "webkit_notifications.js",
    "webgl.js");
  static {
    CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[180]++;
  }

  /**
   * @return a mutable list
   * @throws IOException
   */
  public static List<SourceFile> getDefaultExterns() throws IOException {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[181]++;
    InputStream input = CommandLineRunner.class.getResourceAsStream(
        "/externs.zip");
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[182]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((input == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[52]++;
      // In some environments, the externs.zip is relative to this class.
      input = CommandLineRunner.class.getResourceAsStream("externs.zip");
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[183]++;

    } else {
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[53]++;}
    Preconditions.checkNotNull(input);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[184]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[185]++;

    ZipInputStream zip = new ZipInputStream(input);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[186]++;
    Map<String, SourceFile> externsMap = Maps.newHashMap();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[187]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[13]++;


    for (ZipEntry entry = null;(entry = zip.getNextEntry()) != null; ) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[13]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[14]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[15]++;
}
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[188]++;
      BufferedInputStream entryStream = new BufferedInputStream(
          new LimitInputStream(zip, entry.getSize()));
      externsMap.put(entry.getName(),
          SourceFile.fromInputStream(
              // Give the files an odd prefix, so that they do not conflict
              // with the user's files.
              "externs.zip//" + entry.getName(),
              entryStream));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[189]++;
    }

    Preconditions.checkState(
        externsMap.keySet().equals(Sets.newHashSet(DEFAULT_EXTERNS_NAMES)),
        "Externs zip must match our hard-coded list of externs.");
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[190]++;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[191]++;

    // Order matters, so the resources must be added to the result list
    // in the expected order.
    List<SourceFile> externs = Lists.newArrayList();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[192]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[16]++;


    for (String key : DEFAULT_EXTERNS_NAMES) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[16]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[17]--;
  CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.loops[18]++;
}
      externs.add(externsMap.get(key));
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[193]++;
    }

    return externs;
  }

  /**
   * @return Whether the configuration is valid.
   */
  public boolean shouldRunCompiler() {
    return this.isConfigValid;
  }

  /**
   * Runs the Compiler. Exits cleanly in the event of an error.
   */
  public static void main(String[] args) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[194]++;
    CommandLineRunner runner = new CommandLineRunner(args);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[195]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((runner.shouldRunCompiler()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[54]++;
      runner.run();
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[196]++;

    } else {
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.branches[55]++;
      System.exit(-1);
CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29.statements[197]++;
    }
  }
}

class CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29 ());
  }
    public static long[] statements = new long[198];
    public static long[] branches = new long[56];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[25];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CommandLineRunner.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,0,1};
    for (int i = 1; i <= 24; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$b2bzl8owwhie6kdynofhh8phojq5sqhd29 () {
    super("com.google.javascript.jscomp.CommandLineRunner.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 197; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 55; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CommandLineRunner.java");
      for (int i = 1; i <= 197; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 55; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 24; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
        if (loops[i * 3 - 2] != 0L) {
          log.passCounter("L" + i + "-0", loops[i * 3 - 2]);
          loops[i * 3 - 2] = 0L;
        }
        if ( loops[i * 3 - 1] != 0L) {
          log.passCounter("L" + i + "-1", loops[i * 3 - 1]);
          loops[i * 3 - 1] = 0L;
        }
        if ( loops[i * 3] != 0L) {
          log.passCounter("L" + i + "-2", loops[i * 3]);
          loops[i * 3] = 0L;
        }
      }
  }
}

