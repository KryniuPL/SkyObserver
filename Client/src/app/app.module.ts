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
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatAutocompleteModule, MatInputModule, MatNativeDateModule, MAT_DATE_LOCALE} from '@angular/material';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MultiTravelComponent } from './layout/multi-travel/multi-travel.component';
import { AirportsService } from './services/airports-service/airports-service.service';
import { HttpClientModule } from '@angular/common/http';
import { ExploreMapComponent } from './layout/explore-map/explore-map.component';
import { AgmCoreModule } from '@agm/core';
import { FlightsService } from './services/flights-service/flights.service';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { ResultComponent } from './layout/result/result.component';
import { NgxSpinnerModule } from 'ngx-spinner';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HomePageComponent,
    SearchPanelComponent,
    PageNotFoundComponent,
    MultiTravelComponent,
    ExploreMapComponent,
    ResultComponent,
  ],
  imports: [
    AgmCoreModule.forRoot({
      apiKey: '***REMOVED***'
    }),
    NgxSpinnerModule,
    MatCheckboxModule,
    HttpClientModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
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
  providers: [
    AirportsService,
    FlightsService,
    { 
      provide: MAT_DATE_LOCALE, useValue: 'en-GB' 
    },

  ],
  bootstrap: [AppComponent]
})
export class AppModule { 

}
