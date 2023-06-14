# Report

## (1) Introduzione
_Benvenuti_ nel progetto di *Battaglia Navale!* La nostra **BattleShip** offre un'esperienza di gioco solitario contro un avversario virtuale, il computer. Potete scegliere tra diverse modalità di gioco, inclusi i livelli di difficoltà: `facile`, `medio` e `difficile`. Ogni modalità prevede un numero massimo di tentativi, rispettivamente 50, 30 e 10.

Il gioco si svolge su due mappe di dimensione `10x10`: una mappa dedicata ai colpi e un'altra alle navi. Le celle sono identificate da coordinate che combinano **lettere** per le colonne e **numeri** per le righe. Ad esempio, la cella `F-3` si riferisce alla colonna F e alla riga 3.

Le navi vengono posizionate automaticamente utilizzando un algoritmo, rimanendo nascoste al giocatore. È possibile che due o più navi si tocchino, ma non possono sovrapporsi. Le navi occupano un numero specifico di celle in posizione verticale o orizzontale, senza diagonali.

Ci sono quattro tipi di navi con diverse dimensioni: `Cacciatorpediniere`, `Incrociatore`, `Corazzata` e `Portaerei`. Il vostro obiettivo è distruggere le navi nemiche seguendo queste quantità: `4` cacciatorpediniere, `3` incrociatori, `2` corazzate e `1` portaerei.

**Dopo aver posizionato le navi, il gioco inizia**. Ogni turno, il giocatore sceglie una cella da colpire, come ad esempio `A-8` per la prima colonna e l'ottava riga. All'inizio, la mappa dei colpi mostra un simbolo che indica che _nessuna cella è stata ancora colpita_.

Dopo ogni colpo, la mappa dei colpi viene aggiornata in base all'esito dell'attacco. Una `X` viene visualizzata se viene colpito un pezzo di nave, mentre il simbolo `~` indica l'acqua, ovvero una cella senza nave.

La _partita termina_ quando *tutte* le navi nemiche sono state affondate o quando si esauriscono i tentativi consentiti dal livello di difficoltà.

_Durante la partita, è possibile abbandonare il gioco in qualsiasi momento_. Inoltre, è possibile richiedere di rivelare la griglia del computer con le navi posizionate per avere una visione strategica della disposizione delle navi nemiche.

**Siete pronti per la sfida? Immergetevi in questa battaglia navale epica e dimostrate la vostra abilità strategica per conquistare il mare!**
## (2) Modello di Dominio

### diagramma
```mermaid
    
    classDiagram
        direction LR
            
        Giocatore "1" -- "0..*" Tentativo : Effettua
        Giocatore "1" -- "0..*" Partita : Svolge
        Giocatore "0..*" -- "1" Difficolta : Imposta

        Difficolta "1" -- "0..*" Partita : Influenza
        Difficolta <|-- Facile
        Difficolta <|-- Medio
        Difficolta <|-- Difficile 

        Nave <|-- Cacciatorpediniere
        Nave <|-- Incrociatore
        Nave <|-- Corazzata
        Nave <|-- Portaerea
        
        Tentativo <|-- Acqua
        Tentativo <|-- Colpo
        Tentativo <|-- Affondamento
        Tentativo "0..*" -- "1" Partita : Appartiene
        Tentativo "0..1" -- "1" Cella : Colpisce

        Griglia *-- "*" Cella
        Partita "1" -- "1" Griglia : Genera
        Cella "0..*" -- "0..1" Nave : è occupata da
        Colpo "0..*" -- "1" Nave : è subito da
        Affondamento "0..1" -- "1" Nave : è subito da


        class Nave{
            dimensione
        }

        class Difficolta{
            maxFallibili
        }

        class Giocatore{
            nome
        }

        class Partita{
            numFalliti
        }

        class Cella{
            coordinate
        }

        class Griglia{
            dimensione
        }


```
### Glossario degli attributi
|Attributo|Concetto di appartenenza|Descrizione|
|-|-|-|
| coordinate | Cella | coppia che individua univocamente una Cella di una Griglia. |
| dimensione | Griglia | Numero di righe e di celle per riga. Per esempio, `dimensione=10` implica $10^2$ celle. |
| dimensione | Nave | Numero di celle che la nave occupa. |
| maxFallibili | Difficoltà | Numero massimo di tentativi fallibili. |
| nome | Giocatore | Nome del giocatore |
| numFalliti | Partita| Numero corrente di tentativi falliti; Si ottiene contando il numero di istanze di Acqua relative alla Partita. |

