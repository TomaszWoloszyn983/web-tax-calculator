package com.tomasz.taxcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;


@SpringBootApplication
public class TaxCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxCalculatorApplication.class, args);
	}

	@Bean
	Validator validator(){
		return new LocalValidatorFactoryBean();
	}

}
/*
Stworzyłem klasę person oraz PersonDataRepository
próbuję w Postmanie wysłac request post perosn ale zwraca mi
bład 500.
Trzeba było wywaliś format UUID, którego używałem do idików, chyba Hybernate go nie obsługuje.
 */

/*
Zrobiliśmy taką dość skomplikowaną dla mnie operację:
\Utworzyliśmy nowy inteface PersonDataRepository oraz zmienilismy nazwę PersonDataRepository
 na SqlPersoDataRepository nastęnie wszystkie metody z tego drugiego przenieśliśmy do nowego intefacu
 Chodziło chyba o to zbyśmy abyśmy w sql udostępniali publiocznie tylko te metody które są publiczne
 w konkretnym intrfacie oraz udostępniali pakietowo tylko te które chcemy aby były udostępnione pakietowo.
 Tym sposobem rozdzielamy dostępność do różncyh mentod.
 */

/*
			Podłączanie bazy danych H2
W pliku application.properties dodaliśmy parę ustawień, które to pobralismy z dokumantacji Spring Boota.
Aktywowaliśmy konsole H2 oraz dodaliśmy ściezke do niej. Następnie dodaliśmy mozliwość zapisywania
naszej bazy danych w pliku na dysku (miałę tutaj probelem z podaniem akceptowanej przez Springa ściezki,
ale jdbc:h2:file:./person-db okazało się wystarczające).
Aby dostać się do bazy z konsoli, po przeniesieniu bazy danych do pliku trzeba zerknąć na odres bazy
który pojawia się w logach po odpalieniu apki i zapisać go mniej więcej w ten sposób jdbc:h2:file:./person-db
oraz usunąc nazwę urzytownika oraz hasło.
 */

/*
Podmienilismy plik application.propoerties na application.yml, który to format oferuje bardziej
elastyczne i czytelne podejsćie do zapisywania włąściwości. W lekcji zostało to zrobione poprzez
refaktoryzację, mi to jednak nie działało dobrze dlatego utworzyłem zupełnie nowy plik yml i wkleiłem
do niego kodd ze starego pliku.
DOdatkowo zniknęła mi cała tabela PERSON  z bazy danych (zapewne dlatego ze miałem nie wiem skąd zapis
ddl-auto: drop). Po zamienieniu go na dll-auto: update tabela się znowu poajwiła
 */

/*
Dalszy ciąg zmadania sie z tymi bazami danych.
Dodaliśmy zalezność flyway. Aby dziłała trzeba dodać nowy folder db/migration i utworzyć jakąś mogrację.
Miałem z tym jakieś problemy, ale ogólnie utworzone za pomocą migracji flyway tabele można odczytywać też
za pomoca konsoli H2, tylko trzeba uważać bo ja dodałem nową rubrykę inRelatio typu bit i poajwiły mi się
dwie rubryki inRelation. Jeden to chyba boolean z klasy Person, drugi to bit z flywaya.
Ogólnie zmienianie czegokolwiek w migracjach powinno odbywwać się poprzez trorzenie nowych migracji
zamiast, bo grzebanie i zmienianie może powodowac że nie będzie zgadzałą się flywayowi checksuma i się
wywali.
 */

/*
Dodoaliśmy parę zmiennych do klasy Person (createdOn, updatedOn) aby przechowywac dodatkowe
informacje o dodaniu i zupdateowaniu jakiegoś profilu.
Wydrębnilismy te dwie zmienne do oddzielnej klasy Audit, dzięki temu mamy mniej
bałąganu w klasie Person i jednoczesnie to jedna klasa Audit będzie zaimowała sie
rzeczami związanymi z audytem.
Klasę Audit oznacznyliśmy adnotacją Embedable oraz za pomoca adnotacji @Embedded
zprawiliśmy że będzie automatycznie wczytywana przez klase Person.
Dodatkowo za pomoca @AttributeOverrides({
										 @AttributeOverride(column = @Column(name = "createdOn",
										 									name = "updatedOn")
										)
możemy uzyskać dostep do pojedyńczych zmiennych znajdujących sie w innej klasie i będacych oznaczone jako
@Embedded.
 */

/*
Hybernate umozliwia zarządznie relacyjnymi bazami danych z poziomu aplikacji. Przykład tego mieliśmy z aplikacji
z kursu gdzie nasze taski były przypisywane do konkretnych grup zadań.
Więcej na ten temat znajdziemy w 74 lekcji z kursu.
Takie relacje między tabelami mogą mieć wiele zastosowań jak na przykład w przypadku
gdy mamy jakieś produkty w sklepie, które chcemy podrupowac na kategorie, czy pracownicy w firmie,
których możemy chcieć pogrupować według działów na których pracują wraz z mozliwościa dodawania
kolejnych działow lub zamykania ich gdy sa puste itp.
 */