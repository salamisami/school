# Readme zur Vorgabe AD

Das Framework ist ausgelegt auf JDK 14

## Einrichten

### IntelliJ

#### Importieren

IntelliJ -> Import -> Gradle

#### Build, Ausführen und Testen über IntelliJ

IntelliJ -> Preferences -> Build/Execution/Deployment -> Build Tools -> Gradle

## Ausführen

* rechte Maustaste auf Anwendungsklasse
* Run as -> Java Application
* Sonderfall Java-FX-Anwendungen
  * Application-Klasse in der build.gradle auswählen: mainClassName = ...
  * Gradle build/build (rechts im Gradle-Toolfenster)
  * Gradle application/run (rechts im Gradle-Toolfenster)
