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