### Note
- (Definizione) Tra *Griglia* e *Cella* vi è una **composizione**:
    - Una *Griglia* è composta da più *Celle* (vedi molteplicità `*` nel diagramma)
    - **dipendenza esistenziale**: Una *Cella* esiste solo se parte di una *Griglia*
    - **esclusività**: Una *Cella* può appartenere ad una e una sola *Griglia*
- (Notazione) Si dice fallito un *Tentativo* di tipo *Acqua*.


## (3) Requisiti Specifici
### (3.1) Requisiti funzionali
- (RF1) Il sistema deve permettere di iniziare una nuova partita.
  - (RF1.1) Al comando `/gioca` se nessuna partita è in corso l'applicazione imposta casualmente le navi, mostra la griglia vuota e si predispone a ricevere il primo tentativo o altri comandi.
- (RF2) Il sistema deve permettere di chiudere il gioco.
  - (RF1.2) Al comando `/esci` l'applicazione chiede conferma. Se la conferma è positiva, l'applicazione si chiude resitutendo il controllo al sistema operativo altrimenti si predispone a ricevere nuovi tentativi o comandi.
- (RF3) Il sistema deve permettere al giocatore di abbandonare una partita in corso.
   - (RF3.1) Al comando `/abbandona` l'applicazione chiede conferma. Se la conferma e è positiva, l'applicazione risponde visualizzando sulla griglia la posizione di tutte le navi e si predispone a ricevere nuovi comandi, altrimenti si predispone a ricevere nuovi tentativi o comandi.
- (RF3) Il sistema deve permettere di impostare, prima dell'avvio della partita, la difficoltà di gioco tra _FACILE_, _MEDIO_ e _DIFFICILE_. 
  - (RF3.1) Al comando `/facile` l'applicazione risponde con "OK" e imposta la difficoltà a FACILE.
  - (RF3.2) Al comando `/medio` l'applicazione risponde con "OK" e imposta la difficoltà a MEDIO.
  - (RF3.3) Al comando `/difficile` l'applicazione risponde con "OK" e imposta la difficoltà a DIFFICILE.
- (RF4) Il sistema, in base alla difficoltà impostata deve impostare il numero massimo di tentativi fallibili.
  - (RF4.1) Se la difficoltà è _FACILE_ il numero di default massimo di tentativi fallibili è 50.
  - (RF4.2) Se la difficoltà è _MEDIO_ il numero di default massimo di tentativi fallibili è 30.
  - (RF4.3) Se la difficoltà è _DIFFICILE_ il numero di default massimo di tentativi fallibili è 10.
- (RF5) Il sistema deve peremttere di impostare, prima dell'avvio della partita, il numero massimo di tentativi falliti per ogni difficoltà. 
   - (RF5.1) Al comando `/facile <numero>` l'applicazione risponde con "OK" e imposta a numero il numero massimo di tentativi fallibili per FACILE.
   - (RF5.2) Al comando `/medio <numero>` l'applicazione risponde con "OK" e imposta a numero il numero massimo di tentativi fallibili per MEDIO.
   - (RF5.3) Al comando `/difficile <numero>` l'applicazione risponde con "OK" e imposta a numero il numero massimo di tentativi fallibili per DIFFICILE. 
