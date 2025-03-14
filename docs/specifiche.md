# Specifiche

**Titolo dell'esercizio:**
Sistema di Gestione di un Festival Culturale

**Obiettivo:**  
Realizzare un’applicazione web completa per la gestione di un festival culturale, che consenta agli utenti di consultare eventi, effettuare prenotazioni e lasciare recensioni. L’applicazione deve includere un’area dedicata agli amministratori per il controllo totale del sistema.

**Tecnologie richieste:**  
-**Spring Boot**  
-**Spring Web**  
-**Spring Data JPA**  
-**Thymeleaf**  
-**Spring Security**  
-**MySQL**  
-**Lombok**  
-**Dev Tools**

**Specifiche funzionali e requisiti tecnici:**  

1. **Struttura del progetto e best practices:**  
   - Organizzare il progetto seguendo una struttura modulare e aderendo alle best practices di Spring Boot.  
   - Utilizzare Lombok per semplificare la scrittura di getter, setter, costruttori ed eventuali metodi utilitari.

2. **Modelli e relazioni:**  
   Realizzare almeno le seguenti entità, con relazioni definite in maniera esplicita:  
   - **Utente:**  
     - Campi: id, nome, email, password, ruolo (es. USER, ADMIN).  
     - Relazioni: un utente può avere più prenotazioni e recensioni.
   - **Evento:**  
     - Campi: id, titolo, descrizione, data, orario, prezzo.  
     - Relazioni:  
       - Ogni evento si svolge in una sede (Many-to-One).  
       - Un evento può avere più prenotazioni e recensioni.  
       - (Opzionale) Relazione Many-to-Many con un’entità “Artista” per associare più artisti a un evento.
   - **Sede:**  
     - Campi: id, nome, indirizzo, capienza.  
     - Relazioni: una sede può ospitare più eventi.
   - **Prenotazione:**  
     - Campi: id, dataPrenotazione, numeroBiglietti.  
     - Relazioni: ogni prenotazione è collegata a un utente e a un evento.
   - **Recensione:**  
     - Campi: id, valutazione, commento.  
     - Relazioni: ogni recensione è associata a un utente e a un evento.

   *Nota:* È consigliabile realizzare un diagramma ER per visualizzare e pianificare le relazioni tra le entità.

3. **Funzionalità principali:**  
   - **Registrazione e Login:**  
     - Implementare la gestione degli utenti con Spring Security.  
     - Prevedere la possibilità di registrarsi come utente e assegnare automaticamente il ruolo USER.  
   - **Dashboard Utente:**  
     - Creare pagine per la consultazione degli eventi, la gestione delle prenotazioni e la pubblicazione di recensioni.  
     - Consentire agli utenti di visualizzare lo storico delle proprie prenotazioni e recensioni.
   - **Area Amministrativa:**  
     - Implementare un pannello di controllo accessibile esclusivamente agli amministratori (ruolo ADMIN).  
     - L’admin deve poter gestire tutte le entità: creare, modificare ed eliminare eventi, sedi, prenotazioni e recensioni.  
   - **Query avanzate:**  
     - Oltre alle operazioni CRUD di base, sviluppare metodi di query personalizzati.  
     - Esempi di query complesse:  
       - Ricerca di eventi filtrati per data, sede o artista.  
       - Calcolo di statistiche (es. numero totale di prenotazioni per evento, valutazione media delle recensioni per evento).  
       - Query che combinano più criteri di ricerca per ottenere informazioni aggregate.

4. **Persistenza e gestione dei dati:**  
   - Utilizzare Spring Data JPA per l’accesso ai dati, con MySQL come database di persistenza.  
   - Configurare correttamente le proprietà di connessione e sfruttare Dev Tools per agevolare il processo di sviluppo e testing.

5. **Interfaccia utente:**  
   - Utilizzare Thymeleaf per il rendering delle pagine web.  
   - Progettare interfacce chiare e funzionali, differenziando le viste per utenti normali e amministratori.  
   - Prestare attenzione alla sicurezza delle pagine, in particolare per l’area riservata agli admin.

