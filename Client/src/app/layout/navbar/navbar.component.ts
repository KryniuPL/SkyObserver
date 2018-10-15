import { Component, OnInit } from "@angular/core";
import { Currency } from "../../model/classes/Currency";
import { Language } from "../../model/classes/Language";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.scss"]
})
export class NavbarComponent implements OnInit {
  selectedLanguage: Language;
  selectedCurrency: Currency;
  languages: Language[] = [];
  currencies: Currency[] = [];

  constructor() {}

  ngOnInit() {
    this.loadCurrencies();
    this.loadLanguages();
    this.setCurrencyAndLanguageUsingLanguageOfBrowser();
  }

  changeStyle(){
    console.log("FDSA");
  }

  saveUserLanguageAndCurrencySettings(selectedLanguage: Language, selectedCurrency: Currency){
    this.selectedLanguage = selectedLanguage;
    this.selectedCurrency = selectedCurrency;
    //save settings
    //reload page
  }


  setCurrencyAndLanguageUsingLanguageOfBrowser() {
    const englishBrowser = "en-GB";
    const americanBrowser = "en-US";
    const germanBrowser = "de";
    const polishBrowser = "pl";
    let languageOfBrowser = navigator.language;
  
    if (languageOfBrowser === englishBrowser) {
      this.selectedLanguage = this.languages.find(lang => lang.name === 'English');
      this.selectedCurrency = this.currencies.find(curr => curr.name === 'GBP');
    }
    else if (languageOfBrowser === americanBrowser){
      this.selectedLanguage = this.languages.find(lang => lang.name === 'English');
      this.selectedCurrency = this.currencies.find(curr => curr.name === 'USD');
    }
    else if (languageOfBrowser === germanBrowser){
      this.selectedLanguage = this.languages.find(lang => lang.name === 'Deutsch');
      this.selectedCurrency = this.currencies.find(curr => curr.name === 'EUR');
    }
    else if (languageOfBrowser === polishBrowser){
      this.selectedLanguage = this.languages.find(lang => lang.name === 'Polski');
      this.selectedCurrency = this.currencies.find(curr => curr.name === 'ZŁ');
    }
    else{
      this.selectedLanguage = this.languages.find(lang => lang.name === 'English');
      this.selectedCurrency = this.currencies.find(curr => curr.name === 'USD');
    }
  }

  loadCurrencies() {
    this.currencies.push(new Currency("EUR", "€", ""));
    this.currencies.push(new Currency("GBP", "£", ""));
    this.currencies.push(new Currency("USD", "$", ""));
    this.currencies.push(new Currency("PLN", "ZŁ", ""));
  }

  loadLanguages() {
    this.languages.push(new Language("English", ""));
    this.languages.push(new Language("Polski", ""));
    this.languages.push(new Language("Deutsch", ""));
  }

  
  
}
