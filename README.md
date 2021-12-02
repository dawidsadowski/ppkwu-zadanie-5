# vCard API dla Panoramy Firm

API służy do wyświetlania danych na temat firm oraz umożliwia generowanie [vCard](https://pl.wikipedia.org/wiki/VCard) 
(elektronicznych wizytówek).

## Użycie

Aby skorzystać z API, należy wysłać żądanie GET pod wybrany endpoint podając frazę (np. `hydraulik`) opisującą pracę do
wyszukania w serwisie [Panorama Firm](https://panoramafirm.pl/).

```
ścieżka_do_api/{tekst}
```

## Przykład
Dla danego zapytania:
```
http://localhost:8080/api/elektryk
```
Otrzymamy listę wyników:
![Lista wyników](https://i.imgur.com/MZwwVhy.jpeg =250x)

Po kliknięciu w przycisk `Generuj vCard`, pobierze nam się wygenerowany kontakt, który można dodać do książki telefonicznej:
![Lista wyników](https://i.imgur.com/eQguS1V.jpeg =250x)