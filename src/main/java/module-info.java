// Seit Java9 kann man ein Java-Projekt in Module aufteilen
// Module werden dann in einer solchen Datei konfiguriert
module ad {

    // Abhängigkeiten zu anderen Modulen (Import)
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires com.google.common;

    // API des Moduls (von außen sichtbar) - hier müssen alle Packages mit Anwendungsklassen dabei sein
    // exports ist eine weitere Sichtbarkeitsebene über private/protected/public (Export). Außerdem müssem
    // die Testpackages hier auftauchen.
    exports praktikum.aufgabe1;
    exports praktikum.aufgabe3;
    exports praktikum.aufgabe4;

    // Loesung
    exports praktikum.loesung.aufgabe1;
    exports praktikum.loesung.aufgabe2;
    exports praktikum.loesung.aufgabe3;
    exports praktikum.loesung.aufgabe4;
}