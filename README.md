# Covid summary

Dokumentace pro semestralní projekt v předmětu ALP2S
### Zadaní práce
Napište program pro stažení a zobrazení dat ohledně COVID-19. Data jsou uložená ve formě JSON na stránkách MZCR (https://onemocneni-aktualne.mzcr.cz/). Zobrazte data za použití externí knihovny, která data zobrazí v grafech. Jedno zobrazení by mělo být za celou pandemii, druhé zobrazení pro určitý časový úsek. Graf by se měl po zobrazení uložit pod specifickým názvem jako obrázek do příslušného adresáře.
### Návrh řešení 

1. Nafetchování dat
    * Stažení API s daty o umrtí, testech a vyležených/nakažených
        * Naparsování dat do třídy dataVUT
3. Zobrazení dat o umrtí
    * Zobrazení dat o umrtí od začatku pandemie
        * Vykreslení a uložení celých dat
    * Zobrazení dat o umrtí se zadaním počtu dnů 
        * Vykreslení a uložení dat za určité období 
4. Zobrazení dat o testech
    * Zobrazení dat o testech od začatku pandemie
        * Vykreslení a uložení celých dat 
    * Zobrazení dat o testech se zadaním počtu dnů
        * Vykreslení a uložení dat za určité období 
5. Zobrazení dat o vyléčených/nakažených
    * Zobrazení dat o vylečených a nakažených od začatku pandemie
        * Vykreslení a uložení celých dat 
    * Zobrazení dat o vylečených se zadaním počtu dnů
        * Vykreslení a uložení dat za určité období 
6. Ukončení programu

### Objektový model

![UML Diagram](https://github.com/jenicekhartigu/ALG2021LS_PROJ/blob/main/umldiagram.png)

V UML diagramu jsou zobrazeny i jednotlivé proměné a jejich datové typy

### Externí knihovna Xchart

K zobrazení dat jsem si našel knihovnu Xchart. Je to jednoduchá knihovna pro zobrazování dat do grafů všech druhů:
* Sloupcové grafy
* Čárové grafy
* Výsečové grafy 
* Pruhové grafy
* Plošné grafy
* Bodové grafy
* Kombinované grafy

K zobrazování je potřeba mít data uložená v Listech doublů nebo intů (osa X,Y). 
Nejvíce jsem zde využil spojených bodových grafů, které jsou v některých případech vykresleny přes sebe, oddělené barvami.

Jednotlivé grafy pak mohou být ukládány jako PNG obrázek s nastavením rozlišení pro lepší externí čtení mimo program.


