import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FooterComponent } from './layout/footer/footer.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { SearchPanelComponent } from './layout/search-panel/search-panel.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatAutocompleteModule, MatInputModule} from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HomePageComponent,
    SearchPanelComponent
  ],
  imports: [
    MatInputModule,
    MatAutocompleteModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MDBBootstrapModule.forRoot(),
    BrowserModule,
    AppRoutingModule
  ],
  schemas: [ NO_ERRORS_SCHEMA ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { 

}
