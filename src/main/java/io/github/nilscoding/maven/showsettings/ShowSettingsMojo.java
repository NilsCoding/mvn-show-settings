package io.github.nilscoding.maven.showsettings;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Mojo to show Maven project property settings.
 * @author NilsCoding
 */
@Mojo(name = "show-settings")
public class ShowSettingsMojo extends AbstractMojo {

    /**
     * Maven project.
     */
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

//    /**
//     * Plugin descriptor.
//     */
//    @Parameter(defaultValue = "${plugin}", readonly = true, required = true)
//    private PluginDescriptor pluginDescriptor;
//
//    /**
//     * Maven Session.
//     */
//    @Parameter(defaultValue = "${session}", readonly = true, required = true)
//    private MavenSession session;
//
//    /**
//     * Plugin Manager.
//     */
//    @Component
//    private BuildPluginManager pluginManager;

    /**
     * Creates a new instance.
     */
    public ShowSettingsMojo() {
    }

    /**
     * Executes the Maven Mojo.
     * @throws MojoExecutionException Mojo execution exception
     * @throws MojoFailureException   Mojo failure exception
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Log log = getLog();
        log.info("ShowSettings Mojo at work...");

        if (this.project != null) {
            Properties p = this.project.getProperties();
            if ((p != null) && (p.isEmpty() == false)) {
                for (String propName : p.stringPropertyNames().stream().sorted().collect(Collectors.toList())) {
                    String propValue = p.getProperty(propName);
                    log.info("  '" + propName + "':'" + propValue + "'");
                }
            } else {
                log.info("No properties.");
            }
        } else {
            log.info("No project found.");
        }
    }

}