6. **Documentazione e consegna:**  
   - Accompagnare il progetto con una documentazione (README) che descriva:  
     - La struttura del progetto e il diagramma ER delle entità.  
     - Le funzionalità implementate e le modalità di esecuzione dell’applicazione.  
     - Eventuali considerazioni sul design e sulle query complesse sviluppate.  
   - Il codice dovrà essere pubblicato su un repository Git, garantendo una struttura uniforme che faciliti la valutazione comparativa tra i progetti degli studenti.

**Conclusioni:**  
L’esercizio è finalizzato a verificare la capacità di sviluppare un’applicazione web completa utilizzando il framework Spring Boot, integrando sicurezza, gestione di relazioni complesse tra entità e query personalizzate.

**Titolo dell'esercizio:**  
Sistema di Prenotazione Eventi - Versione Semplificata

**Obiettivo:**  
Realizzare un’applicazione web per la gestione degli eventi che consenta agli utenti di visualizzare gli eventi disponibili e prenotare i biglietti, e agli amministratori di gestire eventi e prenotazioni. Il progetto dovrà implementare in maniera semplice, ma completa, l’interazione tra più modelli e l’utilizzo di query personalizzate.

**Tecnologie richieste:**  
-Spring Boot  
-Spring Web  
-Spring Data JPA  
-Thymeleaf  
-Spring Security  
-MySQL  
-Lombok  
-Dev Tools

**Specifiche funzionali e requisiti tecnici:**  

1. **Struttura del progetto:**  
   - Organizzare il codice seguendo una struttura modulare e le best practices di Spring Boot.  
   - Utilizzare Lombok per la generazione automatica di metodi boilerplate (getter, setter, costruttori).

2. **Modelli e relazioni:**  
   Progettare almeno le seguenti entità:
   - **Utente:**  
     - Campi: id, nome, email, password, ruolo (es. USER, ADMIN).  
     - Relazione: un utente può avere più prenotazioni.
   - **Evento:**  
     - Campi: id, titolo, descrizione, data, prezzo.  
     - Relazione: un evento può avere più prenotazioni.
   - **Prenotazione:**  
     - Campi: id, dataPrenotazione, numeroBiglietti.  
     - Relazioni: ogni prenotazione è collegata a un utente e a un evento.

   *Suggerimento:* Elaborare un semplice diagramma ER per visualizzare le relazioni tra le entità.

3. **Funzionalità principali:**  
   - **Registrazione e Login:**  
     - Implementare la gestione degli utenti tramite Spring Security, assicurando l’accesso differenziato per utenti normali e amministratori.  
     - La registrazione dovrà assegnare il ruolo USER per default.
   - **Dashboard Utente:**  
     - Creare pagine per la visualizzazione degli eventi disponibili e per la gestione delle prenotazioni.  
     - Consentire agli utenti di vedere lo storico delle proprie prenotazioni.
   - **Area Amministrativa:**  
     - Implementare un pannello di controllo riservato agli amministratori, dove sarà possibile creare, modificare ed eliminare eventi e visualizzare tutte le prenotazioni.
   - **Query avanzate:**  
     - Oltre alle operazioni CRUD di base, sviluppare metodi di query personalizzati.  
     - Esempi:  
       - Ricerca degli eventi per data o prezzo.  
       - Calcolo del numero totale di prenotazioni per un determinato evento.

4. **Persistenza dei dati:**  
   - Configurare Spring Data JPA per la gestione dei dati, utilizzando MySQL come database.  
   - Sfruttare Dev Tools per facilitare il ciclo di sviluppo e testing.

5. **Interfaccia utente:**  
   - Utilizzare Thymeleaf per il rendering delle pagine, creando layout chiari e distinti per le aree pubblica e amministrativa.  
   - Assicurare che le pagine riservate siano protette e accessibili solo agli utenti autorizzati.

6. **Documentazione e consegna:**  
   - Redigere un file README che illustri la struttura del progetto, il diagramma ER, le funzionalità implementate e le istruzioni per l’esecuzione dell’applicazione.  
   - Il progetto dovrà essere pubblicato su un repository Git con una struttura chiara e uniforme, facilitando così il confronto tra i lavori degli studenti.

**Conclusioni:**  
Questo esercizio ha l’obiettivo di verificare la capacità di realizzare un’applicazione web completa ma in una versione semplificata, dove si evidenzia l’interazione tra più modelli, l’uso di query avanzate e la gestione della sicurezza degli utenti.