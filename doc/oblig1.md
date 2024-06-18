# Rapport – innlevering 1

**Team:** _4GILE_ – _Magnus Haaland, Ida Karoline Løken, Ole Eiane, Hanna Søndenaa Rasmussen_

# Oppgave A2: Konsept

# Spillbeskrivelse

## Generell beskrivelse av spillet

- Spillet er et plattformspill som spilles i et horisontalt 2D (todimensjonalt) format.

- Spillfiguren:
  - Beveges automatisk i retning øst ved at verden flyttes i spillkameraets "view".
  - Kan styres opp og ned med input fra brukeren.
  - Kan stoppe og starte med input fra brukeren.
  - Har et "energinivå" som må være positivt for å spille.
- Innhold i spillverden:
  - Bakke som spilleren starter på.
  - "Power-ups" og elementer som spilleren kan interagere med for å oppnå progresjon i spillet.
  - Kollisjonselementer, som er alle elementer spilleren kan kollidere med. En kollisjon fører til "game over".
- NPCer (Non-Player Character):
  - Datastyrte spillkarakterer som forsøker å utføre handlinger mot eller kollisjoner med spilleren. Interaksjon med en NPC kan føre til game over eller redusert energinivå for spilleren.
- Oppbygging av spillet:
  - Spillets kamera ("view") er mindre enn selve verdenen. Verden utforskes dermed ved automatisk flytting av spillkameraet, som gir en illusjon av at spilleren beveger seg fra vest til øst.
  - Spilleren starter på bakken, og beveger seg mot første power-up. Når spilleren tar power-upen kan spillfiguren fly.
  - Spilleren skal unngå kollisjonselementer, men må huske på å fylle opp energinivået. Dette gjøres gjennom ulike power-ups, eksempelvis "bensinstasjoner".

## Konkretisering av spillets elementer

### Spillfigur

- Spillfiguren er et lite propellfly.
- Kan beveges opp og ned, samt startes og stoppes av brukerens input.
- Har en gitt mengde drivstoff som tillater spillet.
- Kan gå langs bakken og fly, avhengig av power-ups som er tatt av spilleren.
- Må unngå å kollidere med kollisjonselementer eller NPCer.

### Kollisjonselementer

- Bygninger laget av 2D-blokker.
- Gjerder.
- Hekk.

### Spillets verden

- Spillkameraet ruller automatisk når spillfiguren er i bevegelse, slik at mer av spillverdenen vises/"avsløres" fra retning øst.
- Har enkelte faste elementer som alltid er på samme plass.
- Flere elementer plasseres tilfeldig innenfor gitte rammer for å skape en mer spennende spillopplevelse.

# A3: Velg og tilpass en prosess for teamet

### Møter og hyppighet av dem

Vi planlegger å kjøre møter hver tirsdag 10.15-12.00 og 14.15-16.00 og dersom det er behov for det har vi et mål om å være fleksible og kunne møtes til andre tidspunkter.

### Kommunikasjon mellom møter

I tillegg planlegges det at kommunikasjon i hovedsak foregår over discord og at vi på slutten av hvert møte planlegger når vi skal møtes neste gang og om det er behov for et møte tidligere enn de faste møtene.

### Arbeidsfordeling

Vi tenker å bruke Kanban metoden for å ha oversikt over hva som må gjøres og hvem som jobber med hva.

### Oppfølging av arbeid

Oppfølging av arbeid blir gjort på starten av hvert møte ved å ha en standup der vi går igjennom hva som har blitt gjort siden sist og går igjennom hvordan vi ligger an i forhold til målene.

### Deling og oppbevaring av felles dokumenter, diagram og kodebase

Vi jobber i hovedsak i Git.

### Utenomfaglige aktiviteter

Vi kjører en fellesmiddag i måneden for å bygge teamkontakt og bygge sosiale relasjoner for å gjøre terskelen for å kommunisere med hverandre lavere.

# A3: Få oversikt over forventet produkt

### Overordnet mål for applikasjonen

Det overordnede målet for applikasjonen er et interaktivt spill som gir brukeren en behagelig og spennende spillopplevelse. For å oppnå dette har vi følgende krav til ** Minimum Viable Product **:

1. Vise et spillbrett
2. Vise spiller på spillbrettet
3. Viser en bevegelig spiller midt i spillbrettet (styres vha. tastetrykk)
4. Spiller interagerer med terreng
5. Vise fiender som skal interagere med terreng og spiller
6. Spillbrettet utvikler seg etterhvert som spillet beveger seg (høyere tempo, flere fiender, tettere mellom hvert bygg)
7. Spiller kan dø (ved å gå tom for drivstoff, kontakt med fiender, eller ved å kræsje i terreng)
8. Nytt spillbrett når spillet er ferdig
9. Lydavspilling ved ulike hendelser i spillet (når spiller fyller drivstoff, bakgrunnsmusikk)
10. Start-skjerm ved oppstart / game over

### Brukerhistorier

- Karsten er en ung og spillinteressert tenåring. Han har lenge slitt på skolen på grunn av lese- og lærevansker. Som gamer er det ofte vanskelig å forstå hva man skal gjøre. Han trenger dermed konkrete og enkle startinstrukser før han setter i gang et spill.

- John er en ung mann som studerer kunst på Kunsthøgskolen i Bergen. Han er veldig opptatt av estetikk. Musikk er også en av hans lidenskaper. Når han har litt tid til overs, setter han seg gjerne ned for å spille litt. Da er det viktig for han at spillet har et fint utseende, og musikk/lyder til spillet er også en avgjørende faktor om han liker spillet.

