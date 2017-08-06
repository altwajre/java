# Use picked port variable in other plugin

Use the `build-helper-maven-plugin` to pick up a free port.
Once found, the plugin assigns the picked port to the `http.port` variable.
This plugin is executed early in the build (during the process-sources phase), 
so the http.port variable can be used in the other plugin.

## Intellij 

> Test Run

Click `Maven Projects` on the right border, expand Lifecycle, click `verify` to run integration tests after unit tests

> Result

```
Running com.company.app.unit.AssignPortToVariableTest
unit: port=56899
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.044 sec

Running com.company.app.integration.UsePortVariableInOtherPluginIT
integration: port=56892
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 sec - in com.company.app.integration.UsePortVariableInOtherPluginIT
```
