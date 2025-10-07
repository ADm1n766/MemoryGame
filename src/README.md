# Grundüberlegungen / Architektur
- MVC
Modell: Paare, Status
View: GUI, Buttons
Controller: Event-Handling

- möglichst wenig doppelten Code, generische Strukturen
- GUI-Teil modular, sodass JButtons durch andere ausgetauscht werden könnten (Anfang: JButton + Icon)
- Layout der Felder ist Gitter (`GridLayout`)


## Felder

1. Array / zweidimensionales Array
Z. B. JButton[][] buttons = new JButton[rows][cols].
Vorteil: man kann bequem über Reihen/Spalten iterieren. Man weiß direkt, welches Nachbarfeld oben/unten etc. ist (nützlich, falls du später z. B. 2D-Verknüpfungen brauchst).
Nachteil: wenn du später unregelmäßige Layouts willst oder sehr flexible Felder, kann es etwas starrer sein.

2. Eindimensionales Array oder Liste (z. B. JButton[] oder List<JButton>)
Vorteil: einfacher Handhabung bei zufälligem Zugriff, Mischen (shuffle). Man kann z. B. eine Liste von Kartenobjekten haben, sie mischen, und dann in ein Grid setzen.
Nachteil: man muss Indizes umrechnen (z. B. in Zeile/Spalte) — das ist aber meist gut machbar.

3. ArrayList oder List von spezielleren Objekten (z. B. eine eigene Card-Klasse)
Dann habe ich List<Card> cards = new ArrayList<>();
Vorteil: ich kann Logik und GUI-Einbindung elegant koppeln, ich kann einfacher zusätzliche Felddaten speichern (z. B. welcher Typ, welches Bild).
Ich kann mischen: Collections.shuffle(cards).
Später beim Klickhandler bekomme ich direkt das Card-Objekt, kann Status ändern etc.

4. Für jedes Feld eine eigene Variable/JButton (z. B. btn1, btn2 …)
Das ist meist keine gute Idee, außer nur sehr wenige Felder. Es führt zu viel starren Code und unflexiblem Design. Ich würde dies nicht wählen.

5. Dynamische Komponenten-Erzeugung zur Laufzeit, eventuell mit Data-Binding
Z. B. du könntest eine generische Panel-Klasse machen, die ein „Feld“ darstellt, das man dann mehrfach instanziiert. Intern hat das Panel ein Label oder Bild oder Button. Das ist eine Variante von (3), aber etwas abstrahierter.