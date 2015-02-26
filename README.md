English version ([german version below](https://github.com/felixbarny/stagemonitor-ajax-timing#stagemonitor-plugin-workshop-1))

# Stagemonitor Plugin Workshop

In this workshop we're goning to develop a plugin for stagemonitor which measures and visualizes the execution time of ajax requests.

![Ajax Plugin](https://cloud.githubusercontent.com/assets/2163464/6415148/53c64ad6-be9d-11e4-88ea-aecbb3bc0f01.png)


## Necessary Tools
 * Java
 * Apache Maven
 * IDE with Tomcat plugin or normal Tomcat installation

## Components
The plugin consists of a visualisation and a measuring component.

### Metrics Tab Plugin
This is the visualisation part which enhances the metrics tab of the in browser widget (see screenshot above).

The following files are relevant for this:

#### /src/main/java/org/stagemonitor/ajaxtiming/AjaxTimingPlugin.java:
In this file the path to the HTML and JavaScript is registered.

#### /src/main/resources/META-INF/resources/stagemonitor/static/ajaxtiming/ajax-timing.js:
Contains a declarative description which graphs and table columns should be displayed.

#### /src/main/resources/META-INF/resources/stagemonitor/static/ajaxtiming/ajax-timing.html:
Contains the skeleton html where the graphs and the table are going to be inserted.


### Monitoring of Ajax Requests

#### /src/main/resources/time-ajax-requests.html
This File contains a JavaScript snippet that measures the execution time of all Ajax requests and sends the results
back to the server in batches.

#### /src/main/java/org/stagemonitor/ajaxtiming/AjaxTimerHtmlInjector.java
Takes care that the snippet in time-ajax-requests.html is inserted into every HTML page.

#### /src/main/java/org/stagemonitor/ajaxtiming/AjaxServlet.java
Receives the measurements and records them with the help of the MetricRegistry.

## TODOs
The code contains some TODOs that need to be done. The unit tests show you whether the Java code is correct.

## Test
Execute `./gradlew install` (Unix) or `gradlew.bat install` (Windows) in the stagemonitor-ajax-timing directory to
build and install the plugin into your local maven repository.

To test the plugin with a real application, follow
[this](https://github.com/stagemonitor/stagemonitor/wiki/Installation#set-up-spring-petclinic) guide or deploy PetClinic
with a Tomcat plugin in your favourite IDE

Add this dependency to PetClinic's pom.xml file:

```
<dependencies>
	...
	<dependency>
		<groupId>org.stagemonitor</groupId>
		<artifactId>stagemonitor-ajax-timing</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
	...
```

------


Deutsche Version

# Stagemonitor Plugin Workshop

In diesem Workshop entwickeln wir ein Plugin für stagemonitor, welches die Ausführungszeiten von Ajax-Requests misst und
diese dann graphisch und tabellarisch darstellt.

![Ajax Plugin](https://cloud.githubusercontent.com/assets/2163464/6415148/53c64ad6-be9d-11e4-88ea-aecbb3bc0f01.png)


## Benötigte Tools
 * Java
 * Apache Maven
 * IDE mit Tomcat plugin oder normale Tomcat installation

## Komponenten
Das Plugin besteht im Wesentlichen aus zwei Teilen - einer Visualisierungs und einer Datenerhebungskomponente.

### Metrics Tab Plugin
Dies ist der Darstellungsteil, welcher das Metrics-Tab des In-Browser widgets erweitert (siehe Screenshot oben).

Hierfür sind folgende Dateien relevant:

#### /src/main/java/org/stagemonitor/ajaxtiming/AjaxTimingPlugin.java:
In dieser Datei wird der Pfad zu den HTML- und JavaScript Dateien hinterlegt.

#### /src/main/resources/META-INF/resources/stagemonitor/static/ajaxtiming/ajax-timing.js:
Enthält eine deklarative Beschreibung, welche Graphen und Tabellenspalten dargestellt werden sollen.

#### /src/main/resources/META-INF/resources/stagemonitor/static/ajaxtiming/ajax-timing.html:
Enthält das Grundgerüst, in das die Graphen und die Tabelle eingefügt wird.


### Monitoring der Ajax Requests

#### /src/main/resources/time-ajax-requests.html
Diese Datei enthält einen JavaScript Schnipsel, welcher dafür sorgt, dass die Ausführungszeit aller Ajax Requests
gemessen und das Ergebnis in Bündeln an den Server zurückgesendet wird.

#### /src/main/java/org/stagemonitor/ajaxtiming/AjaxTimerHtmlInjector.java
Sorgt dafür, dass das in time-ajax-requests.html enthaltene Schnipsel in jede HTML Seite eingefügt wird.

#### /src/main/java/org/stagemonitor/ajaxtiming/AjaxServlet.java
Nimmt die Messergebnisse entgegen und zeichnet diese in der MetricRegistry auf.

## TODOs
Der Code enthält Lücken, die es im Rahmen dieses Workshops auszufüllen gilt. Diese sind mit `TODO` gekennzeichnet.
Die Enthaltenen Unit Tests geben einen guten Anhaltspunkt, ob der Java code korrekt ist.

## Testen
Führe `./gradlew install` (Unix) bzw. `gradlew.bat install` (Windows) im stagemonitor-ajax-timing Verzeichnis aus, um
das Plugin zu bauen und im lokalen Maven repository zu installieren.

Um das Plugin mit einer echten Anwendung zu Testen, folge dieser Anleitung
https://github.com/stagemonitor/stagemonitor/wiki/Installation#set-up-spring-petclinic oder binde PetClinic direkt in
Deine IDE ein.

Füge in der Datei pom.xml folgende Dependency hinzu:

```
<dependencies>
	...
	<dependency>
		<groupId>org.stagemonitor</groupId>
		<artifactId>stagemonitor-ajax-timing</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</dependency>
	...
```
