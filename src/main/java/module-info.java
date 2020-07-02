// Seit Java9 kann man ein Java-Projekt in Module aufteilen
// Module werden dann in einer solchen Datei konfiguriert
module pmzwei {

    // Abhängigkeiten zu anderen Modulen (Import)
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires com.google.common;
    requires org.json;

    // API des Moduls (von außen sichtbar) - hier müssen alle Packages mit Anwendungsklassen dabei sein
    // exports ist eine weitere Sichtbarkeitsebene über private/protected/public (Export)
    exports praktikum.aufgabe1.darstellung;
}