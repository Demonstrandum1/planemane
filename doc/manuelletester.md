# Testscenarioer

### Startskjerm

**Formål: Teste startskjermen**

Handlinger:
* Start spillet.
* Observer startskjermen for klare og forståelige instruksjoner.

Forventet resultat:
* Startskjermen skal vise tydelige instruksjoner.

### Spillskjerm

**Formål: Teste spillskjermen**

Handlinger:
* Start spillet og board flyet ved å bevege piloten inn i flyet.

Forventet resultat:
* Observer at det tegnes en bakgrunn med ulike elementer som rullebane, trær, himmel, skyer.
* Observer at det finnes et helsenivå i øvre, høyre hjerne illustrert som hjerter.
* Observer en spillfigur (fly) som kan beveges arbitrært.
* Observer en "Score" i øvre, venstre hjørne.

### Pauseskjerm

**Formål: Teste pauseskjermen**

Handlinger:
* Start spillet og trykk på "P".
* Pauseskjermen skal gjøre at spillet stopper, samt viser en ny skjerm med instrukser for å blant annet fortsette spillet igjen.

### Hjelpeskjerm

**Formål: Teste hjelpeskjermen**

Handlinger:
* Start spillet og trykk på "P", og så trykk "H".
* Hjelpeskjermen skal vise tydelige instruksjoner.

### "Game Over"-state og restart

**Formål: Teste "Game Over"-skjermen, samt å starte spillet på nytt**

Handlinger:
* La spillfiguren kollidere med kollisjonselementer (fugler) for å la spillfiguren "dø"
* Trykk så "Space"-tasten for å restarte spillet

Forventet resultat:
* En ny skjerm som tydelig indikerer at det er Game Over. Det skal også avspilles en ny sang
* Spillet skal startes på nytt ved å restarte spillet med "vanlig" spillmusikk


### Spillkamera og automatisk bevegelse

**Formål: Kontrollere at spillkameraet beveger seg jevnt**

Handlinger:
* Start spillet og observer bakgrunnens automatiske bevegelse
* Kontroller at kameraet beveger seg mot øst

Forventet resultat:
* Kameraet skal rulle jevnt

### Interaksjon og energinivå

**Formål: Sjekke at spillfigurens energinivå påvirkes korrekt av interaksjoner i spillet**

Handlinger:
* Styr spillfiguren til å samle et energiobjekt
* La spillfiguren kollidere med et kollisjonselement

Forventet Resultat:
* Samling av energiobjekt øker energinivået visuelt på skjermen indikert av hjertene i høyre hjørne
* Kollisjoner reduserer energinivået eller fører til "Game Over"
	* Redusert energinivå indikeres av hjertene i høyre hjørne
	* «Game Over» vil oppstå når man kolliderer med en fugl og har «null liv» igjen indikert av at alle hjertene er grået ut

**Formål: Verifisere at NPCer oppfører seg som forventet i møte med spillfiguren**

Handlinger:
* Naviger spillfiguren nær en NPC
* Observer NPCens eventuelle reaksjon og dens effekt på spillfigurens energi.

Forventet Resultat:
* NPCer skal bevege seg på ulik måte
	* Hvit fugl:
		* Vil bevege seg lineært
	* Rosa fugl:
		* Observer at denne fuglen beveger seg i retning av flyets posisjon. Observer imidlertid at fuglen stopper denne distinkte bevegelsen (og fortsetter med lineær bevegelse) om flyets snute har passert fuglen
* Ved å bevege seg inn i fuglene, skal det være riktig visuelt og lydmessig. Det dreier seg om at fuglene skal falle ned fra skjermen og forsvinne. Lydeffekt skal også spilles av ved denne hendelsen

### Interaksjon og poeng

**Formål: Sjekke at poengsystemet påvirkes korrekt av interaksjoner i spillet**

Handlinger:
* Styr spillfiguren til å samle et poengobjekt

Forventninger:
* Samling av poengobjekt øker "Score" med 100 poeng hver som indikert i øvre, venstre hjørne
* Det skal spilles av en lydeffekt ved denne hendelsen



