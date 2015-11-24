## Library .jar

### Configure .jar project steps

> `File`, `Project Structure` or `Command + ;` to open the `Project Structure` dialog

> On the left pane, click `Artifacts`, click `+` and select `JAR`, click `From modules with dependencies...`

> On the Create JAR from Modules dialog, click `Main Class` `...` button, select `App (com.company.lib)`, click `OK` and click "OK" to dismiss the dialog

> On the `Project Structure` dialog, check `Build on make` checkbox, click `Apply` button, and click `OK` button to dismiss the dialog

> `Build`, `Make Project` or `Command + F9` to make project, result out folder is created.

## App that references the 01-library .jar project

### Configure .jar reference steps

> `Build`, `Make Project` 01-library .jar project first

> `File`, `Project Structure` or `Command + ;` to open the `Project Structure` dialog

> On the left pane, click `Modules`, click `Dependencies` tab

> Click `+` and find and add `01_library.jar` file that is in `01-library/out` folder

> Click `Apply` button, and click `OK` button to dismiss the dialog