- (RF6) Il sistema deve permettere, prima dell'avvio della partita, di impostare direttamente il numero massimo di tentativi bypassando le difficoltà disponibili.
   - (RF6.1) Al comando `/tentativi <numero>` l'appliczione risponde con "OK" e imposta a numero il numero massimo di tentativi fallibili.
- (RF7) Il sistema deve permetter di mostrare il numero di tentativi già effettuati e il numero di tentativi falliti.
   - (RF7) Al comando `/mostratentativi` l'applicazione risponde visualizzando il numero di tentativi già effettuati, il numero di tentativi falliti e il numero massimo di tentativi falliti.
- (RF8) Il sistema deve permettere di mostrare il livello di difficoltà in uso e il numero massimo di tentativi falliti.
   - (RF8.1) Al comando `/mostralivello` l'applicazione risponde visualizzando il livello di difficoltà in uso e il numero massimo di tentativi falliti.
- (RF9) Il sistema deve permettere di impostare, prima dell'avvio del gioco, la taglia della griglia. In particolare:
   - (RF9.1) Al comando `/standard` l'applicazione risponde con "OK" e imposta 10x10 la dimensione della griglia. Nel caso in cui il giocatore decide di avviare la partita senza impostre la griglia, per default verrà scelto il caso standard (10x10).
   - (RF9.2) Al comando `/large` l'applicazione risponde con "OK" e imposta a 18x18 la dimensione della griglia.
   - (RF9.3) Al comando `/extralarge` l'applicazione risponde con "OK" e imposta a 26x26 la dimensione della griglia.
- (RF10) Il sistema deve permettere di mostrare i tipi di nave e il numero.
   - (RF10.1) Al comando `/mostranavi` l'applicazione risponde visualizzando, per ogni tipo di nave, la dimensione e il numero di esemplari da affondare.
- (RF11) Il sistema deve peremttere di svelare la griglia con le navi posizionate.
   - (RF11.1) Al comando `/svelagriglia` l'applicazione risponde visualizzando la griglia, con le righe numerate in numeri e le colonne in lettere, e tutte le navi posizionate.
- (RF12) Il sistema deve permettere di mostare la griglia con le navi colpite e affondate.
   - (RF12.1) Al comando `/mostragriglia` l'applicazione risponde visualizzando, una griglia con le righe numerate a partire da 1 e le colonen numerate a partire da A, con le navi affondate e le sole parti già colpite delle navi non affondate.
- (RF13) Il sistema deve permettere di impostare, prima dell'avvio della partita, il tempo di gioco. In particolare:
   - (RF13.1) Al comando `/tempo numero` l'applicazione risponde con "OK" e imposta a numero il numero di minuti a disposizione per giocare.
- (RF14) Il sistema deve permettere di mostrare il numero di minuti trascorsi nel gioco e il numero di minuti ancora disponibili.
  - (RF14.1) Al comando `/mostratempo` l'applicazione risponde visualizzando il numero di minuti trascorsi nel gioco e il numero di minuti ancora disponibili.
- (RF15) Il sistema deve permettere di effettuare un tentativi per colpire una nave.
   - (RF15.1) Digitando una coppia di caratteri separati da un trattino, corrispondenti rispettivamente  alla _lettera della colonna_ e al _numero di riga_ (es: `B-4`), l'applicazione risponde con
       - "**acqua**" se sulla cella non è posizionata nessuna nave.
       - "**colpito**" se sulla cella è posizionata una nave non affondata.
       - "**colpito e affondato**" se sulla cella è posizionata una nave ed è l'ultima cella non colpita occupata da essa.
   - (RF15.2) Qualunque sia l'esito del tentativo, l'applicazione deve mostrare la griglia con le navi colpite parzialmente o affondate, il numero di tentativi già effettuati e il tempo trascorso dall'inizio della partita.
   - (RF15.3) La partita termina con successo se il tentativo ha affondato l'ultima nave.
   - (RF15.4) La parita termina con insuccesso se è stato raggiunto il numero massimo di tentativi falliti o se è scaduto il tempo di gioco.
