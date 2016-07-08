# External library

## Library

> `command + ;`, `Artifacts`, `+`, `JAR`, `From modules with dependencies...`

> `Main class:`, click `...` button, select `App (com.company.lib)`, `OK`, `OK`

> check `Build on make`, `Apply` `OK`

> `Build`, `Make Project` or `Build`, `Build Artifacts...` - `library.jar` is created in the `out` folder

## Application that references the Library above as External library

> `command + ;`, `Modules`, on the right most, click `Dependencies` tab

> `+`, `1 JARs or directories...`, find the `library.jar` in the `library` project `out` folder, `Apply`, `OK`
