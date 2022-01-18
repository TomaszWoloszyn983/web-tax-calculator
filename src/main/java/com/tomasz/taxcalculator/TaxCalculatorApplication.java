package com.tomasz.taxcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
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