06.02.2024

Tilstede: Magnus, Ole, Ida, Hanna

Hva vi gjorde:
Orientert gruppeleder, fullført A0.
Diskuterte rundt Konseptet flappy bird.
Valgte libGDX som skjelett,
Lagde git-gruppe, forket prosjekt-mal.

Hva vi skal gjøre neste gang:
Finne ut av karakterer, power-ups og plot
Finne ut navn

Finne ut i nærmeste fremtid:
Hvordan koden kjøres
Hvilke ressurser vi ønsker å bruke

Ideer:
Kaktuser som rør
Flere karakterer å velge i
Powerups: saktere, usynlighet
Ekstra Problemer fra maskin: fly, kaktuser spytter flammer, personer som hindrer.
Fjell fra bunn og mennesker fra topp
Fly med piltaster som beveger seg i et landskap

---

13.02.2024

Tilstede: Magnus, Ole, Ida, Hanna

Hva har vi gjort:
Fikset brukerhistorier og MVP par-kodet av Ole og Ida
Satt oss mer inn i LibGDX og oppgaven - Alle
Kartlegging - Magnus
Spillet heter midlertidig PlaneMane

Hva vi gjorde:
Snakket om hvordan har det gått denne uken
Fikset siste brukerhistorier par-kodet av Ole og Ida
Fordele hva som skal kodes til neste gang
Model-View-Controller ??
Fugler i motoren
Prosjektbeskrivelse
SKal bruke både piltaster og WS til å bevege fly

Hva skal vi gjøre:
Bevegelse - Magnus og Ole
Level handeling, Start meny, Game State - Ida og Hanna

---

20.02.2024

Tilstede: Ole, Hanna

Hva har vi gjort:
Levert del A

Hva vi gjorde:
Skaffet oversikt over neste innlevering
tix-20
tix-21
tix-22

Hva skal vi gjøre:
få mer oversikt over teamroller
Finne en tydelig plan på hvordan fordele kodeoppgaver

---

05.03.24

Tilstede: Magnus, Ida, Ole, Hanna

Hva har vi gjort:
Skrevet en del kode og prøvd å få større forståelse av libgdx

Hva gjorde vi:
fant ut at det vi har basert koden vår på ikke helt fungerer og vi trenger å ta utgangspunkt i det vi har, men starte “på nytt”
Code review
Vi var misfornøyd med vår MVC så vi gikk igjennom og så hva vi ønsker hvor.
Samarbeidet om modularitet og hvordan klassene skal organiseres
Brukte Notion til dette
Lagde klassediagram

Hva skal vi gjøre:
Ommøblere og sette inn klassediagrammet vi lagde i vscode

---

06.03.024

Tilstede: Magnus, Ida, Ole, Hanna

Hva har vi gjort:
base actor

Hva gjorde vi:
Jobbet med background
Fordelte deler av koden, background og plane

Hva skal vi gjøre:
Gjøre prosjektet klart for innlevering
Levere

---

19.03.24

Tilstede
Ole, Ida, Hanna

Magnus er i Stavanger og har jobbet remote. Han jobber med å implementere en Asset Manager som sørger for at ressursene i spillet lastes vellykket før vi gjør noe annet i applikasjonen.

Ida har problemer med vscode. Det er en git-fil som egentlig ikke finnes som gjør at man ikke kan pulle siste versjon av koden vår. Det løste seg fint uansett siden vi gjør mye parkoding. Resultatet er imidlertid at det fører til en skjevfordeling av commits.

I dag jobbet vi med å abstrahere klassen for Enemy. Dette gjør oss i stand til å gjenbruke kode og skape modularitet. Ved å gjøre dette kunne vi enkelt skille vår opprinnelige fugl (hvit fugl som beveger seg lineært) i en egen klasse som utvider Enemy-funksjonalitet.

Dette gjorde oss også i stand til å utvide programmet vårt med ønsket funksjonalitet som gjenspeilet i vår MVP-punkter som for eksempel introduksjon av flere fiender. Vi laget dermed en ny klasse for en posisjonssøkende fugl som beveger seg i retning av flyet (PinkBird). I tillegg gjør det oss i stand til å levere på akseptansekriterier knyttet til Kristians brukerhistorie om ønske om et utfordrende spill.

Vi har også funnet en bug som gjør at spillet ikke fungerer når man skal starte det. Da må man kommentere ut et bilde og deretter starte spillet og så kommentere bildet inn igjen. Årsaken er litt vanskelig å tolke, men vi tror det kommer av at bildet ikke klarer å laste inn før vi prøver å få tilgang til det, som gjør at applikasjonen kræsjer. Vi ønsker å løse dette med å implementere en asset manager som sørger for at våre assets er ferdig lastet før vi prøver å bruke dem.

Vi kartla hvordan vi skal jobbe gjennom påsken.