- (RF16) Il sistema deve permettere di elencare i comandi leciti per interagire con esso. I comandi del gioco sono:
  - (RF16.1) `/help`: mostra l'elenco dei comandi leciti. Il sistema deve mostrare l'elenco dei comandi leciti.
  - (RF16.2) `/gioca`: avvia una nuova partita.
  - (RF16.3) `/esci`: chiude l'applicazione.
  - (RF16.4) `/abbandona` : esce dala partita in corso.
  - (RF16.5) `/facile`: imposta la difficoltà di gioco a _FACILE_.
  - (RF16.6) `/medio`: imposta la difficoltà di gioco a _MEDIO_.
  - (RF16.7) `/difficile`: imposta la difficoltà di gioco a _DIFFICILE_.
  - (RF16.8) `/facile numero` : imposta a numero il numero massimo di tentativi fallibili per _FACILE_.
  - (RF16.9) `/medio numero` : imposta a numero il numero massimo di tentativi fallibili per _MEDIO_.
  - (RF16.10) `/difficile numero` : imposta a numero il numero massimo di tentativi fallibili per _DIFFICILE_.
  - (RF16.11) `/tentativi numero` : imposta a numero il numero di tentativi fallibili.
  - (RF16.12) `/mostratentativi` : visualizza il numero di tentativi già effettuati, quelli falliti e il numero massimo di quelli fallibili.
  - (RF16.13) `/mostralivello` : visualizza il livello di difficoltà selezionato e il numero massimo di tentativili falliti.
  - (RF16.14) `/standard` : imposta la griglia a **10x10**.
  - (RF16.15) `/large` : imposta la griglia a **18x18**.
  - (RF16.16) `/extralarge` : imposta la griglia a **26x26**.
  - (RF16.17) `/mostranavi`: visualizza per ogni tipo di nave il nome, la dimensione in quadratini e il numero di esemplari da affondare.
  - (RF16.18) `/svelagriglia`: svela la posizione delle navi nella griglia della partita in corso.
  - (RF16.19) `/mostragriglia` : mostra una griglia con le sole navi affondate o in parte colpite.
  - (RF16.20) `/tempo numero`: imposta a numero il numero di minuti a disposizione per giocare.
  - (RF16.21) `/mostratempo` : visualizza il numero di minuti trascorso dall'avvio della partita e il numero di minuti ancora disponibili.
  - (RF16.22) `COL-ROW` : lancia un colpo in posizione riga `ROW` e colonna `COL`.
### (3.2) Requisiti non funzionali
- (RNF1) Il container docker dell'app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 e UTF-16.
  - (RNF1.1) Per linux e macos si consiglia di utilizzare il terminale di default.
  - (RNF1.2) Per Windows si consiglia di utilizzare Powershell o il terminale di git bash.
- (RNF2) Per eseguire il container docker dell'app è necessario:
  - (RNF2.1) Avere installato docker sul proprio sistema operativo.
  - (RNF2.2) Eseguire il comando `docker pull ghcr.io/softeng2223-inf-uniba/battleship-thacker:latest`.
  - (RNF2.3) Eseguire il container docker con il comando `docker run --rm -it ghcr.io/softeng2223-inf-uniba/battleship-thacker:latest`.
- (RNF3) Il sistema deve essere sviluppato in Java utilizzando la JDK 19.

## (7) Manuale Utente

### Introduzione 

La Battaglia Navale è un gioco da tavolo strategico in cui due giocatori si sfidano cercando di affondare le navi nemiche posizionate su una griglia. Tradizionalmente, il gioco viene giocato da due persone, ma in questa versione avrai il computer come avversario.

### Scopo del gioco 

L'obiettivo del gioco è quello di riuscire ad affondare tutte le navi avversarie prima che i tentativi disponibili finiscano. 

