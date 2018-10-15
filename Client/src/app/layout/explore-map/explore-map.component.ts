import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-explore-map',
  templateUrl: './explore-map.component.html',
  styleUrls: ['./explore-map.component.scss']
})
export class ExploreMapComponent implements OnInit {

  lat: number;
  lng: number;

  constructor() { }

  ngOnInit() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
          this.lat = position.coords.latitude;
          this.lng = position.coords.longitude;
      });
  } else {
      this.lat = 52.2296756;
      this.lng = 21.012228700000037;
  }
  }

}