Det vi trenger å jobbe med er:
Bird som følger etter flyet som kommer inn etter en viss tid i spillet.
Flere powerups
Implementere en asset manager for å bli kvitt nevnt bug
Utbedring av hvordan livene ser ut.
Utbedring av level og screen håndtering
Tester
Timer + game over screen med highscore og start
Kunne bruke piltaster

Forbedring av kode generelt

---

20.03.24

Tilstede
Ole, Ida, Hanna

Magnus er i Stavanger, og jobber dermed remote. Han har jobbet videre med Asset Manager.

Selv om vi forbereder oss til individuell jobbing i påsken, ønsket vi å kunne møtes igjen i dag for å få litt mer samkjøring og kontroll på hva vi ønsker av koden vår.

Ida og Ole jobbet med å finne ut av hvordan vi best og enklest mulig kunne slå enemy og energy factoriesene til en generic object factory istedenfor for å unngå gjenbruk av kode.

Hanna jobbet med å lage en Clock() og HighScore(). Vi ønsker å time hvor lenge man spiller for så å lagre tiden i en highscore tabell. Vi diskuterte om vi ville ha klokken integrert inne i GameController() siden den var relativt simpel, men vi synes allikevel det var mer oversiktlig for oss å lage en separat klokkeklasse. Vi lurte også på om vi skulle bruke deltaTime i stedet for en egen klasse, men siden vi ønsker å pause spillet vårt vil det bli mer komplisert.

---

04.04.24

Tilstede:
Ida, Ole. Remote: Magnus, Hanna

Vi kjenner det er seigt å være tilbake etter påskeferien. Vi kjører et standup om hva vi har jobbet med i påsken. Magnus deltar remote, og tar oss gjennom implementasjonen av Asset Manager. Hanna viser hva hun har gjort på Clock og Highscore.

I dag jobbet vi med å lage tester. Ida laget en test for PlaneMane, og testene gikk hovedsakelig i å sjekke om planeMane har ulike instanser. Ole jobbet med å lage en test for å sjekke om ressursene vi bruker faktisk eksisterer i prosjektet. I tillegg lages et par tester for GameModel som sjekker bla. om spillet ender opp i en GAME OVER - state om man har 0 health igjen på flyet.

---

05.04.24

Tilstede: Ole, Ida, Hanna.

Magnus jobber remote siden han fortsatt er hjemme. Han jobber med en bug som gjør at piloten ikke forsvinner når spillet fungerer.

Ida og Ole jobber med tester.

Hanna jobber med å kunne bruke clock() til både poeng og for å time når ulike enemies/power-ups skal dukke opp i spillet.

---

08.04.24

Tilstede: Ole, Ida, Hanna, Magnus

Ida lager interfacet ViewableGameModel() og EntityGameModel()
Hanna ferdigstiller TaskManager, jobber med points og highscore.
Ole har jobbet videre med tester for Game Model. Største utfordring var å teste at “game over”-musikk spilles når spillet går over i en Game Over state. Her valgte vi å utvide konstruktøren til Game Model til å ta inn en AudioUtil, og testen fungerer optimalt da.
Magnus jobber med eventbus

---

09.04.24

Tilstede: Ole, Hanna, Ida, Magnus

Ida jobber med GameState restart
Hanna jobber med point
Ole jobber videre med tester

Vi diskuterte hva vi ønsket å ha klart til innleveringen. Vi ønsker midlertidig å ferdigstille det vi aktivt jobber på nå som er å få tester på alle public metoder, mulighet til å restarte spillet og legge til muligheter for å hente inn poeng.

---

16.04.24

Tilstede: Ole, Ida, Hanna, Magnus (Remote)

Fant tilbakemelding fra forrige obliger, gikk igjennom dem for å finne ut av hva vi må få gjort til siste innlevering og hva som må forbedres.

Vi har bestemt oss for å skrive commits og issues på engelsk.

La inn hva vi ønsker gjort til siste innlevering i issueboard.

---

23.04.24

Tilstede: Ole, Ida, Magnus, Hanna

Vi jobber med tester

---

30.04.2024

Tilstede: Ole, Ida, Magnus, Hanna

Siste innspurt
Hva må pirkes på før innlevering:
Ferdigstille tester
Fikse ESC
Fikse Pom.xml
Fikse README.md
Klassediagram
Skrive ferdig oblig4.md

---

01.05.2024

Tilstede: Ole, Ida, Hanna, Magnus (Remote)

Magnus gjorde ferdig mange tester for View. Ida gjorde ferdig test for planecontactlistener. Hanna fikk oversikt over referater. Ole ferdigstilte oblig4.

---

02.05.2024

Tilstede (alle remote): Ole, Ida, Hanna, Magnus

Vi ble samstemte om at alt er på plass til innlevering.
