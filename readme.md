# mvn-show-settings

**mvn-show-settings** is a custom Maven Mojo to write the effective project properties to the logging output.

## pre-requisits
As **mvn-show-settings** is currently not available on Maven Central, you need to make sure that it is available to whatever system you're going to use it in your build.

### local builds
You need to clone the repository of **mvn-show-settings** and then install it locally by `mvn clean install`.

### server-side builds (with custom Maven hosting)
First, you need to clone and locally build **mvn-show-settings**, then you need to deploy it to your custom Maven hosting (e.g. Nexus).

While you might have configured your custom Maven hosting to be accessible via your Maven configuration, this might not include a custom Maven hosting location for plugins. So, make sure that `pluginRepositories` in your Maven configuration file includes your custom Maven hosting.

## usage

To execute the Mojo in your build process, it needs to be configured in the `build`/`plugins` section, like this:
```xml
<plugin>
    <groupId>io.github.nilscoding.maven</groupId>
    <artifactId>mvn-show-settings</artifactId>
    <version>1.0.0</version>
    <executions>
        <execution>
            <phase>generate-sources</phase>
            <goals>
                <goal>show-settings</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Make sure that you reference the correct version.

Also, you might want to check the [default Maven lifecycle documentation](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#default-lifecycle) to find the appropriate phase in which you want to execute the Mojo.

## output

All Mojo output will be written to Maven build logging using `info` level.

The output will look like this, depending on the actual settings:
```
[INFO] ShowSettings Mojo at work...
[INFO]   'project.build.sourceEncoding':'UTF-8'
```

The first line will always look like this, then in each following line one property will be logged as `'key':'value'` with an indent of two spaces.
If for some reason no properties are available or another problem accessing the data occurs, then a message will be printed instead of listing the properties.

The output will include all effective properties, both those from the current project and from global configuration. Keys will be sorted alphabetically.

## copyright / license

**mvn-show-settings** is licensed under the MIT License, for more details see license.md
