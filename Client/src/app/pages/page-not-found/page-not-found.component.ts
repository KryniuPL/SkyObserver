import { Component, OnInit } from '@angular/core';
import { Title } from '../../../../node_modules/@angular/platform-browser';
import { Router } from '../../../../node_modules/@angular/router';

@Component({
  selector: 'app-page-not-found',
  templateUrl: './page-not-found.component.html',
  styleUrls: ['./page-not-found.component.scss']
})
export class PageNotFoundComponent implements OnInit {

  public constructor(private titleService: Title,private router: Router) { }

  public setTitle( newTitle: string) {
    this.titleService.setTitle( newTitle );
  }

  ngOnInit() {
    this.setTitle('Page Not Found');
  }
  goHome(){ 
    this.router.navigate(['']);
    this.titleService.setTitle('SkyObserver');
  }
}
