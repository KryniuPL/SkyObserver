import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomePageComponent } from "./pages/home-page/home-page.component";
import { FooterComponent } from "./layout/footer/footer.component";
import { PageNotFoundComponent } from "./pages/page-not-found/page-not-found.component";
import { ExploreMapComponent } from "./layout/explore-map/explore-map.component";

const routes: Routes = [
  { path: "", component: HomePageComponent },
  {
    path: "not-found",
    component: PageNotFoundComponent
  },
  {
    path: "explore-map",
    component: ExploreMapComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
