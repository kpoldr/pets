# Pet App docs

## Projekti üleseehitus

### JPA

Projekti backend on ülesse ehitatud spring booti ja JPA-ga. Otsustasin kasutada JPA-d, sest olen ainult JDBC projekte eelnevalt teinud.

### Model

*Appi* model koosneb kuuest olendist. Võimalik oleks ka terve projekt teha ainult kahe tabelida, kuid jagasin kõik looma atribuudid laiali (ja tekitasin rollid). See annab võimaluse otsida loomi ainult vastavate atribuutite järgi samuti lihtsutab ka frontendi lisamist.

### Repository, service

JPA-ga *repository* mustri loomine ei olnud väga keeruline. Igal olendil on oma *repository*, kuid igal olendil pole oma *servicet*. Programmis on kaks servicet **UserService** ja **PetService**, mis võtavad kõik *repositoryd* kokku. On olemas ka kolmas **PetExtraService**, milles on võimalik luua uusi looma atribuute, kuid see ei jõudnud ülesandesse.

### Controller

 Controllerid enamasti kasutavad lihtsalt *servicete* funktsioone. 
 Nendes toimub andmete valideerimine, kuid peale selle nad saadavad enamasti info *servicetesse*. Looma *controllerid* vaatavad, et muudetav olend oleks sama kasutajaga, mis on JWT-s. See on selleks, et kasutaja saaks ainult enda andmeid muuta.
 
### Spring security

Kasutajate loomiseks kasutasin *spring securitit*. See tõenäoliselt on kõige keerulisem *backendi* osa, kuid internetis on VÄGA palju õpetusi selle jaoks. Kõige suuremaks probleemiks oli spring booti üleminemine 6.0 versioonile, kus nad otsustasid palju asju *deprecateda*. Seega korrektse versiooni saamiseks pidin palju "tõlkimist" tegema.

*Spring securitiga* sain luua funktsionaalse kasutaja süsteemi. Igal kasutajal on omad loomad ja JWT tokeni tõttu ei saa üks kasutaja teise loomi mõjutada. Ma ignoreerin kasutaja sisselogimisel (ja registreerimisel) autentimist, et sisselogimine oleks võimalik. 

### Angular

Ma pole kunagi enne Angulari kasutanud. Mul on väike kogemus Reacti ja Vue-ga, mis aitasid mul ülesannet lahendada, kuid ma kardan, et mul on veel mitmeid probleeme programmis. Peale apiga suhtlemisega välja nuputamist läks asi lihtsamaks, kuid mul on ikka paar asja, mis ei saanud lahendust.

Frontend koosneb neljast erinevast peakomponendist. Sisselogimine, kodu (loomade tabeli vaade), looma lisamine, loomade muutmine. On olemas ka lisavaated 404 ja PetNotFound 

### Puudused

Vähene angulari kogemus jättis ikkagi paar probleemi, mida ma ei suutnud lahendada. 

Frontendil puudub *store* - Kuigi võibolla ei ole see vajalik, tahtsin ma lisada programmile viisi andmeid paremini hoida.

JWT ei refreshi korrektselt tokenit (frontendis) - JWT refresh tehniliselt töötab, kuid ma ei teadnud kuidas seda korrektselt rakendada. Praegune lahendus saadab kasutaja sisselogimise vaatesse tagasi (kuigi jwt refreshib). Seega pikendasin *access tokeni* pikkust 20 minuti peale.

Tabeleid ei saa sorteerida - Ma proovisin lahendada, kuid ei jõudnud ajaliselt selleni.

Swagger - Oleks parem ülevaade controlleritest

## Muu

### Aeg

Ajalist pikkuse andmine on natuke keeruline. Jõulud ja paar muud tegemist sõid suure osa ajast ära. Ma tegelsin sellega kuuel päeval, aga täpsemat tundide arv varjeerus päevast päeva. Enamik ajast kulus õppimisele. 

### Õppimine ja Lahendused

Otseselt ühte allikat ei olnud õppimiseks. Probleemidele leidsin lahendust nii YouTubest kui ka Stack Overflowst. Ma proovisin ka kasutada ChatGPTd. See ei olnud nii hea koodi loomiseks, kuid selle võime koodi seletada, aitas mõnest asjast paremini aru saada. 