Se si decide di impostare il timer, è necessario raggiungere l'obiettivo prima che il tempo scada. 

Per assicurarsi che il programma funzioni correttamente su qualsiasi macchina, evitando problemi di compatibilità, è consigliabile installare Docker.

### Installazione Docker

La prima cosa da fare è scaricare il contenuto di uno dei link riportati di seguito, a seconda del vostro sistema operativo: 

<span style="text-align:center">

[Linux](https://docs.docker.com/desktop/linux/install/)

[Apple](https://desktop.docker.com/mac/main/amd64/Docker.dmg?utm_source=docker&utm_medium=webreferral&utm_campaign=dd-smartbutton&utm_location=module)

[Windows](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe?utm_source=docker&utm_medium=webreferral&utm_campaign=dd-smartbutton&utm_location=module)

</span>

Dopodiché, si potrà procedere con l'installazione, come per un normale programma. 

Per gli utenti Windows, una volta installato Docker andrà installato anche il Windows Subsystem Linux. Basterà copiare il comando di seguito in una finestra del terminale. 

```sh
wsl --install + LINUX
```
Dove al posto di LINUX andrà la distribuzione di vostro gradimento. 

Per visualizzare un elenco delle distro Linux supportate da WSL basterà digitare sempre da terminale: 

```sh
wsl --list --online 
```
### Esecuzione immagine Docker 

Una volta assicurato di avere Docker e il WSL (se stai usando Windows) correttamente installati sul tuo sistema, si possono seguire i passaggi qui sotto: 

- Avvia Docker.

- Autentica Docker per l'uso di Github Packages:
- Crea un file di testo chiamato TOKEN.txt e inserisci al suo interno il tuo "personal access token" di Github. Puoi seguire le istruzioni [qui]() per crearlo.

- Apri il terminale e digita il seguente comando, sostituendo <USERNAME> con il tuo username di Github e <PERCORSO> con il percorso completo del file TOKEN.txt:
  ```sh
  cat <PERCORSO>/TOKEN.txt | docker login ghcr.io -u  <USERNAME> --password-stdin
  ```
- Copia l'URL dell'immagine Docker dalla repository di riferimento.
- Nel terminale, esegui il seguente comando, incollando l'URL dell'immagine al posto di <URL_IMMAGINE>:
  ```sh
  docker pull <URL_IMMAGINE>
  ```
  Questo scaricherà l'immagine Docker nel tuo sistema.
- Infine, esegui il comando per avviare l'immagine Docker:
  ```sh
  docker run --rm -it <NOME_IMMAGINE>
  ```
  Sostituisci <NOME_IMMAGINE> con il nome dell'immagine Docker che hai scaricato.

Seguendo questi passaggi, dovresti essere in grado di eseguire correttamente l'immagine Docker senza incontrare problemi.

### Avvio del gioco

Una volta avviata l'immagine docker, potrai scegliere di digitare due comandi: 

```sh
  /help o --help o -h
``` 
```sh 
  /gioca
```

Con il comando `/help` il sul tuo schermo compariranno tutti i comandi di gioco possibli. 

ecco una bfreve descrizione di ognuno: 

- `/gioca` Inizia una nuova partita

- `/facile <numero>` Imposta il numero di tentativi disponibili secondo il valore della variabile `numero`  

- `/medio <numero>` Imposta il numero di tentativi disponibili secondo il valore della variabile `numero`  

- `/difficile <numero>` Imposta il numero di tentativi disponibili secondo il valore della variabile `numero`

- `/tentativi <numero>` Imopsta secondo la variabile `numero`, stabilita dall'utente, il numero massimo di tentativi disponibili

-  `/mostratentativi` Mostra i tentativi ancora disponibili in quel momento

- `/standard` Imposta la dimensione di entrambe griglie di gioco a 10x10 

- `/large` Imposta la dimensione di entrambe griglie di gioco a 18x18 

- `/extralarge` Imposta la dimensione di entrambe griglie di gioco a 26x26

- `/tempo <numero>` Imposta un timer, in minuti, secondo il valore di `numero` che l'applicazione chiederà al giocatore. Una volta impostato, la partita in corso o quella che è in procintio di partire sarà a tempo

- `/mostratempo` mostra i minuti rimanenti per il completamento della partita e queeli trascorsi 

- `/mostralivello` Mostra il livello di difficoltà durante la partita.

- `/mostragriglia` Mostra a schermo la griglia con i colpi andati a buon fine, nel senso che hanno colpito una nave avversaria

- `/svelagriglia` Mostra la griglia dell'avversario

- `/mostranavi` Mostra le informazioni sulle navi, inclusi nomi, rappresentazione e quantità disponibili

- `/esci` Termina il programma se arriva una conferma dall'utente

- `/abbandona` Se il giocatore conferma, l'app mostrerà la posizione delle navi nemiche e aspetterà un comando. Altrimenti, si preparerà per un nuovo tentativo di lancio.


Con il comando `/gioca`, invece, ti sarà possibile iniziare una nuova partita. 

Le navi saranno posizionate in maniera **automatica** in una griglia dalla dimensione che hai scelto in precedenza, senza che tu faccia alcuna azione. 


### Sistema dei colpi

Potrai lanciare un colpo sulla griglia delle navi avversaria semplicemente digitando nella console la coordinata della cella, secondo una sintassi del tipo `<LETTERA>-<NUMERO>`.

A seconda della situazione, si verificherò uno dei seguenti tre scenarti:

- Il colpo non va segno; quindi, a schermo comparirà la scritta **acqua**
- Il colpo intercetta la parte di una nave ma non l'affonda; allora a schermo apparirà la scritta **colpito**
- Il colpo intercetta una nave e la affonda; allora l'applicazione mostrerà a schermo la parola **colpito e affondato**

In ognuno di questi tre casi, verranno mostrate anche la griglia delle navi avversaria, con tutte le navi colpite o affondate, assieme al numero di tentativi effettuati e il tempo trascorso.

### Operazioni di fine partita 

Al termine di una partita, l'applicazione ritornerà allo stato iniziale, quindi tutte le impostazioni che avrai personalizzato verrano re-impostate a default, come: 

- il timer ad infinito
- la dimensione della griglia a 10x10
- la difficoltà a facile 
- il numero massimo di tentativi falliti, di ogni livello di difficoltà, ai rispettivi valori predefiniti

### :magic_wand: Tips and tricks  

#### Il tempo è un fattore importante

Se stai giocando per la prima volta, ti suggeriamo di non mettere un tempo limite troppo breve, in modo da poterti abituare alle regole del gioco. 

Man mano che migliori, potrai sfidarti a completare le partite sempre più velocemente.

#### Obiettivo: distruzione totale

Una volta colpita una nave, è bene fare le mosse giuste per affondarla subito. 

Il segreto potrebbe essere quello di continuare a mirare le caselle che sono a ridosso di quella colpita: continuate senza demordere, prima o poi affonderete le navi dell’avversario.

#### Focus sul centro

Le statistiche parlano chiaro: le probabilità di beccare una nave aumentano se si colpisce la parte centrale del tabellone quindi partire proprio da lì potrebbe essere un’ottima idea.

## (9) Analisi Retrospettiva
All'interno di questa sezione si trovano le relazioni dei vari Sprint che verranno eseguiti durante lo sviluppo del progetto.
### (9.1) Sprint 0
![RetrospettivaSprint0](img/RetrospettivaSprint0.png "Lavagna retrospettiva Sprint 0")
Relazione del meeting del gruppo Thacker riguardo ciò che è accaduto durante lo Sprint 0.
Meeting avvenuto il giorno 14/04/2023
**Partecipanti al meeting:**
- Federico Armagno
- Annarita Bruno
- Vito Stefano Birardi
- Alessandro Carli
- Giovanni Cirigliano
- Antonio Dambra
- Giuseppe D'Urso
  
**Relazione:**
Con lo Sprint 0 il gruppo Thacker ha avuto il primo approccio con le varie dinamiche della piattaforma GitHub ottenendo diversi riscontri.

In questo meeting abbiamo affrontato diversi punti chiave riguardanti la pianificazione, la gestione delle emozioni, le modifiche dell'ultimo minuto e le esperienze individuali all'interno del gruppo. Di seguito, riassumo i principali argomenti discussi.

- Giovanni Cirigliano ha sostenuto la necessità di dedicare più tempo alla fase di pianificazione nei prossimi sprint. Ha evidenziato che una pianificazione approfondita è fondamentale per garantire una maggiore chiarezza sulle attività e sui tempi di consegna. La sua proposta è stata accolta positivamente dal resto del team, che ha riconosciuto l'importanza di una pianificazione solida per il successo del progetto.

- Antonio Dambra ha espresso la sua insoddisfazione riguardo alla gestione delle emozioni in alcune circostanze. Ha evidenziato la necessità di mantenere la calma anche durante situazioni stressanti o conflittuali. Il team ha riconosciuto l'importanza di gestire le emozioni in modo efficace.

- Annarita Bruno ha condiviso la sua insoddisfazione riguardo al fatto che, a causa del poco tempo a disposizione, si è dovuto affrontare un numero elevato di modifiche dell'ultimo minuto. Ha evidenziato come ciò abbia comportato un carico di lavoro aggiuntivo e rallentato il progresso generale del progetto. Il team ha riconosciuto l'importanza di una pianificazione accurata per ridurre le modifiche dell'ultimo minuto e ha discusso di strategie per migliorare la gestione del tempo e delle attività.

- Antonio Dambra si è mostrato entusiasta di lavorare in gruppo, definendolo un'esperienza potenzialmente esaltante. Ha sottolineato l'importanza di creare un ambiente di lavoro positivo e collaborativo, in cui i membri del team si sentano motivati e valorizzati.

- Federico Armagno ha ammesso che spesso,nei primi giorni di lavoro,ha avuto delle sviste per quanto riguarda lo spostare le issue nei vari stati (To Do, In progress, ecc...). Il team ha riconosciuto l'importanza di mantenere traccia dello stato delle attività per una migliore fluidità ed organizzazione del lavoro.

- Giuseppe D'Urso ha sostenuto che lo sprint 0 è stata un'esperienza utile per familiarizzare con le dinamiche di GitHub. Il team ha condiviso l'importanza di acquisire confidenza con gli strumenti utilizzati nel progetto e ha apprezzato l'impegno di Giuseppe nel comprendere l'ambiente di GitHub.

- Alessandro Carli ha espresso qualche disagio iniziale riguardo al meccanismo di creazione dei branch su GitHub durante questo sprint. Tuttavia, si è dimostrato soddisfatto della collaborazione con il collega Giuseppe D'Urso per la redazione del documento `ISPIRATORE.md`, riconoscendo una perfetta suddivisione del lavoro e una collaborazione efficace.

- Vito Stefano Birardi ha espresso la sua insoddisfazione riguardo la procedura di creazione delle issue nella propria repository GitHub. Il team ha preso atto della sua preoccupazione e si è impegnato a fornire un supporto adeguato per migliorare la procedura e rendere più agevole la creazione delle issue.

In conclusione, il meeting del team ha offerto l'opportunità di affrontare diverse questioni critiche per migliorare la pianificazione, la gestione delle emozioni e la comunicazione interna. Il team ha dimostrato un'impegno comune nel lavorare insieme per affrontare queste sfide e ha apprezzato le esperienze positive di collaborazione emerse durante lo sprint 0.