- Petra er venstrehendt og synes det er tungvindt å måtte bruke høyrehånden for å spille med piltastene når hun spiller spill. For hun er det viktig å kunne bruke wasd tastene for å styre spilleren for å kunne yte spillet optimalt.

- Olivia er en tenåring som er glad i å spille. I det siste har hun spilt mange spill som har blitt hastet ut på markedet. Resultatet har vært en spillopplevelse fylt av feil og bugs. Dette gjør at Olivia føler på en håpløshet og legger derfor ofte fra seg spillet for å aldri spille det igjen.

- Kristian elsker en god utfordring, og særlig i spillene han spiller. Hvis spillet ikke utvikler seg over tid og blir vanskelig nok, blir han fort lei av spillet. Kristian liker derfor spill hvor spillfiguren interagerer med terreng hvor fiender og andre objekter prøver å få han til å tape.

### Akseptansekriterier og arbeidsoppgaver

**1. Karstens brukerhistorie: Konkrete og enkle startinstrukser**

- Akseptansekriterier:
    - Spillet skal ha en enkel og intuitiv startskjerm med tydelige instruksjoner.
    - Instruksjonene skal være lette å forstå, med minimal tekst og visuelle hjelpemidler der det er mulig.
- Arbeidsoppgaver:
    - Designe en startskjerm som er visuelt tiltalende og enkel.
    - Utvikle en kort tutorial eller en guide med visuelle instruksjoner.

**2. Johns brukerhistorie: Estetikk og lyd**

- Akseptansekriterier:
    - Spillet må ha et visuelt tiltalende design.
    - Musikk og lydeffekter skal forbedre spillopplevelsen.
- Arbeidsoppgaver:
    - Designarbeid for å skape et estetisk spillbrett og spillelementer.
    - Lage musikk som passer til spillets utseende.
    - Implementere lydeffekter for spesifikke hendelser i spillet.

**3. Petras brukerhistorie: WASD-styring for venstrehendte**

- Akseptansekriterier:
    - Spillet skal la spilleren bruke WASD-tastene for bevegelse.
- Arbeidsoppgaver:
    - Implementere bevegelsesfunksjonalitet som tillater bruk av både piltaster og WASD.

**4. Olivias brukerhistorie: Kvalitetssikring for å unngå bugs**

- Akseptansekriterier:
    - Spillet skal gjennomgå grundig testing for å identifisere og rette opp i feil og bugs.
    - Feedback fra spilltestere skal brukes aktivt for å forbedre spillopplevelsen.
- Arbeidsoppgaver:
    - Gjennomføre testing med både automatiserte tester og brukertester.

**5. Kristians brukerhistorie: Utfordring og interaksjon**

- Akseptansekriterier:
	- Spillerens fremgang skal utfordres av objekter (for eksempel fiender, hindringer) som  prøver å hindre spilleren.
	- Spillet skal eskalere i vanskelighetsgrad over tid, både gjennom økt tempo og ved introduksjon av flere og vanskeligere fiender.
- Arbeidsoppgaver:
	- Utvikle et terreng med varierte fiender og hindringer.
	- Implementere funksjonalitet som øker vanskelighetsgraden i spillet gradvis.

### Prioritert liste for første iterasjon

Med utgangspunkt i vårt *minimal viable product* og våre brukerhistorier, vil dette være den prioriterte listen over brukerhistorier for første iterasjon:

1. **Kristians brukerhistorie:** Interaksjon mellom spiller, terreng og fiender/hindringer er essensielt for å holde spilleren engasjert.
2. **Olivias brukerhistorie:** Kvalitetssikring er viktig for å sikre en feilfri spillopplevelse, noe som kan være avgjørende for spillets suksess og spillernes tilfredshet.
3. **Karstens brukerhistorie:** Denne har høy prioritet fordi en god startopplevelse er kritisk for å engasjere spillere tidlig.
5. **Johns brukerhistorie:** Estetikk og lyd forbedrer spillopplevelsen. Resultatet kan være å tiltrekke og beholde spillere som setter pris på akkurat dette.
6. **Petras brukerhistorie:** Tilrettelegging for venstrehendte øker spillets tilgjengelighet, men kan prioriteres lavere siden det påvirker en mindre målgruppe.

### A5: Oppsummering

- Vi kom fort igang med konsept og spillidé.

- Vi har hatt fokus på å prøve ut forskjellige måter å bruke git på, og har derfor en noe rotete git-historikk.Vi har foreløpig landet på å levere denne innleveringen ved å kun ha en branch: main. Fremover vil vi plukke issues fra issue-board og jobbe basert på branches vi lager for det spesifikke issuet vi er “assigned” eller “assignee”, og vil merge disse med main med en gang issues er løst/features er implementert. Dette vil holde kodebasen oppdatert løpende, med tydeligere historikk.

- Vi følte vi traff ganske greit på oppgaven, men mens vi skulle komme i gang var det vanskelig å fordele oppgaver så alle alltid hadde noe å gjøre, siden det i oppstartsfasen ofte kun var en ting som skulle/kunne gjøres om gangen både av organisatorisk hensyn og i forhold til egen kompetanse rundt denne typen prosjektarbeid.

- Vi vil poengtere at alt arbeid gjort som samarbeid ved fysiske møter med alle gruppemedlemmene, og at vi har mye av kommunikasjonen vår utenfor Discord som en følge av dette.
