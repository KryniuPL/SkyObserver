import { Component, OnInit } from '@angular/core';
import { Currency } from '../../model/Currency';
import { Language } from '../../model/Language';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  languages: Language[] = [];
  currencies: Currency[] = [];

  constructor() { }

  ngOnInit() {
    this.loadCurrencies();
    this.loadLanguages();
  }

  loadCurrencies(){
    this.currencies.push(new Currency('EUR - €',''));
    this.currencies.push(new Currency('GBP - £',''));
    this.currencies.push(new Currency('USD - $',''))
    this.currencies.push(new Currency('PLN - ZŁ',''))
  }

  loadLanguages(){
    this.languages.push(new Language('English',''));
    this.languages.push(new Language('Polski',''));
    this.languages.push(new Language('Deutsch',''));
  }


}

