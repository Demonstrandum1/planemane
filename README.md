# INF112 - Gr.5 4GILE - Plane Mane

## General project-information

> [!INFO] Team name and group information
> Team name: _4GILE_
> Group number: 5

**Team members:**

- *Tech lead – Magnus Haaland*
- *Project lead – Ida Karoline Løken*
- *Customer contact – Ole Eiane*
- *Team lead – Hanna Søndenaa Rasmussen*

**Project name**:

- *Plane Mane*

**Code**:

- *The program is executed through Main.java from the IDE, or with Maven through mvn exec:java@run-app.You may have to compile it first with mvn compile*

**The Game**

About the game:

Plane Mane is a horizontal scrolling game set in a world of clouds, trees, and runways. The player controls an airplane that must maneuver through a landscape with runways, avoiding birds, and collecting energy objects and points. There are two types of birds in the game: those that move in a linear path and those that actively seek the airplane's position. The game starts when the player "boards" the airplane by moving a pilot into it.

How to play the game:

- Start the game: Open the game by running Main.java from the IDE or with Maven through mvn exec:java@run-app.
- Control the airplane: Use the arrow keys or WASD to move the airplane and avoid obstacles.
- Collect objects: Hit energy objects and point objects to increase life and score.
- Avoid birds: Watch out for the two different types of birds that can reduce the airplane's life upon contact.
- Increased game difficulty: as the time progresses in the game, the possibility of pink birds spawnning is increased. The same applies to energy objects and point objects.
- Restart the game: If you lose, restart the game by pressing 'Space' to start over.

**Resources**:

- All resources used (images, sprites, music, etc.) are self-produced.


# Maven Setup

---

This project comes with a working Maven `pom.xml` file. You should be able to import it into Eclipse using _File → Import → Maven → Existing Maven Projects_ (or _Check out Maven Projects from SCM_ to do Git cloning as well). You can also build the project from the command line with `mvn clean compile` and test it with `mvn clean test`.

Pay attention to these folders:

- `src/main/java` – Java source files go here (as usual for Maven) – **IMPORTANT!!** only `.java` files, no data files / assets
- `src/main/resources` – data files go here, for example in an `assets` sub-folder – **IMPORTANT!** put data files here, or they won't get included in the jar file
- `src/test/java` – JUnit tests
- `target/classes` – compiled Java class files

We have now made some edits to the `pom.xml` and filled in details such as the project `name` and `artifactId`:

```xml

	< !-- FIXME - set group id -->
	<groupId>no.uib.inf112.4-gile</groupId>
	< !-- FIXME - set artifact name -->
	<artifactId>plane-mane-game</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>jar</packaging>

	< !-- FIXME - set app name -->
	<name>Plane Mane</name>
	< !-- FIXME change it to the project's website -->
	<url>https://git.app.uib.no/Ole.Eiane/4-gile/</url>
```

## Running

---

You can run the project with Maven using `exec:java@run-app`. You may compile it first. We have changed the main class by modifying the `main.class` setting in `pom.xml`:

```
		<main.class>PlaneMane.Main</main.class>
```

Running the program should open a window with a welcome screen showing you how to play the game. -> First, you need to move the pilot with the WASD/arrow-keys so that it touches the plane. Then the game will play! :)

You may have to compile first, with `mvn compile` – or in a single step, `mvn compile exec:java@run-app`.

## Testing

---

Run unit tests with `mvn test` – unit test files should have `Test` in the file name, e.g., `ExampleTest.java`. This will also generate a [JaCoCo](https://www.jacoco.org/jacoco) code coverage report, which you can find in [target/site/jacoco/index.html](target/site/jacoco/index.html) when you open a local server.

Use `mvn verify` to run integration tests, if you have any. This will do everything up to and including `mvn package`, and then run all the tests with `IT` in the name, e.g., `ExampleIT.java`.

## Jar Files

If you run `mvn package` you get everything bundled up into a `.jar` file + a ‘fat’ Jar file where all the necessary dependencies have been added:

- `target/NAME-VERSION.jar` – your compiled project, packaged in a JAR file -> `target/plane-mane-game-1.0-SNAPSHOT.jar`
- `target/NAME-VERSION-fat.jar` – your JAR file packaged with dependencies -> `target/plane-mane-game-1.0-SNAPSHOT-fat.jar`

Run Jar files with, for example, `java -jar target/NAME-VERSION-fat.jar`. You may do this with `java -jar target/plane-mane-game-1.0-SNAPSHOT-fat.jar`


If you have test failures, and _really_ need to build a jar anyway, you can skip testing with `mvn -Dmaven.test.skip=true package`.

## Git Setup

If you look at _Settings → Repository_ in GitLab, you can protect branches – for example, forbid pushing to the `main` branch so everyone have to use merge requests.

# Credits

All resources are produced by us.
