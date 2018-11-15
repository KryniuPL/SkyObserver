import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomePageComponent } from "./pages/home-page/home-page.component";
import { FooterComponent } from "./layout/footer/footer.component";
import { PageNotFoundComponent } from "./pages/page-not-found/page-not-found.component";
import { ExploreMapComponent } from "./layout/explore-map/explore-map.component";
import { ResultComponent } from "./layout/result/result.component";

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'result', component: ResultComponent },
  {
    path: 'explore-map',
    component: ExploreMapComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
